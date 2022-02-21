package org.cyrus.webserver.request.data.get.editor;

import org.cyrus.webserver.request.RequestContext;
import org.cyrus.webserver.request.RequestType;
import org.cyrus.webserver.request.WebRequest;

import java.io.IOException;

public class EditorJavaScriptRequest implements WebRequest {
    @Override
    public RequestType getType() {
        return RequestType.GET;
    }

    @Override
    public String[] getEndPoints() {
        return new String[]{"/editor/global.js"};
    }

    @Override
    public void onRequest(RequestContext onRequest) throws IOException {
        onRequest.setResource("js/Editor.js");
    }
}
