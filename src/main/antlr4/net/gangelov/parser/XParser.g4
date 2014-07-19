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
    | variable                                             # genericVarType
    ;

typeList
    : type (Comma type)*
    ;

expression
    : OpenParen expression CloseParen
    // TODO: Test nested calls
    | classID    OperatorSend methodID actualArgumentList? block?
    | expression OperatorSend methodID actualArgumentList? block?
    | expression OperatorPrec1 expression
    | expression OperatorPrec2 expression
    |<assoc=right> expression OperatorPrec3Right expression
    | New type
    | branch
    | constant
    | variable
    ;

expressions
    : EndExpr? expression (EndExpr expression)* EndExpr?
    ;

// TODO: Support inline blocks
block
    : Colon blockArgumentNames? EndExpr code End
    ;

blockArgumentNames
    : variable (Comma variable)*
    ;

// Expressions
constant
    : Integer
    | String
    | True
    | False
    ;

variable
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
branch
    : If expression expressions (Elsif expression expressions)* (Else expressions)? End
    ;

actualArgumentList
    : expression (Comma EndExpr? expression)*
    | OpenParen expression (Comma EndExpr? expression)* CloseParen
    ;
