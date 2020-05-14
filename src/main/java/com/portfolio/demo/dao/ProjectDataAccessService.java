package com.portfolio.demo.dao;

import com.portfolio.demo.model.Image;
import com.portfolio.demo.model.Project;
import com.portfolio.demo.util.HelperFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository("project")
public class ProjectDataAccessService implements ProjectDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProjectDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addProject(UUID id, Project project) {
        final String sql = "INSERT INTO project (projectid, title, description) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, id, project.getTitle(), project.getDescription());
    }

    @Override
    public List<Project> getAllProjects() {

        //Join two tables. Creates two lists, one for projects and one for all images.
        final String sql = "SELECT * FROM project LEFT JOIN image USING (projectid)";
        List<Image> allImages = new ArrayList<>();
        List<Project> allProjects = jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID projectId = UUID.fromString(resultSet.getString("projectid"));
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");

            String imageId = resultSet.getString("imageid");
            if(imageId != null){
                String path = resultSet.getString("path");
                String dimensions = resultSet.getString("dimensions");
                allImages.add(new Image(UUID.fromString(imageId), projectId, path, dimensions));
            }
            return new Project(projectId, title, description, new ArrayList<>());
        });

        //Calls a helperfunction to distinct by provided id
        List<Project> distinctList =
                allProjects
                        .stream()
                        .filter(HelperFunctions.distinctByKey(project -> project.getProjectId()))
                        .collect(Collectors.toList());

        //Loops through all projects and all images and insert images to project it belongs
        return distinctList.stream().map(project -> {
            List<Image> temp =
                    allImages.stream()
                            .filter(image -> image.getProjectId().equals(project.getProjectId()))
                            .collect(Collectors.toList());
            project.getImages().get().addAll(temp);
            return project;
        }).collect(Collectors.toList());

    }

    @Override
    public Optional<Project> getProjectById(UUID id) {
        return getAllProjects().stream().findAny().filter(p -> p.getProjectId().equals(id));
    }

    @Override
    public int deleteProjectById(UUID projectId) {
        final String sql = "DELETE FROM project WHERE projectid = ?";
        return jdbcTemplate.update(sql, projectId);
    }

    @Override
    public int updateProjectById(UUID projectId, Map<String, String> keyValuePair) {
        StringJoiner stringJoiner = new StringJoiner("", "UPDATE project SET ", " WHERE projectid = ?");
        final String sql =  stringJoiner.add(HelperFunctions.createPATCHsql(keyValuePair)).toString();
        return jdbcTemplate.update(sql, projectId);
    }

}
