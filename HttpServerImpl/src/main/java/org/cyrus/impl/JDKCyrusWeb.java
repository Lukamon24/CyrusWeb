package org.cyrus.impl;

import com.sun.net.httpserver.HttpServer;
import org.cyrus.CyrusWeb;
import org.cyrus.impl.manager.JDKWebManager;
import org.cyrus.impl.manager.ResourceHttpHandler;
import org.cyrus.webserver.request.WebRequests;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.Scanner;

public class JDKCyrusWeb extends CyrusWeb {

    public static void main(String[] args) throws IOException {
        JDKCyrusWeb web = new JDKCyrusWeb();
        HttpServer server = HttpServer.create(new InetSocketAddress(8500), 0);
        JDKWebManager webManager = new JDKWebManager(server);
        WebRequests.getRequests().forEach(webManager::register);
        URL bootstrap = JDKCyrusWeb.class.getClassLoader().getResource("bootstrap");
        if (bootstrap!=null) {
            String path = bootstrap.getFile();
            String jsPath = path + "/js/";
            String cssPath = path + "/css/";

            String[] jsArray = new String[]{"bootstrap.bundle.js", "bootstrap.bundle.js.map", "bootstrap.bundle.min" +
                    ".js", "bootstrap.bundle.min.js.map", "bootstrap.esm.js", "bootstrap.esm.js.map", "bootstrap.esm" +
                    ".min.js", "bootstrap.esm.min.js.map", "bootstrap.js", "bootstrap.js.map", "bootstrap.min.js",
                    "bootstrap.min.js.map"};

            for (String jsFile : jsArray) {
                server.createContext("/bootstrap/js/" + jsFile, new ResourceHttpHandler(jsPath + jsFile));
            }

            String[] cssArray = new String[]{"bootstrap-grid.css",
                    "bootstrap-grid.css.map",
                    "bootstrap-grid.min.css",
                    "bootstrap-grid.min.css.map",
                    "bootstrap-grid.rtl.css",
                    "bootstrap-grid.rtl.css.map",
                    "bootstrap-grid.rtl.min.css",
                    "bootstrap-grid.rtl.min.css.map",
                    "bootstrap-reboot.css",
                    "bootstrap-reboot.css.map", "bootstrap-reboot.min.css", "bootstrap-reboot.min.css.map", "bootstrap-reboot.rtl.css", "bootstrap-reboot.rtl.css.map", "bootstrap-reboot.rtl.min.css", "bootstrap-reboot.rtl.min.css.map", "bootstrap-utilities.css", "bootstrap-utilities.css.map", "bootstrap-utilities.min.css", "bootstrap-utilities.min.css.map", "bootstrap-utilities.rtl.css", "bootstrap-utilities.rtl.css.map", "bootstrap-utilities.rtl.min.css", "bootstrap-utilities.rtl.min.css.map", "bootstrap.css", "bootstrap.css.map", "bootstrap.min.css", "bootstrap.min.css.map", "bootstrap.rtl.css", "bootstrap.rtl.css.map", "bootstrap.rtl.min.css", "bootstrap.rtl.min.css.map"};
            for (String jsFile : cssArray) {
                server.createContext("/bootstrap/css/" + jsFile, new ResourceHttpHandler("bootstrap/css/" + jsFile));
            }
        }


        web.setServerManager(webManager);
        web.init();

        server.start();

        System.out.println("server: http://localhost:" + server.getAddress().getPort());

        Scanner sc = new Scanner(System.in);
        System.out.println("Type 'stop' to stop the server");
        String arg;
        while (true) {
            arg = sc.nextLine();
            if (arg.equals("stop")) {
                System.out.println("Stopping");
                server.stop(2);
                System.out.println("Stopped");
                return;
            } else if (arg.equals("")) {

            } else {
                System.out.println("Unknown command");
            }
        }
    }
}
