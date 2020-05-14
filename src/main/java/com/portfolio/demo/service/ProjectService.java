package com.portfolio.demo.service;

import com.portfolio.demo.dao.ProjectDao;
import com.portfolio.demo.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService {

    private final ProjectDao projectDao;

    @Autowired
    public ProjectService(@Qualifier("project") ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public int addProject(Project project) {
        return projectDao.addProject(project);
    }

    public List<Project> getAllProjects() {
        return projectDao.getAllProjects();
    }

    public Optional<Project> getProjectById(UUID id) {
        return projectDao.getProjectById(id);
    }

    public int deleteProjectById(UUID id) {
        return projectDao.deleteProjectById(id);
    }

    public int updateProjectById(UUID id, Map<String, String> keyValuePair) {
        return projectDao.updateProjectById(id, keyValuePair);
    }
}
