package com.example.demo.model;

import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

public class ImageForm {
    private MultipartFile multipartFile;

    @Valid
    private Photo photo;

    //GETTERS
    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public Photo getPhoto() {
        return photo;
    }

    //SETTERS
    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}
