package org.cyrus.impl.manager;

import com.sun.net.httpserver.HttpExchange;
import org.cyrus.file.FileSerializerTypes;
import org.cyrus.file.common.generic.AbstractSerializerObject;
import org.cyrus.impl.manager.send.DataSender;
import org.cyrus.impl.manager.send.JSONDataSender;
import org.cyrus.impl.manager.send.RawDataSender;
import org.cyrus.webserver.request.RequestContext;
import org.cyrus.webserver.request.RequestType;
import org.cyrus.webserver.state.WebState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CyrusHttpExchange implements RequestContext {

    private final HttpExchange exchange;
    private final WebState state;
    private final AbstractSerializerObject sent;
    private DataSender toSend;
    private int status = 200;

    public CyrusHttpExchange(HttpExchange exchange, WebState state) {
        this.exchange = exchange;
        this.state = state;

        AbstractSerializerObject sent = null;
        try {
            InputStream stream = this.exchange.getRequestBody();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String line = br.lines().collect(Collectors.joining("\n"));
            if (line.startsWith("{") && line.endsWith("}")) {
                sent = FileSerializerTypes.JSON.deserialize(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.sent = null;

    }

    int getStatus() {
        return this.status;
    }

    Optional<DataSender> getToSend() {
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
    public String getEndpoint() {
        return this.exchange.getRequestURI().getPath();
    }

    @Override
    public Map<String, String> queryString() {
        //TODO
        return new HashMap<>();
    }

    @Override
    public AbstractSerializerObject getJson() throws IOException {
        InputStream is = this.exchange.getRequestBody();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String json = br.lines().collect(Collectors.joining(""));
        br.close();
        return FileSerializerTypes.JSON.deserialize(json);
    }

    @Override
    public void setJson(AbstractSerializerObject object) {
        this.toSend = new JSONDataSender(object);
    }

    @Override
    public void setRaw(String value) {
        this.toSend = new RawDataSender(value);
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
