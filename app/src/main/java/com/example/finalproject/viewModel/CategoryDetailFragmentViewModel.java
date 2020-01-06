package com.example.finalproject.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.finalproject.model.Product;
import com.example.finalproject.repositories.CategoriesRepository;

import java.io.IOException;
import java.util.List;

public class CategoryDetailFragmentViewModel extends AndroidViewModel {

    private CategoriesRepository mRepository ;
    private MutableLiveData<List<Product>> mNewProducts = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mRatedProducts = new MutableLiveData<>();

    public CategoryDetailFragmentViewModel(@NonNull Application application) {
        super(application);
        mRepository = CategoriesRepository.getInstance(application);
        mNewProducts = mRepository.getNewProducts() ;
        mRatedProducts = mRepository.getRatedProducts() ;
    }

    public MutableLiveData<List<Product>> getNewProducts() {
        return mNewProducts;
    }

    public MutableLiveData<List<Product>> getRatedProducts() {
        return mRatedProducts;
    }

    public void loadNewProductListFromApi(int categoryid) {
        mRepository.loadNewProductList(categoryid);
    }

    public void loadRatedProductListFromApi(int categoryid){
        mRepository.loadRatedProductList(categoryid);
    }
}
