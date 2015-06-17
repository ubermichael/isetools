/*
 *
 * Copyright (C) 2015 Maxwell Terpstra <terpstra@alumni.uvic.ca>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation version 2.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package ca.nines.ise.log;

import nu.xom.*;
import java.io.OutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * Serializes a Log as to XML
 *
 * The output will be structured like:
 * <log>
 *  <source name="">
 *       <entry code="" columnNumber="" lineNumber="" severity="" tln="">
 *           <message>...</message>
 *           <context>...</context>
 *           <note>...</note>
 *       </entry>
 *   </source>
 * </log>
 * 
 * Log entries are grouped by source, and each may contain a "message" string,
 * a "context" quote, and multiple "note"s.
 *
 * @author Maxwell Terpstra <terpstra@alumni.uvic.ca>
 */
public class XMLLogSerializer {

    private static Attribute attr(String name, String value) {
        if (value == null) {
            return new Attribute(name, "");
        } else {
            return new Attribute(name, value);
        }
    }

    private static Element createEntry(Message m) {
        Element entry = new Element("entry");

        entry.addAttribute(attr("code", m.getCode()));
        entry.addAttribute(attr("severity", m.getSeverity()));
        entry.addAttribute(attr("lineNumber", Integer.toString(m.getLineNumber())));
        entry.addAttribute(attr("columnNumber", Integer.toString(m.getColumnNumber())));
        entry.addAttribute(attr("tln", m.getTLN()));
        if (!m.getMessage().equals("unknown")) {
            Element msg = new Element("message");
            msg.appendChild( new Text(m.getMessage()) );
            entry.appendChild(msg);
        }
        if (!m.getLine().equals("")) {
            Element ctx = new Element("context");
            ctx.appendChild( new Text(m.getLine()) );
            entry.appendChild(ctx);
        }
        for (String note : m.getNotes()) {
            Element noteEl = new Element("note");
            noteEl.appendChild( new Text(note) );
            entry.appendChild(noteEl);
        }
        return entry;
    }

    public static Document serializeToXom(Log l) {
        HashMap<String, Element> sources = new HashMap<>();
        Element root = new Element("log");
        for (int i=0; i<l.count(); i++) {
            Message m = l.get(i);
            Element entry = createEntry(m);
            String src = m.getSource();
            Element srcEl;           
            if (!sources.containsKey(src)) {
                srcEl = new Element("source");
                srcEl.addAttribute(new Attribute("name", src));
                root.appendChild(srcEl);
                sources.put(src, srcEl);
            } else {
                srcEl = sources.get(src);
            }
            srcEl.appendChild(entry);
        }
        return new Document(root);
    }

    public static void serialize(Log l, OutputStream out) throws IOException {
        new Serializer(out).write(serializeToXom(l));
    }

}
