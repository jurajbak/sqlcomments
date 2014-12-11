
/**
 * SQLComments declaration grammar for ANTLR 4
 *
 */
grammar SQLComments;

declaration
    : SQLCOMMENT LEFTPARENTHESIS QUOTATION commentName QUOTATION RIGHTPARENTHESIS
    | SQLCOMMENT LEFTPARENTHESIS commentName RIGHTPARENTHESIS
    | SQLCOMMENT LEFTPARENTHESIS parameterDeclaration (',' parameterDeclaration)* RIGHTPARENTHESIS
	;

parameterDeclaration
    :   nameParam
    |   baseClassParam
    |   resultClassParam 
    |   configClassParam
    ;

nameParam
    :  'name' '=' QUOTATION commentName QUOTATION
    ;

baseClassParam   
    :  'baseClass' '=' QUOTATION className QUOTATION
    ;

resultClassParam
    :  'resultClass' '=' QUOTATION className QUOTATION
    |  'resultClass' '=' 'default'
    ;
    
configClassParam
    :  'configClass' '=' QUOTATION className QUOTATION
    |  'configClass' '=' 'default'
    ;

commentName
    :   Identifier
    ;

className
	:	Identifier
	|	className '.' Identifier
	;

Identifier
	:	JavaLetter JavaLetterOrDigit*
	;
	
fragment
JavaLetter
	:	[a-zA-Z$_] // these are the "java letters" below 0xFF
	|	// covers all characters above 0xFF which are not a surrogate
		~[\u0000-\u00FF\uD800-\uDBFF]
		{Character.isJavaIdentifierStart(_input.LA(-1))}?
	|	// covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
		[\uD800-\uDBFF] [\uDC00-\uDFFF]
		{Character.isJavaIdentifierStart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)))}?
	;

fragment
JavaLetterOrDigit
	:	[a-zA-Z0-9$_] // these are the "java letters or digits" below 0xFF
	|	// covers all characters above 0xFF which are not a surrogate
		~[\u0000-\u00FF\uD800-\uDBFF]
		{Character.isJavaIdentifierPart(_input.LA(-1))}?
	|	// covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
		[\uD800-\uDBFF] [\uDC00-\uDFFF]
		{Character.isJavaIdentifierPart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)))}?
	;

SQLCOMMENT  :  '@SQLComment'
    ;

LEFTPARENTHESIS   :   '('
    ;

RIGHTPARENTHESIS   :   ')'
    ;

COMMA   :   ','
    ;
    
QUOTATION   :   '"'
    ;

WS  :  [ \t\r\n\u000C]+ -> skip
    ;
