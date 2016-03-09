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
import ca.nines.ise.node.EmptyNode;
import ca.nines.ise.node.Node;
import ca.nines.ise.node.StartNode;
import ca.nines.ise.node.TagNode;
import java.io.IOException;

/**
 * Renumbering transformer - changes the \@n attribute of act, scene, line,
 * page, qln, tln, wln as necessary.
 *
 * @author Michael Joyce <ubermichael@gmail.com>
 */
public class Renumberer extends IdentityTransform {

    private int act = 1;
    private int line = 1;
    private int lined = 1;

    private int page = 1;

    private int qln = 0;
    private int qlnd = 1;

    private int scene = 1;
    private int stanza = 1;

    private int tln = 0;
    private int tlnd = 1;

    private int wln = 0;
    private int wlnd = 1;

    private boolean renumberAct = false;
    private boolean renumberLine = false;
    private boolean renumberPage = false;
    private boolean renumberQln = false;
    private boolean renumberScene = false;
    private boolean renumberStanza = false;
    private boolean renumberTln = false;
    private boolean renumberWln = false;

    private boolean inSplit = false;

    public void empty_l(EmptyNode n) {
        boolean inSD;
        Node tmp;

        if (renumberLine) {

            // is this line part of a  split.
            if (n.hasAttribute("part") && n.getAttribute("part").equals("i")) {
                inSplit = true;
            }

            // look ahead to see if this line is a stage direction
            inSD = false;
            int i = n.getPosition() + 1;
            while (i < dom.size()) {
                i++;
                tmp = dom.get(i);
                if (tmp.getName().equals("SD")) {
                    inSD = true;
                    break;
                }
                if (tmp.getName().equals("L")) {
                    inSD = false;
                    break;
                }
            }

            if (inSD) {
                if (inSplit) {
                    n.setAttribute("n", "");
                } else {
                    if (line > 0) {
                        line--;
                    }
                    n.setAttribute("n", line + "." + lined);
                    lined++;
                }
            } else {
                n.setAttribute("n", String.valueOf(line));
                lined = 1;
            }

            if (n.hasAttribute("part") && n.getAttribute("part").equals("f")) {
                inSplit = false;
            }

            if (!inSplit) {
                line++;
            }
        }
        dom.add(n);
    }

    public void empty_tln(EmptyNode n) {
        if (renumberTln) {
            String num = n.getAttribute("n");
            if (num != null && num.contains(".")) {
                n.setAttribute("n", tln + "." + tlnd);
                tlnd++;
            } else {
                tln++;
                n.setAttribute("n", String.valueOf(tln));
                tlnd = 1;
            }
        }
        dom.add(n);
    }

    public void empty_qln(EmptyNode n) {
        if (renumberQln) {
            String num = n.getAttribute("n");
            if (num != null && num.contains(".")) {
                n.setAttribute("n", qln + "." + qlnd);
                qlnd++;
            } else {
                qln++;
                n.setAttribute("n", String.valueOf(qln));
                qlnd = 1;
            }
        }
        dom.add(n);
    }

    public void empty_wln(EmptyNode n) {
        if (renumberWln) {
            String num = n.getAttribute("n");
            if (num != null && num.contains(".")) {
                n.setAttribute("n", wln + "." + wlnd);
                wlnd++;
            } else {
                wln++;
                n.setAttribute("n", String.valueOf(wln));
                wlnd = 1;
            }
        }
        dom.add(n);
    }

    /**
     * @param renumberAct the renumberAct to set
     */
    public void setRenumberAct(boolean renumberAct) {
        this.renumberAct = renumberAct;
    }

    /**
     * @param renumberLine the renumberLine to set
     */
    public void setRenumberLine(boolean renumberLine) {
        this.renumberLine = renumberLine;
    }

    /**
     * @param renumberPage the renumberPage to set
     */
    public void setRenumberPage(boolean renumberPage) {
        this.renumberPage = renumberPage;
    }

    /**
     * @param renumberQln the renumberQln to set
     */
    public void setRenumberQln(boolean renumberQln) {
        this.renumberQln = renumberQln;
    }

    /**
     * @param renumberScene the renumberScene to set
     */
    public void setRenumberScene(boolean renumberScene) {
        this.renumberScene = renumberScene;
    }

    /**
     * @param renumberStanza the renumberStanza to set
     */
    public void setRenumberStanza(boolean renumberStanza) {
        this.renumberStanza = renumberStanza;
    }

    /**
     * @param renumberTln the renumberTln to set
     */
    public void setRenumberTln(boolean renumberTln) {
        this.renumberTln = renumberTln;
    }

    /**
     * @param renumberWln the renumberWln to set
     */
    public void setRenumberWln(boolean renumberWln) {
        this.renumberWln = renumberWln;
    }

    public void start_act(StartNode n) {
        if (renumberAct) {
            n.setAttribute("n", String.valueOf(act));
            act++;
        }
        scene = 1;
        dom.add(n);
    }

    public void start_page(StartNode n) {
        if (renumberPage) {
            n.setAttribute("n", String.valueOf(page));
            page++;
        }
        dom.add(n);
    }

    public void start_scene(StartNode n) {
        if (renumberScene) {
            n.setAttribute("n", String.valueOf(scene));
            scene++;
        }
        dom.add(n);

        // should the first line in the scene be numbered from 0 or 1?
        line = 1;
        Node l = dom.findForward(n, "L");
        if (l == null || !(l instanceof TagNode)) {
            return;
        }
        TagNode tn = (TagNode) l;
        if (!tn.hasAttribute("n")) {
            return;
        }
        if (tn.getAttribute("n").startsWith("0")) {
            line = 0;
        }
    }

    public void start_stanza(StartNode n) {
        if (renumberStanza) {
            n.setAttribute("n", String.valueOf(stanza));
            stanza++;
        }
        dom.add(n);
    }

    @Override
    public DOM transform(DOM dom) throws IOException {
        dom.index();
        return super.transform(dom);
    }

}
