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

    //Find a photo searching by ID
    public Photo findPhoto(Integer id) throws PhotoNotFoundException {
        Optional<Photo> photo = photoRepo.findById(id);
        if (photo.isPresent()) {
            return photo.get();
        } else {
            throw new PhotoNotFoundException("Can't find photo with " + id + " id");
        }
    }

    //Find a photo searching by name
    public List<Photo> findPhotoByName(String name) {
        return photoRepo.findByTitleContainingIgnoreCase(name);
    }

    //Create a new photo
    public Photo createPhoto(Photo photo) {
        Photo newPhoto = new Photo();
        newPhoto.setTitle(photo.getTitle());
        newPhoto.setImgUrl(photo.getImgUrl());
        newPhoto.setVisible(photo.getVisible());
        newPhoto.setDescription(photo.getDescription());
        newPhoto.setCategories(photo.getCategories());
        return photoRepo.save(newPhoto);
    }

    //Update an existing photo
    public Photo updatePhoto(Photo photo, Integer id) throws PhotoNotFoundException {
        Photo updatePhoto = findPhoto(id);
        updatePhoto.setTitle(photo.getTitle());
        updatePhoto.setImgUrl(photo.getImgUrl());
        updatePhoto.setVisible(photo.getVisible());
        updatePhoto.setDescription(photo.getDescription());
        updatePhoto.setCategories(photo.getCategories());
        return photoRepo.save(updatePhoto);
    }

    //Delete a photo
    public void deletePhoto(Integer id) throws PhotoNotFoundException {
        Photo photo = findPhoto(id);
        photoRepo.delete(photo);
    }
}
