package org.cyrus.impl.manager;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ResourceHttpHandler implements HttpHandler {

    private final String pathToResource;

    public ResourceHttpHandler(String path) {
        this.pathToResource = path;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(this.pathToResource);
            if (is==null) {
                System.err.println("Could not find resource of '" + this.pathToResource + "'");
                return;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String lines = br.lines().collect(Collectors.joining("\n"));
            exchange.sendResponseHeaders(200, lines.length());
            exchange.getResponseBody().write(lines.getBytes());
        } catch (Throwable e) {
            e.printStackTrace();
            throw new IOException(e);
        }
    }
}
