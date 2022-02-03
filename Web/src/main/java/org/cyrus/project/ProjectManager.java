package org.cyrus.project;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class ProjectManager {

    private final Collection<Project> projects = new HashSet<>();

    public Collection<Project> getProjects() {
        return this.projects;
    }

    public Optional<Project> getProject(String id) {
        return this.getProjects().stream().filter(project -> project.getId().equals(id)).findAny();
    }
    
    public void register(Project project) {
        this.projects.add(project);
    }
}
