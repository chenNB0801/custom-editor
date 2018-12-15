package com.lang.project;

import com.intellij.openapi.project.Project;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class ProjectModel {
    public String name;
    public String path;

    public ProjectModel(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public static Set<ProjectModel> getProjectModels(Project[] projects) {
        if (null == projects || projects.length == 0) return null;
        Set<ProjectModel> projectModels = new LinkedHashSet<>();
        for (Project project : projects) {
            projectModels.add(new ProjectModel(project.getName(), project.getBasePath()));
        }
        return projectModels;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectModel that = (ProjectModel) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, path);
    }
}
