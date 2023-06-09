package com.example.demo.controller;

import com.example.demo.exceptions.PhotoNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.model.ImageFile;
import com.example.demo.model.ImageForm;
import com.example.demo.model.Photo;
import com.example.demo.repository.ImageFileRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ImageFileService;
import com.example.demo.service.PhotoService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/photos")
public class PhotoController {
    @Autowired
    public PhotoService photoService;
    @Autowired
    public CategoryService categoryService;

    @Autowired
    public ImageFileService imageFileService;

    //INDEX
    @GetMapping
    public String index(@RequestParam(name = "s") Optional<String> s, Model model) {
        List<Photo> photos;
        if (s.isEmpty()) {
            photos = photoService.getAllPhotos();
        } else {
            photos = photoService.findPhotoByName(s.get());
        }
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

    //CREATE
    @GetMapping("/create")
    public String create(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("imageForm", new ImageForm());
        model.addAttribute("categories", categories);
        return "photo/create";
    }

    //STORE
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute ImageForm imageForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            List<Category> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            return "/photo/create";
        }

        Photo photoSave = photoService.createPhoto(imageForm.getPhoto());

        try{
            imageFileService.createImageFile(photoSave, imageForm.getMultipartFile());
        } catch (IOException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        redirectAttributes.addFlashAttribute("success", "La foto " + imageForm.getPhoto().getTitle() + " è stata creata con successo");
        return "redirect:/photos";
    }

    //EDIT
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        try {
            Photo photo = photoService.findPhoto(id);
            ImageForm imageForm = new ImageForm();
            imageForm.setPhoto(photo);
            model.addAttribute("imageForm", imageForm);
            List<Category> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            return "photo/edit";
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    //UPDATE
    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute ImageForm imageForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            List<Category> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            return "photo/edit";
        }

        try {
            Photo updatedPhoto = photoService.updatePhoto(imageForm.getPhoto(), id);
            imageFileService.updateImageFile(updatedPhoto, imageForm.getMultipartFile(), updatedPhoto.getImageFile().getId());
            redirectAttributes.addFlashAttribute("success", "La foto " + imageForm.getPhoto().getTitle() + " è stata aggiornata con successo");
            return "redirect:/photos";
        } catch (PhotoNotFoundException | ResponseStatusException | IOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    //DELETE
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            redirectAttributes.addFlashAttribute("success", "La foto " + photoService.findPhoto(id).getTitle() + " è stata eliminata con successo");
            photoService.deletePhoto(id);
            return "redirect:/photos";
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
