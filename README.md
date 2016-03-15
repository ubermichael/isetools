# ISE Tools

Data parsing, validation, and transformation tools for the [Internet Shakespeare 
Editions](http://internetshakespeare.uvic.ca).

[![Build Status](https://travis-ci.org/ubermichael/isetools.svg?branch=master)](https://travis-ci.org/ubermichael/isetools)

## Basic Usage

The tools are all run from the command line.

```
General usage: java -jar path/to/isetools.jar [command] [options]
For a specific command: java -jar path/to/isetools.jar [command] -h

[command] is one of the following: 

     command   description

       abbrs   Report depreciated abbrs in one or more ISE documents.
 annotations   Validate annotations for an ISE document.
       chars   Report on the special characters used in one or more documents.
    fragment   Modernize the markup of an old-spelling edition.
        help   Display useful help messages.
   modernize   Modernize the markup of an old-spelling edition.
    renumber   Renumber the @n attributes in a tag.
      syntax   Syntax check one or more ISE SGML documents.
   transform   Transform an ISE SGML document another format.
    validate   Validate one or more ISE SGML documents.
     version   Print version information.
      wikify   Create a wikified version of the schema.
       works   List the documents.
```

## Input Documents

ISE documents are based on SGML, with some variations:

 * tags need not be properly nested, but open and close tags must match. `<A><B></A></B>` is perfectly valid, but `<A><B></A>` isn't.
 * although input documents are assumed to be valid UTF-8, there is a syntax for non-ascii characters: `{s}` is shorthand for ſ (long/medial s). `{&017f}` is also acceptable.

## Output

The tools can output XML, SGML, RTF, and plain text. 

## License & Copyright

Copyright (C) 2014-2015 Michael Joyce <ubermichael@gmail.com>

This program is licensed under the GPL version 2. For details see GPL.txt

ISO-639/3 language name index used is from SIL:
	http://www-01.sil.org/iso639-3
	Copyright © 2015 SIL International
	
	
