/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
lexer grammar ISELexer;

// abbreviations
ABBR_START : '|' -> pushMode(ABBR) ;

// special characters
CHAR_START : '{' -> pushMode(CHAR) ;

TEXT : ~[<{|}>]+ ;

mode ABBR ;

ABBR_END : '|' ->popMode ;
ABBR_CONTENT : ~'|' ;

mode CHAR ;

CHAR_END : '}' -> popMode ;

CHAR_NAMED_SINGLE : ( 'c' | 'P' ) ;
CHAR_NAMED_DOUBLE : ( 'sm' | 'pi' | 'ae' | 'AE' | 'oe' | 'OE' ) ;
CHAR_TYPOGRAPHIC : ( 's' | 'r' | 'w' | 'W' ) ;
CHAR_SPACE : [#-\ ] ;
CHAR_ACCENT : [^"'`_~] ;
CHAR_ALPHA : [a-zA-Z] ;
