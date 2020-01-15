package com.example.finalproject.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.finalproject.model.Category;
import com.example.finalproject.model.Product;
import com.example.finalproject.repositories.CategoriesRepository;
import com.example.finalproject.repositories.FilterRepository;
import com.example.finalproject.repositories.ProductRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainFragmentViewModel extends AndroidViewModel {

    private MutableLiveData<List<Product>> mNewProductList;
    private MutableLiveData<List<Product>> mRatedProducts ;
    private MutableLiveData<List<Product>> mVisitedProducts ;
    private MutableLiveData<List<Product>> mVipProducts ;
    private MutableLiveData<List<Category>> mCategories ;
    private ProductRepository mProductRepository;
    private CategoriesRepository mCategoriesRepository ;
    private FilterRepository mFilterRepository ;

    public MainFragmentViewModel(@NonNull Application application) {
        super(application);
        mProductRepository = ProductRepository.getInstance(application);
        mCategoriesRepository = CategoriesRepository.getInstance(application);
        mFilterRepository = FilterRepository.getInstance(application);
        mFilterRepository.getSelectedTerms().setValue(new ArrayList<>());
        mNewProductList = mProductRepository.getNewProducts();
        mRatedProducts = mProductRepository.getRatedProducts();
        mVisitedProducts = mProductRepository.getVisitedProducts();
        mVipProducts = mProductRepository.getVipProducts();
        mCategories = mCategoriesRepository.getAllCategories();
        mProductRepository.loadBasketProducts();
    }

    public MutableLiveData<List<Product>> getNewProductList() {
        return mNewProductList;
    }

    public MutableLiveData<List<Product>> getRatedProductList() {
        return mRatedProducts;
    }

    public MutableLiveData<List<Product>> getVisitedProductList() {
        return mVisitedProducts;
    }

    public MutableLiveData<List<Product>> getVipProducts(){return mVipProducts ;}

    public MutableLiveData<List<Category>> getCategoriesList() {
        return mCategories;
    }

    public void loadNewProductListFromApi() throws IOException {
        mProductRepository.loadNewProductList();
    }

    public void loadRatedProductListFromApi() throws IOException {
        mProductRepository.loadRatedProductList();
    }

    public void loadVisitedProductListFromApi() throws IOException {
        mProductRepository.loadVisitedProductList();
    }

    public void loadCategoriesListFromApi() throws IOException {
         mCategoriesRepository.loadCategoriesList();
    }

    public void loadAttributesFromApi() throws IOException {
        mFilterRepository.loadAttributesFromApi();
    }

    public void loadAttributeTermsFromApi() throws IOException {
        mFilterRepository.loadAttributeTerms();
    }
}
