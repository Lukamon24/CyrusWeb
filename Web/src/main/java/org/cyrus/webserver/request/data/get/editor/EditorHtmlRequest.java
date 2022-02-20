package org.cyrus.webserver.request.data.get.editor;

import org.cyrus.webserver.request.RequestContext;
import org.cyrus.webserver.request.RequestType;
import org.cyrus.webserver.request.WebRequest;

import java.io.IOException;

public class EditorHtmlRequest implements WebRequest {
    @Override
    public RequestType getType() {
        return RequestType.GET;
    }

    @Override
    public String[] getEndPoints() {
        return new String[]{"/editor"};
    }

    @Override
    public void onRequest(RequestContext onRequest) throws IOException {
        String projectId = onRequest.queryString().get("projectId");
        //TODO

        onRequest.setResource("html/Editor.html");
    }
}
