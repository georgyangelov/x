parser grammar XParser;

options {
    tokenVocab=XLexer;
}

program
    : (EndExpr* (classDef|interfaceDef) EndExpr*)* EOF
    ;

code
    : expression (EndExpr expression)* EndExpr?
    ;

// TODO: Support specifying base class and interfaces
classDef
    : Class type EndExpr (EndExpr* methodDef EndExpr*)* End
    ;

interfaceDef
    : Iface type EndExpr (EndExpr* (methodDef|methodDecl) EndExpr*)* End
    ;

// TODO: Support specifying block type
methodDef
    : Def methodID (OpenParen formalArgumentList CloseParen)? type? (Colon|EndExpr) code End
    ;

methodDecl
    : Def methodID (OpenParen formalArgumentList CloseParen)? type?
    ;

formalArgument
    : VarID type
    | OperatorRef VarID? type
    ;

formalArgumentList
    : formalArgument (Comma formalArgument)*
    ;

type
    : (ConstID | FullyQualifiedConstID) (GenericTypeSpecOpen typeList GenericTypeSpecClose)? # generic
    | OpenBracket type CloseBracket                        # arrayAlias
    | OpenBrace type CloseBrace                            # setAlias
    | OpenBrace type Colon type CloseBrace                 # hashAlias
    | OpenBrace typeList? FnTypeSeparator type? CloseBrace # functionAlias
    | varExpression                                        # genericVarType
    ;

typeList
    : type (Comma type)*
    ;

expression
    : OpenParen expression CloseParen                             # parentized
    | classID    OperatorSend methodID actualArgumentList? block? # staticCall
    | expression OperatorSend methodID actualArgumentList? block? # instanceCall
    | expression infixOperatorPrec1 expression                    # infixPrec1
    | expression infixOperatorPrec2 expression                    # infixPrec2
    | expression infixOperatorPrec3 expression                    # infixPrec2
    | New type                                                    # objectCreate
    | branchExpression                                            # branch
    | constExpression                                             # constant
    | varExpression                                               # variable
    ;

expressions
    : EndExpr? expression (EndExpr expression)* EndExpr?
    ;

// TODO: Support inline blocks
block
    : Colon blockArgumentNames? EndExpr code End
    ;

blockArgumentNames
    : varExpression (Comma varExpression)*
    ;

// Operators
// TODO: Support more operators
infixOperatorPrec1
    : OperatorMult
    | OperatorDiv
    ;

infixOperatorPrec2
    : OperatorPlus
    | OperatorMinus
    ;

infixOperatorPrec3
    : OperatorAssign
    ;

// Expressions
constExpression
    : Integer
    | String
    | True
    | False
    ;

varExpression
    : VarID
    ;

// Identificators
methodID
    : ConstID
    | VarID
    ;

classID
    : ConstID
    | FullyQualifiedConstID
    ;

// TODO: Support single-line ifs
// '<true_branch> if <cond> else <false_branch>'
branchExpression
    : If expression expressions (Elsif expression expressions)* (Else expressions)? End
    ;

actualArgumentList
    : expression (Comma EndExpr? expression)*
    | OpenParen expression (Comma EndExpr? expression)* CloseParen
    ;
