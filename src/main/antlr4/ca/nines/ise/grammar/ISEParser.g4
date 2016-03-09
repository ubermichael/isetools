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

parser grammar ISEParser;

options {
  tokenVocab=ISELexer;
}

// a complete document
document : element+ ;

// elements

element
  : abbr
  | comment
  | character
  | tag
  | content
  ;

// abbreviations

abbr
  : ABBREVIATION;

comment
  : COMMENT_GOOD
  | COMMENT_BAD
  ;

// special characters

charUnicode 
  : CHAR_UNICODE 
  ;

charCodePoint
  : CHAR_CODEPOINT
  ;

charDigraph 
  : CHAR_DIGRAPH 
  ;

charSpace   
  : CHAR_SPACE 
  ;

charAccent  
  : CHAR_ACCENT 
  ;

charTypographic 
  : CHAR_TYPOGRAPHIC 
  ;

charLigature
  : CHAR_LIGATURE
  ;

charNested
  : CHAR_NESTED
  ;

character
  : charAccent
  | charCodePoint
  | charDigraph 
  | charLigature
  | charNested
  | charSpace
  | charTypographic
  | charUnicode 
  ;

// attributes before tags

attribute
  : attributeName TAG_EQ attributeValue
  | attributeName
  ;

attributeName
  : TAG_NAME
  ;

attributeValue
  : ATTRIBUTE_VALUE
  ;

// tags

emptyTag
  : TAG_START tagName attribute* TAG_SLASH_END
  ;

endTag
  : TAG_START TAG_SLASH tagName TAG_END
  ;

startTag
  : TAG_START tagName attribute* TAG_END
  ;

tag
  : emptyTag
  | endTag
  | startTag
  ;

tagName
  : TAG_NAME ;

// content - should be last.

content 
    : TEXT 
    | NEWLINE
    ;
