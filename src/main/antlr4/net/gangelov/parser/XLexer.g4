lexer grammar XLexer;

Whitespace: [ \r\t]+ -> skip;

EndExpr: [;\n]+;

// Constants
// TODO: Support other notations and number systems
Integer: [0-9]+;
// TODO: Support escape sequences
String: '"' (~'"'|'\\"')* '"' | '\'' (~'\''|'\\\'')* '\'';

OpenParen:  '(';
CloseParen: ')';

OpenBracket:  '[';
CloseBracket: ']';

OpenBrace:  '{';
CloseBrace: '}';

GenericTypeSpecOpen:  '<';
GenericTypeSpecClose: '>';

Colon: ':';
Comma: ',';

FnTypeSeparator: '->';

// Keywords
End:     'end';
If:      'if';
Else:    'else';
Elsif:   'elsif';
Class:   'class';
Iface:   'interface';
Private: 'private';
Public:  'public';
Fn:      'fn';
Def:     'def';
New:     'new';

True:  'true';
False: 'false';

// Operators
OperatorSend:   '.';
OperatorRef:    '&'; // TODO: Use this

OperatorPrec3Right: OperatorAssign;
OperatorAssign: '=';

OperatorPrec2: OperatorPlus | OperatorMinus;
OperatorPlus:  '+';
OperatorMinus: '-';

OperatorPrec1: OperatorMult | OperatorDiv;
OperatorMult:  '*';
OperatorDiv:   '/';

// TODO: Support unicode
ConstID: [A-Z] [a-zA-Z0-9_?!]*;
VarID:   [a-z] [a-zA-Z0-9_?!]*;

FullyQualifiedConstID: ([a-zA-Z0-9_]+ '::')* ConstID;
