package org.cyrus;

import org.cyrus.project.Project;
import org.cyrus.project.ProjectManager;
import org.cyrus.webserver.CyrusWebServerManager;
import org.cyrus.webserver.request.serialize.DataSerializers;
import org.cyrus.webserver.request.serialize.SerializeManager;

public abstract class CyrusWeb {

    private CyrusWebServerManager serverManager;
    private SerializeManager serializeManager = new SerializeManager();
    private ProjectManager projectManager = new ProjectManager();

    private static CyrusWeb web;

    public CyrusWeb() {
        web = this;
    }

    protected void init() {
        this.serializeManager.register(DataSerializers.getVanillaData());

        this.projectManager.register(new Project("1", "name1"));
    }

    protected CyrusWeb setServerManager(CyrusWebServerManager manager) {
        this.serverManager = manager;
        return this;
    }

    public ProjectManager getProjectManager() {
        return this.projectManager;
    }

    public SerializeManager getSerializeManager() {
        return this.serializeManager;
    }

    public CyrusWebServerManager getServerManager() {
        return this.serverManager;
    }

    public static CyrusWeb getInstance() {
        return web;
    }


}