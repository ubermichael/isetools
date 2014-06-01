#!/bin/sh

export CLASSPATH=$CLASSPATH:lib/antlr-4.2.2-complete.jar

dir=src/ca/nines/ise/
lib=src/ca/nines/ise/grammar
pkg=ca.nines.ise.grammar

lexer=grammar/ISELexer.g4
parser=grammar/ISEParser.g4

rm $lib/*
java org.antlr.v4.Tool -o $dir -package $pkg -lib $lib $lexer
java org.antlr.v4.Tool -o $dir -package $pkg -lib $lib $parser
