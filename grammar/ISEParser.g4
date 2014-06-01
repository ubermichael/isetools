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

// content - should be last.

content : TEXT ;