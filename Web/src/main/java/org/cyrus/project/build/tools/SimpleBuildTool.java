package org.cyrus.project.build.tools;

import org.cyrus.project.LoadedProject;
import org.cyrus.project.Project;
import org.cyrus.project.build.BuildTool;

public class SimpleBuildTool implements BuildTool {
    @Override
    public String getName() {
        return "Simple";
    }

    @Override
    public void setupEnvironment(Project project) {

    }

    @Override
    public void loadEnvironment(Project project) {

    }

    @Override
    public void resolveDependencies(LoadedProject project) {

    }

    @Override
    public boolean canBeUsed() {
        return false;
    }
}
