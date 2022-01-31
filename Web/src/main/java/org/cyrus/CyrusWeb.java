package org.cyrus;

import org.cyrus.webserver.CyrusWebServerManager;

public abstract class CyrusWeb {

    private CyrusWebServerManager serverManager;

    private static CyrusWeb web;

    public CyrusWeb() {
        web = this;
    }

    protected CyrusWeb setServerManager(CyrusWebServerManager manager) {
        this.serverManager = manager;
        return this;
    }

    public CyrusWebServerManager getServerManager() {
        return this.serverManager;
    }

    public static CyrusWeb getInstance() {
        return web;
    }


}