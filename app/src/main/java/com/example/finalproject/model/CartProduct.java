package com.example.finalproject.model;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartProduct {

    public CartProduct(String name, int id, List<Product.Images> images, String price, String short_description) {
        this.name = name;
        this.id = id;
        this.images = images;
        this.price = price;
        this.short_description = short_description;
    }

    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("images")
    private List<Product.Images> images;

    @Expose
    @SerializedName("price")
    private String price;

    @Expose
    @SerializedName("short_description")
    private String short_description;

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
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
