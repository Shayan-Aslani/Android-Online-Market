package com.example.finalproject.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.finalproject.model.Category;
import com.example.finalproject.model.Product;
import com.example.finalproject.model.Repository;

import java.util.List;
import java.util.ListIterator;

public class MainFragmentViewModel extends AndroidViewModel {

    private MutableLiveData<List<Product>> mNewProductList;
    private MutableLiveData<List<Product>> mRatedProducts ;
    private MutableLiveData<List<Product>> mVisitedProducts ;
    private MutableLiveData<List<Category>> mCategories ;
    private Repository mRepository ;

    public MainFragmentViewModel(@NonNull Application application) {
        super(application);
        mRepository = Repository.getInstance(application);
        mRepository.loadShoppingBagProducts();
        mRepository.generateParentList();
    }

    public MutableLiveData<List<Product>> getNewProductList() {
        if(mNewProductList == null)
            mNewProductList = mRepository.getNewProducts();

        return mNewProductList;
    }

    public MutableLiveData<List<Product>> getRatedProductList() {
        if(mRatedProducts == null)
            mRatedProducts = mRepository.getRatedProducts();

        return mRatedProducts;
    }

    public MutableLiveData<List<Product>> getVisitedProductList() {
        if(mVisitedProducts == null)
            mVisitedProducts = mRepository.getVisitedProducts();

        return mVisitedProducts;
    }

    public MutableLiveData<List<Category>> getCategoriesList() {
        if(mCategories == null)
            mCategories = mRepository.getAllCategories();

        return mCategories;
    }

    public void saveShoppingCartProducts(){
        mRepository.saveShoppingBagProducts();
    }



}
