/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
lexer grammar ISELexer;


// basic definitions.
TEXT : ~('<' | '{' | '|' | '}' | '>')+ ;

ALPHA : [a-zA-Z] ;

DIGIT : [0-9] ;

ALPHANUM : [a-zA-Z0-9] ;

// space characters
S : [ \t\r\n] ;

// special character markup.

ACCENT : [~_`'"^] ;

COMMENT : '<!--' .*? '-->' ;

BAD_COMMENT : '<!' .*? '>' ;

ABBR : '|' .*? '|' ;

CHAR_START : '{' ;

CHAR_END : '}' ;

TYPOGRAPHIC_CHAR : [srwW] ;

SPACE_CHAR : [-# ];

NAMED_CHAR
  : 'c'
  | 'p'
  | 'sm'
  | 'pi'
  | 'ae'
  | 'AE'
  | 'oe'
  | 'OE'
  ;

// quotation marks for quoted strings.
SQ : '\'' ;
DQ : '"' ;
FQ : '“' | '”' ; // MS fancy quotes.

DOUBLE_QUOTED_STR : DQ ~[<"]* DQ ;

SINGLE_QUOTED_STR : SQ ~[<']* SQ ;

UNQUOTED_STR : ~[ ]+;

FANCY_QUOTED_STR : FQ ~[<"“”]* FQ ;

STRING
  : DOUBLE_QUOTED_STR
  | SINGLE_QUOTED_STR
  | UNQUOTED_STR
  | FANCY_QUOTED_STR
  ;

// tags

TAG_OPEN : '<' ;

DOC_TEXT : ~[<{|}>]+ ;

TAG_CLOSE : '>' ;

TAG_SLASH_CLOSE : '/>' ;

TAG_SLASH : '/' ;

TAG_EQ : '=' ;

TAG_NAME : TAG_NameStartChar TAG_NameChar* ;

TAG_NameStartChar : ALPHA ;

TAG_NameChar : ALPHA | DIGIT | '_' | '-' ;

ATTVALUE_VALUE : S* ATTRIBUTE ;

ATTRIBUTE : STRING ;

