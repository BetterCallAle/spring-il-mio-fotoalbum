package com.example.demo.controller;

import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoriesController {
    @Autowired
    CategoryService categoryService;

    //INDEX
    @GetMapping
    public String index(Model model, @RequestParam(name = "id") Optional<Integer> id) {
        //Show all categories
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        //For create and update
        Category category;
        if (id.isEmpty()) {
            category = new Category();
            model.addAttribute("categoryToCreate", category);
        } else {
            try {
                category = categoryService.getCategoryById(id.get());
                model.addAttribute("categoryToUpdate", category);
            } catch (CategoryNotFoundException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }

        return "category/index";
    }

    //STORE
    @PostMapping("/create")
    public String store(@ModelAttribute Category category) {
        categoryService.createCategory(category);
        return "redirect:/categories";
    }

    //UPDATE
    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute Category category) {
        try {
            categoryService.updateCategory(id, category);
            return "redirect:/categories";
        } catch (CategoryNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    //DELETE
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        try {
            categoryService.deleteCategory(id);
            return "redirect:/categories";
        } catch (CategoryNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
