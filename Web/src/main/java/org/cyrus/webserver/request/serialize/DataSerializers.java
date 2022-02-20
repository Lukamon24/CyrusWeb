package org.cyrus.webserver.request.serialize;

import org.cyrus.CyrusWeb;
import org.cyrus.file.FileSerializerObject;
import org.cyrus.file.common.generic.AbstractSerializerObject;
import org.cyrus.project.Project;
import org.cyrus.project.ProjectBuilder;
import org.cyrus.project.build.BuildTools;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public interface DataSerializers {

    DataSerialize.Serialize<Project, AbstractSerializerObject> MICRO_PROJECT = value -> new AbstractSerializerObject().set("id", value.getId()).set("name", value.getName());

    DataSerialize.DeSerialize<Project, FileSerializerObject> PROJECT_DATA_DESERIALIZE = value -> {
        String name = value.getString("name").orElseThrow(() -> new IOException("No 'name' specified"));
        String version = value.getString("version").orElseThrow(() -> new IOException("no 'version' specified"));
        String groupId = value.getString("groupId").orElseThrow(() -> new IOException("no 'groupId' specified"));
        String artifactId = value.getString("artifactId").orElseThrow(() -> new IOException("no 'artifactId' specified"));
        String id = value.getString("projectId").orElse(name.toLowerCase().replaceAll(" ", "_"));
        String buildToolString = value.getString("buildTool").orElseThrow(() -> new IOException("no 'buildTool' specified"));
        return new ProjectBuilder()
                .setArtifactId(artifactId)
                .setGroupId(groupId)
                .setName(name)
                .setId(id)
                .setVersion(version)
                .setBuildTool(BuildTools.getTool(buildToolString).orElseThrow(() -> new IOException("no 'buildTool' by the name of " + buildToolString)))
                .build();
    };

    DataSerialize.Serialize<Project, AbstractSerializerObject> PROJECT_DATA_SERIALIZE = value -> {
        AbstractSerializerObject json = new AbstractSerializerObject();
        json.set("name", value.getName());
        json.set("version", value.getVersion());
        json.set("groupId", value.getGroupId());
        json.set("artifactId", value.getArtifactId());
        json.set("projectId", value.getId());
        json.set("buildTool", value.getBuildTool().getName());
        return json;
    };


    DataSerialize.DeSerialize<Project, FileSerializerObject> PROJECT_DESERIALIZE = value -> {
        String id = value.getString("projectId").orElseThrow(() -> new IOException("Missing 'projectId'"));
        return CyrusWeb.getInstance().getProjectManager().getProject(id).orElseThrow(() -> new IOException("No project by that id"));
    };

    static Set<DataSerialize<?, ? extends FileSerializerObject>> getVanillaData () {
        return Arrays.stream(DataSerializers.class.getDeclaredFields()).filter(field -> field.getType().isAssignableFrom(DataSerialize.class)).map(field -> {
            try {
                return (DataSerialize<?, ? extends FileSerializerObject>) field.get(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toSet());
    }
}
