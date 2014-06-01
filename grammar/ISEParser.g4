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
  : CHAR_START CHAR_ACCENT ALPHA CHAR_END ;

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
