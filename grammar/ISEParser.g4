parser grammar ISEParser;

options {
  tokenVocab=ISELexer;
}

// a complete document
document : element* ;

// elements

element
  : abbr
  | special_char
  | tag
  | comment
  | content
  ;

// document content/text.
content : TEXT ;

// comments

comment
  : COMMENT
  | BAD_COMMENT
  ;

// abbreviations

abbr : ABBR ;

// Special characters.
defined_char :
  CHAR_START NAMED_CHAR CHAR_END ;

space_char :
  CHAR_START SPACE_CHAR CHAR_END ;

accent_char :
  CHAR_START ACCENT ALPHA CHAR_END ;

typographic_char :
  CHAR_START TYPOGRAPHIC_CHAR CHAR_END ;

ligature_char :
  CHAR_START ALPHA ALPHA ALPHA? CHAR_END;

nested_char :
  CHAR_START (ALPHA | accent_char | typographic_char | ligature_char)+ CHAR_END ;

special_char
  : defined_char
  | space_char
  | accent_char
  | typographic_char
  | ligature_char
  | nested_char
  ;

// tags

tag_name : TAG_NAME ;

attribute_name : TAG_NAME ;

attribute_value : ATTVALUE_VALUE ;

attribute
  : attribute_name TAG_EQ attribute_value
  | attribute_name
  ;

start_tag : TAG_OPEN tag_name attribute* TAG_CLOSE ;

empty_tag : TAG_OPEN tag_name attribute* TAG_SLASH_CLOSE ;

end_tag : TAG_OPEN TAG_SLASH tag_name TAG_CLOSE ;

tag
  : start_tag
  | empty_tag
  | end_tag
  ;
