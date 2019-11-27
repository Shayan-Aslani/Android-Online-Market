package com.example.finalproject;

import com.example.finalproject.model.Product;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    private static Repository mInstance;
    private List<Product> newProducts;
    private List<Product> ratedProducts;
    private List<Product> visitedProducts;
    private List<Product> allProducts;

    private Repository() {
        newProducts = new ArrayList<>();
        allProducts = new ArrayList<>();
    }

    public static Repository getInstance() {
        if (mInstance == null)
            mInstance = new Repository();

        return mInstance;
    }

    public List<Product> getNewProducts() {
        return newProducts;
    }

    public void setNewProducts(List<Product> newProducts) {
        this.newProducts = newProducts;
    }

    public List<Product> getRatedProducts() {
        return ratedProducts;
    }

    public void setRatedProducts(List<Product> ratedProducts) {
        this.ratedProducts = ratedProducts;
    }

    public List<Product> getVisitedProducts() {
        return visitedProducts;
    }

    public void setVisitedProducts(List<Product> visitedProducts) {
        this.visitedProducts = visitedProducts;
    }

    public List<Product> getAllProducts() {
        return allProducts;
    }

    public void setAllProducts(List<Product> allProducts) {
        this.allProducts = allProducts;
    }

    public Product getProductById(int id) {
        for (Product product : allProducts) {
            if (id == product.getId())
                return product;
        }

        return null;
    }



}
