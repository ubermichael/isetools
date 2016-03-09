/*
 /*
 * Copyright (C) 2014 Michael Joyce <ubermichael@gmail.com>
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
package ca.nines.ise.transformer;

import ca.nines.ise.dom.DOM;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.NodeType;
import ca.nines.ise.node.TagNode;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.collections4.iterators.PeekingIterator;

/**
 * Transformer base class, to transform an ISE DOM into another ISE DOM.
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
abstract public class Transformer {

    private static final Logger logger = Logger.getLogger(Transformer.class.getName());

    private PeekingIterator<Node> iterator;

    DOM dom;

    public DOM transform(DOM dom) throws IOException {
        this.dom = new DOM();
        this.dom.setLines(dom.getLines());
        this.dom.setSource(dom.getSource());
        iterator = new PeekingIterator<>(dom.iterator());

        while (iterator.hasNext()) {
            Node n = iterator.next();
            dispatch(n);
        }
        return this.dom;
    }

    public void defaultTransform(Node n) {
        // do nothing by default.
    }

    private void dispatch(Node n) {

        String type = n.type().toString().toLowerCase();
        if (type.equals("char")) {
            // char is not allowed as a method name.
            type = "character";
        }
        String tag = "";
        if (n instanceof TagNode) {
            tag = "_" + ((TagNode) n).getName().toLowerCase();
        }
        String methodName = type + tag;
        Class<?> cls = this.getClass();
        try {
            Method m = cls.getMethod(methodName, n.getClass());
            m.invoke(this, n);
        } catch (NoSuchMethodException ex) {
            System.out.println("Cannot find " + methodName + "(" + n.getClass() + ")");
            this.defaultTransform(n);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    protected Node peek() {
        return iterator.peek();
    }

    void skipTo(NodeType nt, String nodeName) {
        while (iterator.hasNext()) {
            Node n = iterator.next();
            if ((n.type() == nt) && (n.getName().toLowerCase().equals(nodeName.toLowerCase()))) {
                return;
            }
        }
    }

}