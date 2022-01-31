package org.cyrus.webserver.request;

import org.cyrus.webserver.state.WebState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.stream.Collectors;

public interface RequestContext {

    WebState getSender();

    RequestType getType();

    String[] getEndpoint();

    Optional<String> getString(String key);

    Optional<Integer> getInt(String key);

    Optional<Double> getDouble(String key);

    void set(String key, Object value);

    void setRaw(String value);


    void setHeader(String key, String value);

    void addHeader(String key, String value);

    default void setResource(String resourcePath) throws IOException {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(resourcePath);
        if (stream==null) {
            throw new IllegalArgumentException("Unknown resource of " + resourcePath);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String file = reader.lines().collect(Collectors.joining("\n"));
        this.setRaw(file);
        reader.close();

    }

    default void setStatus(int statusCode) {
        this.setHeader("status", statusCode + "");
    }

    default void setContentType(ContentType type) {
        this.setHeader("Content-Type", type.getContentType());
    }

}
