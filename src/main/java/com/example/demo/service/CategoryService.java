package com.example.demo.service;

import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    //Get all Categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    //Search Category by id
    public Category getCategoryById(Integer id) throws CategoryNotFoundException {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category with id " + id + " does not exists"));
    }

    //Create a new Category
    public void createCategory(Category category) {
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        categoryRepository.save(newCategory);
    }

    //Update an existing Category
    public void updateCategory(Integer id, Category category) throws CategoryNotFoundException {
        Category categoryToUpdate = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category with id " + id + " does not exists"));
        categoryToUpdate.setId(category.getId());
        categoryToUpdate.setName(category.getName());
        categoryRepository.save(categoryToUpdate);
    }

    //Delete a category
    public void deleteCategory(Integer id) throws CategoryNotFoundException {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category with id " + id + " does not exists"));
        categoryRepository.delete(category);
    }
}
