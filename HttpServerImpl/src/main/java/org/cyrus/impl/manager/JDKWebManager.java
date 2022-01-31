package org.cyrus.impl.manager;

import com.sun.net.httpserver.HttpServer;
import org.cyrus.impl.manager.user.CyrusUser;
import org.cyrus.impl.manager.user.CyrusWebState;
import org.cyrus.webserver.CyrusWebServerManager;
import org.cyrus.webserver.request.WebRequest;
import org.cyrus.webserver.state.WebState;
import org.cyrus.webserver.user.User;

import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class JDKWebManager implements CyrusWebServerManager {

    private HttpServer server;
    private final Set<WebState> states = new HashSet<>();
    private final Set<WebRequest> requestHandlers = new HashSet<>();

    public JDKWebManager(HttpServer server) {
        this.server = server;
    }

    @Override
    public Set<User> getUsers() {
        return this.getActiveStates().parallelStream().map(WebState::getUser).collect(Collectors.toSet());
    }

    @Override
    public Set<WebState> getActiveStates() {
        return Collections.unmodifiableSet(this.states);
    }

    @Override
    public Set<WebRequest> getRequestHandlers() {
        return Collections.unmodifiableSet(this.requestHandlers);
    }

    @Override
    public User register(InetSocketAddress address) {
        return new CyrusUser(address);
    }

    @Override
    public void register(WebState state) {
        this.states.add(state);
    }

    @Override
    public void register(WebRequest request) {
        for (String endpoint : request.getEndPoints()) {
            this.server.createContext(endpoint, new CyrusHttpHandler(this, request));
        }
        this.requestHandlers.add(request);
    }

    @Override
    public WebState register(User user) {
        WebState state = new CyrusWebState(user);
        this.states.add(state);
        return state;
    }
}
