package com.example.demo.controller;

import com.example.demo.model.ImageFile;
import com.example.demo.repository.ImageFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/file")
public class ImageController {

    @Autowired
    private ImageFileRepository imageFileRepo;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> serveImage(@PathVariable Integer id){
        ImageFile img = imageFileRepo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        MediaType mediaType = MediaType.IMAGE_PNG;
        return ResponseEntity.ok().contentType(mediaType).body(img.getContent());
    }
}
