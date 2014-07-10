parser grammar XParser;

tokens {
    EndExpr,
    Integer,
    String,
    ConstID,
    VarID,
    OpenParen,
    CloseParen,
    OpenBracket,
    CloseBracket,
    Colon,

    KeywordEnd,
    KeywordIf,
    KeywordElse,
    KeywordElsif,

    KeywordTrue,
    KeywordFalse,

    OperatorPlus,
    OperatorMinus,
    OperatorMult,
    OperatorDiv
}

program: expression (EndExpr expression)* EndExpr? EOF;

expression:
      OpenParen expression CloseParen
    | expression infixOperatorPrec1 expression
    | expression infixOperatorPrec2 expression
    | branchExpression
    | constExpression
    | varExpression;

expressions: EndExpr? expression (EndExpr expression)* EndExpr?;

// Operators
// TODO: Support more operators
infixOperatorPrec1: OperatorMult | OperatorDiv;
infixOperatorPrec2: OperatorPlus | OperatorMinus;

// Expressions
constExpression: Integer | String | KeywordTrue | KeywordFalse;
varExpression:   ConstID | VarID;

// TODO: Support single-line ifs
//       'if <cond>: <true_branch> else: <false_branch>'
branchExpression:
    KeywordIf expression expressions (KeywordElsif expression expressions)* (KeywordElse expressions)? KeywordEnd;