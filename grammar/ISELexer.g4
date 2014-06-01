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

