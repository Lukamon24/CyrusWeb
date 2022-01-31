package org.cyrus.impl.manager;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.cyrus.webserver.CyrusWebServerManager;
import org.cyrus.webserver.request.WebRequest;
import org.cyrus.webserver.state.WebState;
import org.cyrus.webserver.user.User;

import java.io.IOException;
import java.io.OutputStream;
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
            Optional<User> opUser = this.manager.getUser(exchange.getRemoteAddress());
            if (opUser.isEmpty()) {
                opUser = Optional.of(this.manager.register(exchange.getRemoteAddress()));
            }

            Optional<WebState> opState = this.manager.getActiveState(opUser.get());
            //create new if not present
            if (opState.isEmpty()) {
                opState = Optional.of(this.manager.register(opUser.get()));
            }

            CyrusHttpExchange cyrusRequest = new CyrusHttpExchange(exchange, opState.get(),
                    this.request.getEndPoints());
            this.request.onRequest(cyrusRequest);


            Optional<String> opSend = cyrusRequest.getToSend();
            if (opSend.isPresent()) {
                exchange.sendResponseHeaders(cyrusRequest.getStatus(), opSend.get().length());

                OutputStream os = exchange.getResponseBody();
                os.write(opSend.get().getBytes());
                os.close();
            }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new IOException(throwable);
        }
    }
}
