package org.cyrus.webserver.request.data.action.create.project;

import org.cyrus.CyrusWeb;
import org.cyrus.file.FileSerializerObject;
import org.cyrus.file.common.generic.AbstractSerializerObject;
import org.cyrus.project.Project;
import org.cyrus.webserver.request.RequestContext;
import org.cyrus.webserver.request.RequestType;
import org.cyrus.webserver.request.WebRequest;
import org.cyrus.webserver.request.serialize.DataSerializers;

import java.io.IOException;

public class CreateProjectRequest implements WebRequest {
    @Override
    public RequestType getType () {
        return RequestType.POST;
    }

    @Override
    public String[] getEndPoints () {
        return new String[]{"/project/create/"};
    }

    @Override
    public void onRequest (RequestContext onRequest) throws IOException {
        AbstractSerializerObject json = onRequest.getJson();
        Project project = DataSerializers.PROJECT_DATA_DESERIALIZE.deserialize(json);
        CyrusWeb.getInstance().getProjectManager().register(project);

        AbstractSerializerObject newValue = DataSerializers.PROJECT_DATA_SERIALIZE.serialize(project);
        onRequest.setJson(newValue);

    }
}
