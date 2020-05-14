package com.portfolio.demo.dao;

import com.portfolio.demo.model.Image;

import java.util.UUID;

public interface ImageDao {

    int addImage(UUID id, Image image);

    default int addImage(Image image) {
        UUID id = UUID.randomUUID();
        return addImage(id, image);
    }

    int deleteImageById(UUID id);

}
