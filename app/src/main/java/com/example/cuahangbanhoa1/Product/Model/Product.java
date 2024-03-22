package com.example.cuahangbanhoa1.Product.Model;

import android.net.Uri;

import java.net.URI;

public class Product {
    private String name;
    private String description;
    private String imageId;
    private double price;
    private Uri image;

    public Product() {
    }

    public Product(String name, String description, String imageId, double price) {
        this.name = name;
        this.description = description;
        this.imageId = imageId;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }
}
