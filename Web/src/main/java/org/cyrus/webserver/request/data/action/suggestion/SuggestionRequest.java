package org.cyrus.webserver.request.data.action.suggestion;

import org.cyrus.file.common.generic.AbstractSerializerObject;
import org.cyrus.webserver.request.RequestContext;
import org.cyrus.webserver.request.RequestType;
import org.cyrus.webserver.request.WebRequest;

import java.io.IOException;

public class SuggestionRequest implements WebRequest {
    @Override
    public RequestType getType() {
        return RequestType.POST;
    }

    @Override
    public String[] getEndPoints() {
        return new String[]{"/action/suggest/"};
    }

    @Override
    public void onRequest(RequestContext onRequest) throws IOException {
        var temp = new AbstractSerializerObject();

        var entry = new AbstractSerializerObject();
        entry.set("type", "field");
        entry.set("value", "test");


        temp.setRaw("suggestions", entry);
        onRequest.setJson(temp);
    }
}
