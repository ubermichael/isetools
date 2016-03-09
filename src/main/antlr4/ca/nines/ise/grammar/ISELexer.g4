/*
 * Copyright (C) 2014 Michael Joyce <ubermichael@gmail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation version 2.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

lexer grammar ISELexer;

COMMENT_GOOD : '<!--' .*? '-->' ;
COMMENT_BAD : '<!' .*? '>' ;

// abbreviations
ABBREVIATION : '|' ~'|'* '|' ;

CHAR_UNICODE 
    : 
    ( '{s}'       // long/medial s        ſ     U+017F
    | '{P}'       // pilcrow              ¶     U+00B6
    | '{r}'       // lower rotunda r      ꝛ     U+A75B
    | '{R}'       // upper rotunda r      Ꝛ     U+A75A
    | '{c}'       // lower c with cedilla ç     U+00E7
    | '{C}'       // upper c with cedilla Ç     U+00C7
    | '{th}'      // lower thorn          þ     U+00FE
    | '{TH}'      // upper thorn          Þ     U+00DE  
    ) 
    ;

CHAR_CODEPOINT
    : '{&#x' [0-9a-fA-F]+ '}'
    | '{&#' [0-9]+ '}'
    | '{&' [a-zA-Z0-9]+ '}'
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
    | '{-}'         // soft hyphen
    | '{#}'         // missing space
    )
    ;

CHAR_ACCENT
    : 
    ( '{^'  [a-zA-Z] '}'         // carret   (ê)   U+0302 
    | '{"'  [a-zA-Z] '}'         // umlat    (ë)   U+0308
    | '{,'  [a-zA-Z] '}'         // cedilla  (̧e)   U+0327
    | '{\'' [a-zA-Z] '}'         // acute    (é)   U+0301
    | '{`'  [a-zA-Z] '}'         // grave    (̀e)   U+0300
    | '{_'  [a-zA-Z] '}'         // macron   (ō)   U+0304
    | '{~'  [a-zA-Z] '}'         // tilde    (ñ)   U+0303
    )
    ;

CHAR_TYPOGRAPHIC
    :
    ( '{w}'         // two v characters for a w. No unicode equivalent.
    | '{W}'         // two V characters for a W. No unicode equivalent.
    | '{-' '-'+ '}' // run of hyphens. No unicode equivalent.
    )
    ;

CHAR_LIGATURE
    : '{' [a-zA-Z] [a-zA-Z] [a-zA-Z]? '}' 
    ;

CHAR_NESTED
    : '{' ( [a-zA-Z] | CHAR_UNICODE | CHAR_ACCENT | CHAR_TYPOGRAPHIC ) ( [a-zA-Z] | CHAR_UNICODE | CHAR_ACCENT | CHAR_TYPOGRAPHIC )+ '}' 
    ;

// beginning of tags.
TAG_START 
    : '<' -> pushMode(TAG) 
    ;

// general content.
TEXT 
    : ~[<{|\n]+ 
    ;

NEWLINE
    : '\n'
    ;

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

// recognizing attribute values
TAG_EQ
    : [ ]* '=' [ ]* ->pushMode(ATTR)
    ;

TAG_NAME
    : [a-zA-Z] [a-zA-Z0-9]*
    ;

TAG_WS
    : [ \r\t\n]+ -> channel(HIDDEN)
    ;

// Parsing attribute values
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
    : ~[<"{} '>]
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
