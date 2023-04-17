package com.example.demo.api;

import com.example.demo.exceptions.PhotoNotFoundException;
import com.example.demo.model.Photo;
import com.example.demo.service.PhotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/photos")
public class PhotoRestController {
    @Autowired
    PhotoService photoService;

    //INDEX
    @GetMapping
    public List<Photo> index(@RequestParam(name = "title") Optional<String> title) {
        List<Photo> photos;
        if (title.isPresent()) {
            photos = photoService.getAllVisiblePhotosSearchingByName(title.get());
        } else {
            photos = photoService.getAllVisiblePhotos();
        }

        return photos;
    }

    //SHOW
    @GetMapping("/{id}")
    public Photo show(@PathVariable Integer id) {
        try {
            return photoService.findPhoto(id);
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    //STORE
    @PostMapping()
    public Photo store(@Valid @RequestBody Photo photo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            return photoService.createPhoto(photo);
        }
    }

    //UPDATE
    @PutMapping("/{id}")
    public Photo update(@Valid @RequestBody Photo photo, @PathVariable Integer id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            try {
                return photoService.updatePhoto(photo, id);
            } catch (PhotoNotFoundException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }
    }

    //DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            photoService.deletePhoto(id);
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
