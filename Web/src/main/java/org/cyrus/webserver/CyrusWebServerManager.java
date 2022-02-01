package org.cyrus.webserver;

import org.cyrus.webserver.request.RequestContext;
import org.cyrus.webserver.request.WebRequest;
import org.cyrus.webserver.state.WebState;
import org.cyrus.webserver.user.User;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

public interface CyrusWebServerManager {

    Set<User> getUsers();

    Set<WebState> getActiveStates();

    Set<WebRequest> getRequestHandlers();

    User register(InetSocketAddress address);

    void register(WebState state);

    void register(WebRequest request);

    WebState register(User user);

    default Optional<WebRequest> getRequestHandler(RequestContext context) {
        return this
                .getRequestHandlers()
                .parallelStream()
                .filter(handler -> Arrays.stream(handler.getEndPoints()).anyMatch(endpoint -> Arrays.asList(context.getEndpoint()).contains(endpoint)))
                .findAny();
    }

    default Optional<WebState> getActiveState(User user) {
        return this.getActiveStates().parallelStream().filter(state -> state.getUser().equals(user)).findAny();
    }

    default Optional<User> getUser(InetSocketAddress address) {
        return this.getUsers().parallelStream().filter(user -> user.getAddress().getAddress().toString().equals(address.getAddress().toString())).findAny();
    }
}
