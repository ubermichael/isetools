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

character
  : charUnicode 
  | charDigraph 
  | charSpace
  | charAccent
  | charTypographic
  | charLigature
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

content : TEXT ;