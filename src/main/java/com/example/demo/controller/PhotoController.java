package com.example.demo.controller;

import com.example.demo.exceptions.PhotoNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.model.Photo;
import com.example.demo.service.CategoryService;
import com.example.demo.service.PhotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/photos")
public class PhotoController {
    @Autowired
    public PhotoService photoService;
    @Autowired
    public CategoryService categoryService;

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

    //CREATE
    @GetMapping("/create")
    public String create(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("photo", new Photo());
        model.addAttribute("categories", categories);
        return "photo/create";
    }

    //STORE
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute Photo photo, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            List<Category> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            return "/photo/create";
        }

        photoService.createPhoto(photo);
        redirectAttributes.addFlashAttribute("success", "La foto " + photo.getTitle() + " è stata creata con successo");
        return "redirect:/photos";
    }

    //EDIT
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        try {
            Photo photo = photoService.findPhoto(id);
            model.addAttribute("photo", photo);
            List<Category> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            return "photo/edit";
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    //UPDATE
    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute Photo photo, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            List<Category> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            return "photo/edit";
        }

        try {
            photoService.updatePhoto(photo, id);
            redirectAttributes.addFlashAttribute("success", "La foto " + photo.getTitle() + " è stata aggiornata con successo");
            return "redirect:/photos";
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
