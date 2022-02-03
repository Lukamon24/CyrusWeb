package org.cyrus.webserver.request;

import java.io.IOException;

public interface WebRequest {

    RequestType getType();

    String[] getEndPoints();

    void onRequest(RequestContext onRequest) throws IOException;
}
