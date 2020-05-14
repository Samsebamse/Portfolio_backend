package com.portfolio.demo.service;

import com.portfolio.demo.dao.ImageDao;
import com.portfolio.demo.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
public class ImageService {
    private final ImageDao imageDao;

    @Autowired
    public ImageService(@Qualifier("image") ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    public int addImage(Image image) {
        return imageDao.addImage(image);
    }

    public int deleteImageById(UUID id) {
        return imageDao.deleteImageById(id);
    }

}
