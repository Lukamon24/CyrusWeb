package org.cyrus.webserver.request;

public interface WebRequest {

    RequestType getType();

    String[] getEndPoints();

    void onRequest(RequestContext onRequest);
}
