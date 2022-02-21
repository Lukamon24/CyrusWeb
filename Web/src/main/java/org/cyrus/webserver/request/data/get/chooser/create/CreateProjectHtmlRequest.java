package org.cyrus.webserver.request.data.get.chooser.create;

import org.cyrus.utils.HtmlHelper;
import org.cyrus.webserver.request.RequestContext;
import org.cyrus.webserver.request.RequestType;
import org.cyrus.webserver.request.WebRequest;

import java.io.IOException;

public class CreateProjectHtmlRequest implements WebRequest {
    @Override
    public RequestType getType() {
        return RequestType.GET;
    }

    @Override
    public String[] getEndPoints() {
        return new String[]{"/create/project"};
    }

    @Override
    public void onRequest(RequestContext onRequest) throws IOException {
        String html = onRequest.setResource("html/CreateProject.html");
        html = HtmlHelper.addToElementById(html, "gradle", "disabled");
        html = HtmlHelper.addToElementById(html, "maven", "disabled");
        onRequest.setRaw(html);
    }
}
