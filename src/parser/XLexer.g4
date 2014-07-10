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

Colon: ':';
Comma: ',';

// Keywords
KeywordEnd:   'end';
KeywordIf:    'if';
KeywordElse:  'else';
KeywordElsif: 'elsif';

KeywordTrue:  'true';
KeywordFalse: 'false';

// Operators
OperatorSend:  '.';

OperatorPlus:  '+';
OperatorMinus: '-';
OperatorMult:  '*';
OperatorDiv:   '/';

// TODO: Support unicode
ConstID: [A-Z][a-zA-Z0-9_]*;
VarID:   [a-z][a-zA-Z0-9_]*;