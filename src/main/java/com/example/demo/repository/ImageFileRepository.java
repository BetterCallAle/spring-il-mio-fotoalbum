package com.example.demo.repository;

import com.example.demo.model.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageFileRepository extends JpaRepository<ImageFile, Integer> {
}
