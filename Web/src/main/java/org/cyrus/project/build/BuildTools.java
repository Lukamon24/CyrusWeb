package org.cyrus.project.build;

import org.cyrus.project.build.tools.SimpleBuildTool;

import java.util.Optional;
import java.util.Set;

public final class BuildTools {

    public static final SimpleBuildTool SIMPLE = new SimpleBuildTool();

    private BuildTools() {
        throw new RuntimeException("Should not create BuildTools");
    }

    public static Set<BuildTool> getTools() {
        return Set.of(SIMPLE);
    }

    public static Optional<BuildTool> getTool(String name) {
        return getTools().stream().filter(tool -> tool.getName().equalsIgnoreCase(name)).findAny();
    }
}
