package org.cyrus.webserver.request.data.login;

import org.cyrus.webserver.request.ContentType;
import org.cyrus.webserver.request.RequestContext;
import org.cyrus.webserver.request.RequestType;
import org.cyrus.webserver.request.WebRequest;

import java.io.IOException;

public class LoginStyleSheetRequest implements WebRequest {
    @Override
    public RequestType getType() {
        return RequestType.GET;
    }

    @Override
    public String[] getEndPoints() {
        return new String[]{"/login/global.css"};
    }

    @Override
    public void onRequest(RequestContext onRequest) {
        onRequest.setContentType(ContentType.TEXT_CSS);
        try {
            onRequest.setResource("css/ChooseProject.css");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
