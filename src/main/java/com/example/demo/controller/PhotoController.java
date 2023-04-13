package com.example.demo.controller;

import com.example.demo.exceptions.PhotoNotFoundException;
import com.example.demo.model.Photo;
import com.example.demo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/photos")
public class PhotoController {
    @Autowired
    public PhotoService photoService;

    //INDEX
    @GetMapping
    public String index(Model model) {
        List<Photo> photos = photoService.getAllPhotos();
        model.addAttribute("photos", photos);
        return "photo/index";
    }

    //SHOW
    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        try {
            Photo photo = photoService.findPhoto(id);
            model.addAttribute("photo", photo);
            return "photo/show";
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
