package org.cyrus.webserver.request.data.login.create;

import org.cyrus.webserver.request.ContentType;
import org.cyrus.webserver.request.RequestContext;
import org.cyrus.webserver.request.RequestType;
import org.cyrus.webserver.request.WebRequest;

import java.io.IOException;

public class CreateProjectJavaScriptRequest implements WebRequest {
    @Override
    public RequestType getType() {
        return RequestType.GET;
    }

    @Override
    public String[] getEndPoints() {
        return new String[]{"/create/project/global.js"};
    }

    @Override
    public void onRequest(RequestContext onRequest) {
        onRequest.setContentType(ContentType.APPLICATION_JAVASCRIPT);
        try {
            onRequest.setResource("js/CreateProject.js");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
