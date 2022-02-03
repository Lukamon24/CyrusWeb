package org.cyrus.webserver.request.data.json.project;

import org.cyrus.CyrusWeb;
import org.cyrus.file.common.generic.AbstractSerializerObject;
import org.cyrus.project.Project;
import org.cyrus.webserver.request.RequestContext;
import org.cyrus.webserver.request.RequestType;
import org.cyrus.webserver.request.WebRequest;
import org.cyrus.webserver.request.serialize.DataSerializers;

import java.io.IOException;
import java.util.Optional;

public class ProjectMinRequest implements WebRequest {
    @Override
    public RequestType getType() {
        return RequestType.POST;
    }

    @Override
    public String[] getEndPoints() {
        return new String[]{"/data/project/min"};
    }

    @Override
    public void onRequest(RequestContext onRequest) {
        Optional<String> opId;
        try {
            opId = onRequest.getJson().getString("projectId");
        } catch (IOException e) {
            e.printStackTrace();
            onRequest.setStatus(500);
            onRequest.setRaw("Issue reading JSON");
            return;
        }
        if (opId.isEmpty()) {
            onRequest.setStatus(400);
            onRequest.setRaw("The data sent must contain a 'projectId'");
            return;
        }
        Optional<Project> opProject = CyrusWeb.getInstance().getProjectManager().getProject(opId.get());
        if (opProject.isEmpty()) {
            onRequest.setStatus(406);
            onRequest.setRaw("The specified 'projectId' was not found");
            return;
        }
        Project project = opProject.get();
        AbstractSerializerObject obj = DataSerializers.MICRO_PROJECT.serialize(project);
        onRequest.setJson(obj);
    }
}
