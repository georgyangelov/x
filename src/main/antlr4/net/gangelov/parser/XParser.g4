parser grammar XParser;

options {
    tokenVocab=XLexer;
}

methodID: ConstID | VarID;

program: expression (EndExpr expression)* EndExpr? EOF;

expression:
      OpenParen expression CloseParen                       # parentized
    | expression OperatorSend methodID actualArgumentList?  # call
    | expression infixOperatorPrec1 expression              # infixPrec1
    | expression infixOperatorPrec2 expression              # infixPrec2
    | branchExpression                                      # branch
    | constExpression                                       # constant
    | varExpression                                         # variable
    ;

expressions: EndExpr? expression (EndExpr expression)* EndExpr?;

// Operators
// TODO: Support more operators
infixOperatorPrec1: OperatorMult | OperatorDiv;
infixOperatorPrec2: OperatorPlus | OperatorMinus;

// Expressions
constExpression: Integer | String | True | False;
varExpression:   ConstID | VarID  | FullyQualifiedConstID;

// TODO: Support single-line ifs
//       'if <cond>: <true_branch> else: <false_branch>'
branchExpression:
    If expression expressions (Elsif expression expressions)* (Else expressions)? End;

actualArgumentList: expression (Comma EndExpr? expression)*;
