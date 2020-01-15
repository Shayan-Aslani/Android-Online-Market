package com.example.finalproject.model;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartProduct {

    public CartProduct(String name, int id, List<Product.Images> images, String price, String description) {
        this.name = name;
        this.id = id;
        this.images = images;
        this.price = price;
        this.description = description;
    }

    private String name;
    private int id;
    private List<Product.Images> images;
    private String price;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product.Images> getImages() {
        return images;
    }

    public void setImages(List<Product.Images> images) {
        this.images = images;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
