package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    List<Photo> photos;

    //GETTERS
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    //SETTERS
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
