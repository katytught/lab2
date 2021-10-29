grammar calc;
compUnit : funcDef;
funcDef  : FuncType Ident '(' ')' block;
FuncType : 'int';
Ident    : 'main';
block    : '{' stmt '}';
stmt     : 'return' exp ';';
Number             : Decimalconst | Octalconst | Hexadecimalconst;
Decimalconst      : Nonzerodigit(Nonzerodigit|'0')*;
Octalconst        : '0'Octaldigit*;
Hexadecimalconst  : Hexadecimalprefix Hexadecimaldigit*;
Hexadecimalprefix : '0x' | '0X';
Nonzerodigit      : '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9';
Octaldigit        : '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7';
Digit              : '0' | Nonzerodigit;
Hexadecimaldigit  : '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9'
                      | 'a' | 'b' | 'c' | 'd' | 'e' | 'f'
                      | 'A' | 'B' | 'C' | 'D' | 'E' | 'F';
exp       :addexp;
addexp :mulexp|addexp Addfunc mulexp;
Addfunc : '+'|'-';
mulexp:unaryexp|mulexp Mulfunc unaryexp;
Mulfunc : '*'|'/'|'%';
unaryexp:primaryexp|Addfunc unaryexp;
primaryexp:'(' exp ')'|Number;
WHITE_SPACE: ['\t'|'\n'|' '|'\r'] -> skip; // -> skip 表示解析时跳过该规则