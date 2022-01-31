package org.cyrus.webserver.request;

import org.cyrus.webserver.request.data.HomeWebRequest;

import java.util.Set;

public interface WebRequests {

    HomeWebRequest HOME = new HomeWebRequest();

    static Set<? extends WebRequest> getRequests() {
        return Set.of(HOME);
    }
}
