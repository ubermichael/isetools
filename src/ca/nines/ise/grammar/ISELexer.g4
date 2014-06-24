/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
lexer grammar ISELexer;

COMMENT_GOOD : '<!--' .*? '-->' ;
COMMENT_BAD : '<!' .*? '>' ;

// abbreviations
ABBREVIATION : '|' ~'|'* '|' ;

CHAR_UNICODE 
  : 
    ( '{c}'       // lower c with cedilla ç     U+00E7
    | '{C}'       // upper c with cedilla Ç     U+00C7
    | '{th}'      // lower thorn          þ     U+00FE
    | '{TH}'      // upper thorn          Þ     U+00DE  
    ) 
  ;

CHAR_DIGRAPH 
  :           
    ( '{ae}'        // ae digraph               æ   U+00E6
    | '{AE}'        // AE digraph               Æ   U+00C6
    | '{oe}'        // oe digraph               œ   U+0153
    | '{OE}'        // OE digraph               Œ   U+0152
    | '{qp}'        // qp digraph  (quod/quoth) ȹ   U+0239
    | '{db}'        // db digraph               ȸ   U+0238
    )             
  ;

CHAR_SPACE 
  :  
    ( '{ }'         // extra space
    | '{-}'         // shy space
    | '{#}'         // missing space
    )
  ;

CHAR_ACCENT
  : 
    ( '{^'  [a-zA-Z] '}'         // carret   (ê)   U+0302 
    | '{"'  [a-zA-Z] '}'         // umlat    (ë)   U+0308 
    | '{\'' [a-zA-Z] '}'         // acute    (é)   U+0301
    | '{`'  [a-zA-Z] '}'         // grave    (̀e)   U+0300
    | '{_'  [a-zA-Z] '}'         // macron   (ō)   U+0304
    | '{~'  [a-zA-Z] '}'         // tilde    (ñ)   U+0303
    )
  ;

CHAR_TYPOGRAPHIC
  :
    ( '{s}'         // long/medial s    ſ   U+017F
    | '{r}'         // lower rotunda r  ꝛ   U+A75B
    | '{R}'         // upper rotunda r  Ꝛ   U+A75A
    | '{w}'         // two v characters for a w. No unicode equivalent.
    | '{W}'         // two V characters for a W. No unicode equivalent.
    )
  ;

CHAR_SIMPLE_LIGATURE
  : '{' [a-zA-Z] [a-zA-Z] [a-zA-Z]? '}' 
  ;

CHAR_COMPLEX_LIGATURE
  : '{' ( [a-zA-Z] | CHAR_ACCENT | CHAR_TYPOGRAPHIC )+ '}' 
  ;

// beginning of tags.
TAG_START : '<' -> pushMode(TAG) ;

// general content.
TEXT : ~[<{|]+ ;

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
  : [a-zA-Z] [a-zA-Z0-9]*
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
