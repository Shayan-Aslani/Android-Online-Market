package com.example.finalproject.model;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    private static Repository mInstance;
    private List<Product> newProducts;
    private List<Product> ratedProducts;
    private List<Product> visitedProducts;
    private List<Product> allProducts;
    private List<Category> allCategories;
    private List<Category> parentCategories ;
    private MutableLiveData<List<CartProduct>> shoppingCartProducts = new MutableLiveData<>();

    private Repository() {
        newProducts = new ArrayList<>();
        allProducts = new ArrayList<>();
        allCategories = new ArrayList<>();
        parentCategories = new ArrayList<>();
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

    public List<Category> getAllCategories() {
        return allCategories;
    }

    public List<Category> getParentCategories() {
        return parentCategories;
    }

    public void setAllCategories(List<Category> allCategories) {
        this.allCategories = allCategories;
        generateParentList();
    }


    public Product getProductById(int id) {
        for (Product product : allProducts) {
            if (id == product.getId())
                return product;
        }

        return null;
    }

    public List<Category> getSubCategoires(long parentId) {
        List<Category> result = new ArrayList<>();

        for (Category category : allCategories) {
            if (category.getParent() == parentId)
                result.add(category);
        }
        return result;
    }

    public Category getCategoryById(int id) {
        for (Category category : allCategories)
            if (category.getId() == id)
                return category;

        return null;
    }

    private void generateParentList() {
        for (Category category : allCategories) {
            if (category.getParent() == 0)
                parentCategories.add(category);
        }
    }

    public MutableLiveData<List<CartProduct>> getShoppingCartProducts() {
        return shoppingCartProducts;
    }

    public void deleteCartproduct(CartProduct cartProduct){
        List<CartProduct> list = shoppingCartProducts.getValue();
        list.remove(cartProduct);
        shoppingCartProducts.setValue(list);
    }

    public CartProduct convertToCartProduct(Product product){
        return new CartProduct(product.getName() , product.getId() , product.getImages() , product.getPrice() , product.getShort_description());
    }
}
