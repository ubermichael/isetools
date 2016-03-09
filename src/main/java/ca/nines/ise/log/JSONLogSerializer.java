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

import java.io.OutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Serializes a Log to JSON
 *
 * The output will be structured as a list of objects, each with the following
 * keys:
 *   * source (string)
 *   * code (string)
 *   * severity (string)
 *   * lineNumber (integer)
 *   * columnNumber (integer)
 *   * tln (string|null)
 *   * message (string|null)
 *   * line (string|null)
 *   * notes (array of string)
 *
 * @author Maxwell Terpstra <terpstra@alumni.uvic.ca>
 */
public class JSONLogSerializer {

    private static String escapeString(String s) {
        return s.replace("\"", "\\\"").replace("\n", "\\\\n");
    }

    public static String serializeToJSON(Log l) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i=0; i<l.count(); i++) {
            Message m = l.get(i);
            if (i!=0) sb.append(", ");
            sb.append("{");

            sb.append(" \"source\":\"");
            sb.append(escapeString(m.getSource()));
            sb.append("\", \"code\":\"");
            sb.append(escapeString(m.getCode()));
            sb.append("\", \"severity\":\"");
            sb.append(escapeString(m.getSeverity()));
            sb.append("\", \"lineNumber\":");
            sb.append(Integer.toString(m.getLineNumber()));
            sb.append(", \"columnNumber\":");
            sb.append(Integer.toString(m.getColumnNumber()));
            sb.append(", \"tln\":");
            if (m.getTLN().equals("unknown")) {
                sb.append("null");
            } else {
                sb.append("\"");
                sb.append(escapeString(m.getTLN()));
                sb.append("\"");
            }
            sb.append(", \"message\":");
            if (m.getMessage().equals("unknown")) {
                sb.append("null");
            } else {
                sb.append("\"");
                sb.append(escapeString(m.getMessage()));
                sb.append("\"");
            }
            sb.append(", \"line\":");
            if (m.getLine().equals("")) {
                sb.append("null");
            } else {
                sb.append("\"");
                sb.append(escapeString(m.getLine()));
                sb.append("\"");
            }

            sb.append(", \"notes\":[");
            boolean first = true;
            for (String n : m.getNotes()) {
                if (!first) sb.append(",");
                sb.append("\"");
                sb.append(escapeString(n));
                sb.append("\"");
                first = false;
            }
            sb.append("]");

            sb.append("}");
        }

        sb.append("]");
        return sb.toString();
    }

    public static void serialize(Log l, OutputStream out) throws IOException {
        out.write(serializeToJSON(l).getBytes(StandardCharsets.UTF_8));
    }

}
