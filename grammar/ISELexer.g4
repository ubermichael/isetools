/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
lexer grammar ISELexer;

// abbreviations
ABBR_DELIM : '|' ;

// special characters
CHAR_START : '{' ;
CHAR_END : '}' ;

CHAR_NAMED : ( 'c' | 'P' | 'sm' | 'pi' | 'ae' | 'AE' | 'oe' | 'OE' ) ;
CHAR_SPACE : [#-\ ] ;
CHAR_ACCENT : [^"'`_~] ;
CHAR_TYPOGRAPHIC : [srwW] ;
CHAR_LIGATURE : ALPHA ALPHA ALPHA? ;

// basic definitions. These are very generic, and must be last.
ALPHA : [a-zA-Z]+ ;
