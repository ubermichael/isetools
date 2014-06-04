parser grammar ISEParser;

options {
  tokenVocab=ISELexer;
}

// a complete document
document : element* ;

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
  : ABBR_START ABBR_CONTENT* ABBR_END;

comment
  : COMMENT
  | COMMENT_BAD
  ;


// special characters

character : CHAR_START (CHAR_CONTENT | nested_char )* CHAR_END ;

nested_char : NESTED_START NESTED_CONTENT NESTED_END ;


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