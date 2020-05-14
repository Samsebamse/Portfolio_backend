package com.portfolio.demo.dao;

import com.portfolio.demo.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("image")
public class ImageDaoAccessService implements ImageDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ImageDaoAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addImage(UUID imageId, Image image) {
        final String sql = "INSERT INTO image (imageId, projectId, path, dimensions) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, imageId, image.getProjectId(), image.getPath(), image.getDimensions());
    }

    @Override
    public int deleteImageById(UUID imageId) {
        final String sql = "DELETE FROM image WHERE imageId = ?";
        return jdbcTemplate.update(sql, imageId);
    }

}
