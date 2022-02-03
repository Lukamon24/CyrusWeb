package org.cyrus.webserver.request.data.json.project;

import org.cyrus.CyrusWeb;
import org.cyrus.file.common.generic.AbstractSerializerArray;
import org.cyrus.file.common.generic.AbstractSerializerObject;
import org.cyrus.webserver.request.RequestContext;
import org.cyrus.webserver.request.RequestType;
import org.cyrus.webserver.request.WebRequest;
import org.cyrus.webserver.request.serialize.DataSerializers;

public class ProjectsRequest implements WebRequest {

    @Override
    public RequestType getType() {
        return RequestType.POST;
    }

    @Override
    public String[] getEndPoints() {
        return new String[]{"/data/projects"};
    }

    @Override
    public void onRequest(RequestContext onRequest) {
        AbstractSerializerArray array =
                AbstractSerializerArray.from(CyrusWeb.getInstance().getProjectManager().getProjects(), DataSerializers.MICRO_PROJECT);
        AbstractSerializerObject obj = new AbstractSerializerObject();
        obj.setRaw("projects", array);
        onRequest.setJson(obj);
    }
}
