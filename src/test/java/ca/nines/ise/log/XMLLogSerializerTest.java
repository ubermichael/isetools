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
import org.junit.*;
import static org.junit.Assert.*;

/**
 * @author Maxwell Terpstra <terpstra@alumni.uvic.ca>
 */
public class XMLLogSerializerTest {

    Log l;

    @Before
    public void setup() {
        l = Log.getInstance();
    }

    @Test
    public void emptyLog() {
        Document d = XMLLogSerializer.serializeToXom(l);
        Nodes root = d.query("/log");
        assertTrue("root node named \"log\"", root.size() == 1);
        Nodes descendants = d.query("/log//*");
        assertTrue("root should be empty", descendants.size() == 0);
    }

    @Test
    public void oneSource() {
        l.add(Message.builder(null).build());
        Document d = XMLLogSerializer.serializeToXom(l);
        Nodes sources = d.query("/log/source");
        assertTrue("one source", sources.size() == 1);
    }

    @Test
    public void twoSources() {
        l.add(Message.builder(null).setSource("one").build());
        l.add(Message.builder(null).setSource("two").build());
        Document d = XMLLogSerializer.serializeToXom(l);
        Nodes sources = d.query("/log/source");
        assertTrue("two sources", sources.size() == 2);
        Nodes messageOne = d.query("/log/source[@name='one']/entry");
        Nodes messageTwo = d.query("/log/source[@name='two']/entry");
        assertTrue(
            "one message in each source",
            messageOne.size() == 1 && messageTwo.size() == 1
        );
    }

    @Test
    public void bareMessageIsEmpty() {
        l.add(Message.builder(null).build());
        Document d = XMLLogSerializer.serializeToXom(l);
        Nodes messages = d.query("/log/source/entry");
        assertTrue("one entry", messages.size() == 1);
        Element e = (Element) messages.get(0);
        assertTrue("entry should be empty: "+e.toXML(), e.getChildCount() == 0);
    }

    @Test
    public void messageWithNotes() {
        l.add(
            Message.builder(null)
                .addNote("test")
                .addNote("test2")
                .build()
        );
        Document d = XMLLogSerializer.serializeToXom(l);
        Nodes messages = d.query("/log/source/entry");
        assertTrue("one entry", messages.size() == 1);
        Element e = (Element) messages.get(0);
        Nodes notes = e.query("note");
        assertTrue("entry has two notes", notes.size() == 2);
    }

    @Test
    public void messageWithContext() {
        l.add(
            Message.builder(null)
                .setLine("test")
                .build()
        );
        Document d = XMLLogSerializer.serializeToXom(l);
        Nodes ctx = d.query("/log/source/entry/context");
        assertTrue("one context", ctx.size() == 1);
    }

    @After
    public void teardown() {
        l.clear();
    }

}
