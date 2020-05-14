package com.portfolio.demo.dao;

import com.portfolio.demo.model.Project;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


public interface ProjectDao {

    int addProject(UUID id, Project project);

    default int addProject(Project project) {
        UUID id = UUID.randomUUID();
        return addProject(id, project);
    }

    List<Project> getAllProjects();

    Optional<Project> getProjectById(UUID id);

    int deleteProjectById(UUID id);

    int updateProjectById(UUID id, Map<String, String> keyValuePair);
}
