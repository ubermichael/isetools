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
  : ABBR_START ABBR_CONTENT* ABBR_END;

comment
  : COMMENT_GOOD
  | COMMENT_BAD
  ;

// special characters

character
  : CHAR_START
    ( CHAR_CONTENT
    | nestedChar
    )*
  CHAR_END ;

nestedChar
  :  NESTED_START NESTED_CONTENT NESTED_END ;


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

content : TEXT ;