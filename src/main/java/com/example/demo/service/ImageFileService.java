package com.example.demo.service;

import com.example.demo.model.ImageFile;
import com.example.demo.model.Photo;
import com.example.demo.repository.ImageFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
public class ImageFileService {
    @Autowired
    private ImageFileRepository imageFileRepo;

    //create a new imageFile
    public ImageFile createImageFile(Photo photo, MultipartFile multipartFile) throws IOException {
        ImageFile imageFile = new ImageFile();
        imageFile.setPhoto(photo);
        imageFile.setContent(multipartFile.getBytes());
        return imageFileRepo.save(imageFile);
    }

    //Update an existing imageFile
    public ImageFile updateImageFile(Photo photo, MultipartFile multipartFile, Integer id) throws IOException {
        ImageFile imageFile = imageFileRepo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        imageFile.setPhoto(photo);
        imageFile.setContent(multipartFile.getBytes());
        return imageFileRepo.save(imageFile);
    }
}
