package org.cyrus.impl;

import com.sun.net.httpserver.HttpServer;
import org.cyrus.CyrusWeb;
import org.cyrus.impl.manager.JDKWebManager;
import org.cyrus.webserver.request.WebRequests;

import java.io.IOException;
import java.net.InetSocketAddress;

public class JDKCyrusWeb extends CyrusWeb {

    public static void main(String[] args) throws IOException {
        JDKCyrusWeb web = new JDKCyrusWeb();
        HttpServer server = HttpServer.create(new InetSocketAddress(8500), 0);
        JDKWebManager webManager = new JDKWebManager(server);
        WebRequests.getRequests().forEach(webManager::register);


        web.setServerManager(webManager);
        server.start();

        System.out.println("server: http://localhost:" + server.getAddress().getPort());
        while (true) {

        }

    }
}
