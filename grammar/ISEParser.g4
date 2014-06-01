parser grammar ISEParser;

options {
  tokenVocab=ISELexer;
}

// a complete document
document : element* ;

// elements

element
  : abbr
  | character
  | content
  ;

// abbreviations

abbr
  : ABBR_DELIM .*? ABBR_DELIM ;

// special characters

char_named
  : CHAR_START CHAR_NAMED CHAR_END ;

char_spaced
  : CHAR_START CHAR_SPACE CHAR_END ;

char_accented
  : CHAR_START CHAR_ACCENT CHAR_ALPHA CHAR_END ;

char_typographic
  : CHAR_START CHAR_TYPOGRAPHIC CHAR_END ;

char_ligature
  : CHAR_START ( CHAR_LIGATURE | character )+ CHAR_END ;

character
  : char_named
  | char_spaced
  | char_accented
  | char_typographic
  | char_ligature
  ;

// tags

start_tag : TAG_START TAG_NAME S* attribute* TAG_END ;
empty_tag : TAG_START TAG_NAME S* attribute* TAG_SLASH_END ;
end_tag : TAG_START TAG_SLASH TAG_NAME TAG_END ;
short_tag : TAG_START TAG_NAME S* EQ S* ATTR_VALUE S* TAG_END;

tag
   : start_tag | empty_tag | end_tag | short_tag ;

// attributes

attribute_name : ATTR_NAME ;
attribute_value : ATTR_VALUE ;

attribute
  : attribute_name S* EQ S* attribute_value
  | attribute_name
  ;

content : TEXT ;
