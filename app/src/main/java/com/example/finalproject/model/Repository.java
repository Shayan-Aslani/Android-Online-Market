package com.example.finalproject.model;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.finalproject.Utils.ShoppingCartPreferences;
import com.example.finalproject.controller.fragment.ShoppingBagFragment;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    private static Repository mInstance;
    private List<Product> allProducts;
    private List<Category> parentCategories ;
    private List<Attribute> allAttributes ;
    private List<Product> vipProducts ;
    private Context mContext;

    private List<Attribute.Term> selectedTerms = new ArrayList<>();

    private MutableLiveData<List<CartProduct>> shoppingCartProducts = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mNewProducts = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mRatedProducts = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mVisitedProducts = new MutableLiveData<>();
    private MutableLiveData<List<Category>> mCategories = new MutableLiveData<>();

    private Repository(Context context) {
        mContext = context ;
        allProducts = new ArrayList<>();
        parentCategories = new ArrayList<>();
    }

    public static Repository getInstance(Context context) {
        if (mInstance == null)
            mInstance = new Repository(context);

        return mInstance;
    }




    public void loadShoppingBagProducts(){
        List<CartProduct> list = ShoppingCartPreferences.getProductList(mContext);
        shoppingCartProducts.setValue(list);
    }

    public void saveShoppingBagProducts(){
        ShoppingCartPreferences.setProductList(mContext , shoppingCartProducts.getValue());
    }


    public List<Product> getAllProducts() {
        return allProducts;
    }

    public void setAllProducts(List<Product> allProducts) {
        this.allProducts = allProducts;
    }

    public MutableLiveData<List<Category>> getAllCategories() {
        return mCategories;
    }

    public List<Category> getParentCategories() {
        return parentCategories;
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

        for (Category category : mCategories.getValue()) {
            if (category.getParent() == parentId)
                result.add(category);
        }


        return result;
    }

    public Category getCategoryById(int id) {
        for (Category category : mCategories.getValue())
            if (category.getId() == id)
                return category;

        return null;
    }

    public void generateParentList() {
        for (Category category : mCategories.getValue()) {
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

    public List<Product> getVipProducts() {
        return vipProducts;
    }

    public void setVipProducts(List<Product> vipProducts) {
        this.vipProducts = vipProducts;
    }


    public List<Attribute.Term> getSelectedTerms() {
        return selectedTerms;
    }

    public void setSelectedTerms(List<Attribute.Term> selectedTerms) {
        this.selectedTerms = selectedTerms;
    }

    public List<Attribute> getAllAttributes() {
        return allAttributes;
    }

    public void setAllAttributes(List<Attribute> allAttributes) {
        this.allAttributes = allAttributes;
    }

    public void addSelectedTerm(Attribute.Term term)
    {
        selectedTerms.add(term);
    }

    public void removeSelectedTerm(Attribute.Term term)
    {
        selectedTerms.remove(term);
    }

    public MutableLiveData<List<Product>> getNewProducts() {
        return mNewProducts;
    }

    public MutableLiveData<List<Product>> getRatedProducts() {
        return mRatedProducts;
    }

    public MutableLiveData<List<Product>> getVisitedProducts() {
        return mVisitedProducts;
    }
}
