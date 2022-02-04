package org.cyrus.webserver.request;

import org.cyrus.file.common.generic.AbstractSerializerObject;
import org.cyrus.webserver.state.WebState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public interface RequestContext {

    WebState getSender();

    RequestType getType();

    String getEndpoint();

    AbstractSerializerObject getJson() throws IOException;

    void setJson(AbstractSerializerObject object);

    void setRaw(String value);

    void setHeader(String key, String value);

    void addHeader(String key, String value);

    default String setResource(String resourcePath) throws IOException {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(resourcePath);
        if (stream==null) {
            throw new IllegalArgumentException("Unknown resource of " + resourcePath);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String file = reader.lines().collect(Collectors.joining("\n"));
        this.setRaw(file);
        reader.close();
        return file;

    }

    default void setStatus(int statusCode) {
        this.setHeader("status", statusCode + "");
    }

    default void setContentType(ContentType type) {
        this.setHeader("Content-Type", type.getContentType());
    }

}
