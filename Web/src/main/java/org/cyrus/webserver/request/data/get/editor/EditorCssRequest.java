package org.cyrus.webserver.request.data.get.editor;

import org.cyrus.webserver.request.RequestContext;
import org.cyrus.webserver.request.RequestType;
import org.cyrus.webserver.request.WebRequest;

import java.io.IOException;

public class EditorCssRequest implements WebRequest {
    @Override
    public RequestType getType() {
        return RequestType.GET;
    }

    @Override
    public String[] getEndPoints() {
        return new String[]{"/editor/global.css"};
    }

    @Override
    public void onRequest(RequestContext onRequest) throws IOException {
        onRequest.setResource("css/Editor.css");
    }
}
