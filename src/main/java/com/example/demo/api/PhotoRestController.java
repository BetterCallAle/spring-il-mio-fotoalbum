package com.example.demo.api;

import com.example.demo.model.Photo;
import com.example.demo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/photos")
public class PhotoRestController {
    @Autowired
    PhotoService photoService;

    @GetMapping
    public List<Photo> index(@RequestParam(name = "title") Optional<String> title) {
        List<Photo> photos;
        if (title.isPresent()) {
            photos = photoService.findPhotoByName(title.get());
        } else {
            photos = photoService.getAllPhotos();
        }

        return photos;
    }
}
