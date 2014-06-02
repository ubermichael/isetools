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
  | tag
  | content
  ;

// abbreviations

abbr
  : ABBR_START ABBR_CONTENT* ABBR_END;

// special characters

char_named
  : CHAR_START (CHAR_NAMED_SINGLE | CHAR_NAMED_DOUBLE) CHAR_END ;

char_spaced
  : CHAR_START CHAR_SPACE CHAR_END;

char_typographic
  : CHAR_START CHAR_TYPOGRAPHIC CHAR_END ;

char_accented
  : CHAR_START CHAR_ACCENT
    ( CHAR_TYPOGRAPHIC | CHAR_ALPHA | CHAR_NAMED_SINGLE)
    CHAR_END;

char_ligature
  : CHAR_START CHAR_ALPHA+ CHAR_END ;

character
  : char_named
  | char_spaced
  | char_accented
  | char_typographic
  | char_ligature
  ;

// attributes before tags

attribute
  : attribute_name TAG_EQ attribute_value
  | attribute_name
  ;

attribute_name
  : TAG_NAME
  ;

attribute_value
  : ATTRIBUTE_VALUE
  ;

// tags

tag
  : empty_tag
  | end_tag
  | start_tag
  ;

empty_tag
  : TAG_START tag_name attribute* TAG_SLASH_END ;

end_tag
  : TAG_START TAG_SLASH tag_name TAG_END ;

start_tag
  : TAG_START tag_name attribute* TAG_END ;

tag_name
  : TAG_NAME ;

// content - should be last.

content : TEXT ;