package org.cyrus.project;

public class Project {

    private final String name;
    private final String id;

    public Project(String id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
