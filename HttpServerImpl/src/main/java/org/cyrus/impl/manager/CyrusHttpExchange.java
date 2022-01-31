package org.cyrus.impl.manager;

import com.sun.net.httpserver.HttpExchange;
import org.cyrus.webserver.request.RequestContext;
import org.cyrus.webserver.request.RequestType;
import org.cyrus.webserver.state.WebState;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CyrusHttpExchange implements RequestContext {

    private final HttpExchange exchange;
    private final WebState state;
    private final String[] endpoints;
    private final Map<String, String> body;
    private String toSend;
    private int status = 200;

    public CyrusHttpExchange(HttpExchange exchange, WebState state, String... endpoints) {
        this.exchange = exchange;
        this.state = state;
        this.endpoints = endpoints;

        InputStream stream = this.exchange.getRequestBody();
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        this.body = br
                .lines()
                .map(line -> line.split("="))
                .filter(line -> line.length==2)
                .collect(Collectors.toMap(line -> line[0], line -> line[1]));
    }

    int getStatus() {
        return this.status;
    }

    Optional<String> getToSend() {
        return Optional.of(this.toSend);
    }

    @Override
    public WebState getSender() {
        return this.state;
    }

    @Override
    public RequestType getType() {
        String status = this.exchange.getRequestHeaders().get("status").get(0);
        return RequestType.valueOf(status);
    }

    @Override
    public String[] getEndpoint() {
        return this.endpoints;
    }

    @Override
    public Optional<String> getString(String key) {
        return Optional.ofNullable(this.body.get(key));
    }

    @Override
    public Optional<Integer> getInt(String key) {
        return Optional.ofNullable(this.body.get(key)).map(Integer::parseInt);

    }

    @Override
    public Optional<Double> getDouble(String key) {
        return Optional.ofNullable(this.body.get(key)).map(Double::parseDouble);

    }

    @Override
    public void set(String key, Object value) {
        if (this.toSend.isEmpty()) {
            this.toSend = key + "=" + value.toString();
            return;
        }
        this.toSend = this.toSend + "\n" + key + "=" + value.toString();
    }

    @Override
    public void setRaw(String value) {
        this.toSend = value;
    }

    @Override
    public void setHeader(String key, String value) {
        this.exchange.getResponseHeaders().set(key, value);
    }

    @Override
    public void addHeader(String key, String value) {
        this.exchange.getResponseHeaders().set(key, value);
    }

    @Override
    public void setStatus(int statusCode) {
        RequestContext.super.setStatus(statusCode);
        this.status = statusCode;
    }
}
