#!/bin/sh

JING=lib/jing.jar

RNC=src/resources/schemas/sgml-metaschema.rnc
XML=src/resources/schemas/default.xml

java -jar $JING -c $RNC $XML
