package com.portfolio.demo.api;

import com.portfolio.demo.model.Project;
import com.portfolio.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public void addProject(@Valid @NonNull @RequestBody Project project) {
        projectService.addProject(project);
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping(path = "{id}")
    public Optional<Project> getProjectById(@PathVariable("id") UUID id) {
        return projectService.getProjectById(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteProjectById(@PathVariable("id") UUID id) {
        projectService.deleteProjectById(id);
    }

    @PatchMapping(path = "{id}")
    public void updateProjectById(
            @PathVariable("id") UUID id,
            @Valid @NonNull @RequestBody Map<String, String> keyValuePair
    ) throws SQLException {
        projectService.updateProjectById(id, keyValuePair);
    }
}
