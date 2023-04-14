package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il titolo è obbligatorio")
    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private boolean visible;

    @NotBlank(message = "URL obbligatorio")
    @Column(columnDefinition = "TEXT", nullable = false)
    private String imgUrl;
    @ManyToMany
    @JoinTable(
            name = "category_photo",
            joinColumns = @JoinColumn(name = "photo_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

    //GETTERS
    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean getVisible() {
        return visible;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    //SETTERS
    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    //EQUALS AND HASHCODE
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        if (getVisible() != photo.getVisible()) return false;
        if (getId() != null ? !getId().equals(photo.getId()) : photo.getId() != null) return false;
        if (getTitle() != null ? !getTitle().equals(photo.getTitle()) : photo.getTitle() != null) return false;
        if (getDescription() != null ? !getDescription().equals(photo.getDescription()) : photo.getDescription() != null)
            return false;
        if (getImgUrl() != null ? !getImgUrl().equals(photo.getImgUrl()) : photo.getImgUrl() != null) return false;
        return getCategories() != null ? getCategories().equals(photo.getCategories()) : photo.getCategories() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getVisible() ? 1 : 0);
        result = 31 * result + (getImgUrl() != null ? getImgUrl().hashCode() : 0);
        result = 31 * result + (getCategories() != null ? getCategories().hashCode() : 0);
        return result;
    }
}
