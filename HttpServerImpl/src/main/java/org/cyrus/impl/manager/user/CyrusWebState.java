package org.cyrus.impl.manager.user;

import org.cyrus.webserver.state.WebState;
import org.cyrus.webserver.user.User;

public class CyrusWebState implements WebState {

    private final User user;

    public CyrusWebState(User user) {
        this.user = user;
    }

    @Override
    public User getUser() {
        return this.user;
    }
}
