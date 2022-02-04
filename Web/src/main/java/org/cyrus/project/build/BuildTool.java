package org.cyrus.project.build;

import org.cyrus.project.LoadedProject;
import org.cyrus.project.Project;

public interface BuildTool {

    String getName();

    void setupEnvironment(Project project);

    void loadEnvironment(Project project);

    void resolveDependencies(LoadedProject project);

    boolean canBeUsed();

}
