package com.example.demo.api;

import com.example.demo.model.ImageFile;
import com.example.demo.repository.ImageFileRepository;
import com.example.demo.service.ImageFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping("/api/images")
public class ImageRestController {
    @Autowired
    private ImageFileRepository imageFileRepo;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> serveImage(@PathVariable Integer id){
        ImageFile img = imageFileRepo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        MediaType mediaType = MediaType.IMAGE_PNG;
        return ResponseEntity.ok().contentType(mediaType).body(img.getContent());
    }
}
