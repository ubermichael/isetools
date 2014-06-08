/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
lexer grammar ISELexer;

COMMENT_GOOD : '<!--' .*? '-->' ;
COMMENT_BAD : '<!' .*? '>' ;

// abbreviations
ABBR_START : '|' -> pushMode(ABBR) ;

// special characters
CHAR_START : '{' -> pushMode(CHAR) ;

// beginning of tags.
TAG_START : '<' -> pushMode(TAG) ;

// general content.
TEXT : ~[<{|}>]+ ;

mode ABBR ;

ABBR_END : '|' ->popMode ;
ABBR_CONTENT : ~'|' ;

mode CHAR ;

CHAR_END : '}' -> popMode ;

CHAR_CONTENT : ~[{}]+ ;

NESTED_START : '{' -> pushMode(NESTED) ;

mode NESTED ;

NESTED_END : '}' -> popMode;
NESTED_CONTENT : ~[{}]+ ;

mode TAG ;

TAG_END
  : '>' -> popMode
  ;

TAG_SLASH_END
  : '/>' -> popMode
  ;

TAG_SLASH
  : '/'
  ;

TAG_EQ
  : '=' ->pushMode(ATTR)
  ;

TAG_NAME
  : [a-zA-Z]+
  ;

TAG_WS
  : [ \r\t\n]+ -> skip
  ;

mode ATTR ;

ATTRIBUTE_VALUE
  : [ ]* ATTRIBUTE -> popMode
  ;

ATTRIBUTE
  : DOUBLE_QUOTE_STRING
  | SINGLE_QUOTE_STRING
  | ATTRIBUTE_CHARS
  ;

fragment ATTRIBUTE_CHAR
  : ~[<"{}'>]
  ;

fragment ATTRIBUTE_CHARS
  : ATTRIBUTE_CHAR+ ' '?
  ;

// basic definitions. These are very generic, and must be last.
fragment DOUBLE_QUOTE_STRING
    : '"' ~[<"]* '"'
    ;

fragment SINGLE_QUOTE_STRING
    : '\'' ~[<']* '\''
    ;
