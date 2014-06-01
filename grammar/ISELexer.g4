/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
lexer grammar ISELexer;

// abbreviations
ABBR_DELIM : '|' ;

// special characters
CHAR_START : '{' -> pushMode(CHAR) ;

// tag fun
TAG_START : '<' -> pushMode(TAG) ;

ALPHA : [a-zA-Z] ;
ALPHANUM : [a-zA-Z0-9] ;
S : [ \r\t\n] ;
EQ : '=' ;
TEXT : ~( '<' | '{' | '|' )+ ;

mode CHAR ;

CHAR_END : '}' -> popMode;

CHAR_NAMED : ( 'c' | 'P' | 'sm' | 'pi' | 'ae' | 'AE' | 'oe' | 'OE' ) ;
CHAR_SPACE : [#-\ ] ;
CHAR_ACCENT : [^"'`_~] ;
CHAR_ALPHA : [a-zA-Z] ;
CHAR_TYPOGRAPHIC : ( 's' | 'r' | 'w' | 'W' ) ;
CHAR_LIGATURE : ALPHA ALPHA ALPHA? ;

mode TAG ;

TAG_END : '>' -> popMode;
TAG_SLASH : '/' ;
TAG_SLASH_END : '/>' ->popMode;

TAG_NAME : ALPHA+ ;

// attribute fun
ATTR_NAME : ALPHA+ ;
ATTR_VALUE : STRING ;

// basic definitions. These are very generic, and must be last.
SQ : '\'' ;
DQ : '"' ;
FQ : '“' | '”' ;

SQ_STRING : SQ ~'\''* SQ ;
DQ_STRING : DQ ~'"'* DQ ;
FQ_STRING : FQ ~[“”"']* FQ ;
UQ_STRING : ~[ \r\t\n]+ ;

STRING : SQ_STRING | DQ_STRING | FQ_STRING | UQ_STRING ;

