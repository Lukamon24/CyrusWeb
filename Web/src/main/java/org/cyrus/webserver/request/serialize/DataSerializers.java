package org.cyrus.webserver.request.serialize;

import org.cyrus.CyrusWeb;
import org.cyrus.file.FileSerializerObject;
import org.cyrus.file.common.generic.AbstractSerializerObject;
import org.cyrus.project.Project;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public interface DataSerializers {

    DataSerialize.Serialize<Project, AbstractSerializerObject> MICRO_PROJECT =
            value -> new AbstractSerializerObject().set("id", value.getId()).set("name", value.getName());

    DataSerialize.DeSerialize<Project, FileSerializerObject> PROJECT_DESERIALIZE = value -> {
        String id = value.getString("projectId").orElseThrow(() -> new IOException("Missing 'projectId'"));
        return CyrusWeb
                .getInstance()
                .getProjectManager()
                .getProject(id)
                .orElseThrow(() -> new IOException("No project by that id"));
    };

    static Set<DataSerialize<?, ?>> getVanillaData() {
        return Arrays
                .stream(DataSerializers.class.getDeclaredFields())
                .filter(field -> field.getType().isAssignableFrom(DataSerialize.class))
                .map(field -> {
                    try {
                        return (DataSerialize<?, ?>) field.get(null);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
}
