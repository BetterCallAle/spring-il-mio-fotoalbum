package com.example.demo.service;

import com.example.demo.exceptions.PhotoNotFoundException;
import com.example.demo.model.Photo;
import com.example.demo.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {
    @Autowired
    private PhotoRepository photoRepo;

    //Find all photos from DB
    public List<Photo> getAllPhotos() {
        return photoRepo.findAll();
    }

    //Find one photo searching by ID
    public Photo findPhoto(Integer id) throws PhotoNotFoundException {
        Optional<Photo> photo = photoRepo.findById(id);
        if (photo.isPresent()) {
            return photo.get();
        } else {
            throw new PhotoNotFoundException("Can't find photo with " + id + " id");
        }
    }
}
