package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "image_files")
public class ImageFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    @Column(length = 16777215)
    private byte[] content;

    @OneToOne
    private Photo photo;

    //GETTERS
    public Integer getId() {
        return id;
    }

    public byte[] getContent() {
        return content;
    }

    public Photo getPhoto() {
        return photo;
    }

    //SETTERS
    public void setId(Integer id) {
        this.id = id;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}
