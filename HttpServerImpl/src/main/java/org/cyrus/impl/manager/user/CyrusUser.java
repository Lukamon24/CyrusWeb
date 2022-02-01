package org.cyrus.impl.manager.user;

import org.cyrus.webserver.user.User;

import java.net.InetSocketAddress;

public class CyrusUser implements User {

    private final InetSocketAddress address;

    public CyrusUser(InetSocketAddress address) {
        this.address = address;
    }

    @Override
    public InetSocketAddress getAddress() {
        return this.address;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User user)) {
            return false;
        }
        return this.getAddress().getAddress().toString().equals(user.getAddress().getAddress().toString());
    }
}
