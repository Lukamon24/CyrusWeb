package org.cyrus.utils;

public class HtmlHelper {

    public static String addToElementById(String html, String id, String toAdd) {
        String buffer = "";
        int start = 0;
        int end = 0;
        boolean wasEnter = false;
        boolean insideTag = false;
        for (int a = 0; a < html.length(); a++) {
            char at = html.charAt(a);
            if (at=='<') {
                insideTag = true;
            }
            if (at=='>') {
                insideTag = false;
            }
            if (insideTag && at=='\n') {
                wasEnter = true;
                continue;
            }
            if (insideTag && wasEnter && (at==' ' || at=='\t')) {
                continue;
            }
            wasEnter = false;
            buffer = buffer + at;
            if (buffer.endsWith("<inputid=\"" + id + "\"")) {
                start = a - 18;
            }
            if (start!=0) {
                if (buffer.endsWith("/>")) {
                    end = a;
                    break;
                }
            }
        }
        if (start!=end && end!=0) {
            return html.substring(0, start) + html.substring(start, end - 2) + toAdd + html.substring(end - 2);
        }
        throw new IllegalArgumentException("Could not find element with ID of '" + id + "'");
    }
}
