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
  : ABBR_START ABBR_CONTENT* ABBR_END;

// special characters

char_accented
  : CHAR_ACCENT CHAR_ALPHA ;

char_named
  : CHAR_NAMED ;

char_spaced
  : CHAR_SPACE ;

char_typographic
  : CHAR_TYPOGRAPHIC ;

char_ligature
  : CHAR_ALPHA CHAR_ALPHA CHAR_ALPHA ;

character
  :
  CHAR_START (
      char_named
    | char_spaced
    | char_accented
    | char_typographic
    | char_ligature
  ) CHAR_END
  ;
