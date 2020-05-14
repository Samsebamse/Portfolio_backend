package com.portfolio.demo.api;

import com.portfolio.demo.model.Image;
import com.portfolio.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/images")
public class ImageController {


    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public void addImage(@Valid @NonNull @RequestBody Image image) {
        imageService.addImage(image);
    }

    @DeleteMapping(path = "{id}")
    public void deleteImageById(@PathVariable("id") UUID id) {
        imageService.deleteImageById(id);
    }

}
