package org.cyrus.impl.manager;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.cyrus.impl.manager.send.DataSender;
import org.cyrus.webserver.CyrusWebServerManager;
import org.cyrus.webserver.request.WebRequest;
import org.cyrus.webserver.state.WebState;
import org.cyrus.webserver.user.User;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Optional;

public class CyrusHttpHandler implements HttpHandler {

    private final WebRequest request;
    private final CyrusWebServerManager manager;

    public CyrusHttpHandler(CyrusWebServerManager manager, WebRequest request) {
        this.request = request;
        this.manager = manager;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String path = exchange.getRequestURI().getPath();
            if (Arrays.stream(this.request.getEndPoints()).noneMatch(endpoint -> endpoint.equals(path) || (endpoint + "/").equals(path))) {
                exchange.sendResponseHeaders(404, 0);
                return;
            }


            Optional<User> opUser = this.manager.getUser(exchange.getRemoteAddress());
            if (opUser.isEmpty()) {
                opUser = Optional.of(this.manager.register(exchange.getRemoteAddress()));
            }

            Optional<WebState> opState = this.manager.getActiveState(opUser.get());
            //create new if not present
            if (opState.isEmpty()) {
                opState = Optional.of(this.manager.register(opUser.get()));
            }

            CyrusHttpExchange cyrusRequest = new CyrusHttpExchange(exchange, opState.get());
            this.request.onRequest(cyrusRequest);


            Optional<DataSender> opSend = cyrusRequest.getToSend();
            if (opSend.isPresent()) {
                String toSend = opSend.get().asString();
                exchange.sendResponseHeaders(cyrusRequest.getStatus(), toSend.length());

                OutputStream os = exchange.getResponseBody();
                os.write(toSend.getBytes());
                os.close();
            }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
            exchange.sendResponseHeaders(500, throwable.getMessage().length());
            exchange.getResponseBody().write(throwable.getMessage().getBytes());
            exchange.close();
        }
    }
}
