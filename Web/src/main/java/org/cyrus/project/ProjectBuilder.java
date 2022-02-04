package org.cyrus.project;

import org.cyrus.project.build.BuildTool;

public class ProjectBuilder {

    private String name;
    private String id;
    private String groupId;
    private String artifactId;
    private BuildTool buildTool;


    public String getName() {
        return this.name;
    }

    public ProjectBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public ProjectBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public ProjectBuilder setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getArtifactId() {
        return this.artifactId;
    }

    public ProjectBuilder setArtifactId(String artifactId) {
        this.artifactId = artifactId;
        return this;
    }

    public BuildTool getBuildTool() {
        return this.buildTool;
    }

    public ProjectBuilder setBuildTool(BuildTool buildTool) {
        this.buildTool = buildTool;
        return this;
    }



}
