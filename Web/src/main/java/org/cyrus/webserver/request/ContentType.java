package org.cyrus.webserver.request;

public enum ContentType {

    APPLICATION_JSON("application/json"),
    APPLICATION_JAVASCRIPT("application/javascript"),
    TEXT_HTML("text/html"),
    TEXT_CSS("text/css"),
    TEXT_PLAIN("text/plain");

    private final String contentType;

    private ContentType (String contentType) {
        this.contentType = contentType;
    }

    public String getContentType () {
        return this.contentType;
    }
}
