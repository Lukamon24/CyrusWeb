package org.cyrus.project;

import org.cyrus.project.build.BuildTool;

public class Project {

    private final String name;
    private final String id;
    private final String groupId;
    private final String artifactId;
    private final BuildTool buildTool;
    private final String version;


    public Project (ProjectBuilder builder) {
        this.name = builder.getName();
        this.id = builder.getId();
        this.groupId = builder.getGroupId();
        this.artifactId = builder.getArtifactId();
        this.buildTool = builder.getBuildTool();
        this.version = builder.getVersion();
        if (this.name == null || this.id == null || this.groupId == null || this.artifactId == null || this.buildTool == null || this.version == null) {
            throw new IllegalArgumentException("Cannot accept null on any builder property");
        }
    }

    public String getVersion () {
        return this.version;
    }

    public String getId () {
        return this.id;
    }

    public String getName () {
        return this.name;
    }

    public String getGroupId () {
        return this.groupId;
    }

    public String getArtifactId () {
        return this.artifactId;
    }

    public BuildTool getBuildTool () {
        return this.buildTool;
    }
}
