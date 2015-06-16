#!/bin/sh

export CLASSPATH=$CLASSPATH:lib/antlr-4.2.2-complete.jar

pkg=ca.nines.ise.grammar
dir=src/main/java/ca/nines/ise/grammar

lexer=ISELexer.g4
parser=ISEParser.g4

rm -f $dir/*.java
rm -f $dir/*.tokens
java org.antlr.v4.Tool -o . -package $pkg -lib $dir -encoding UTF-8 $dir/ISELexer.g4 
java org.antlr.v4.Tool -o . -package $pkg -lib $dir -encoding UTF-8 $dir/ISEParser.g4 
