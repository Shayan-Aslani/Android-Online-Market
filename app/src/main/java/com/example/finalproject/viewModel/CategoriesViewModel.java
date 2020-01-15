package com.example.finalproject.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalproject.model.Category;
import com.example.finalproject.model.Product;
import com.example.finalproject.repositories.CategoriesRepository;

import java.io.IOException;
import java.util.List;

public class CategoriesViewModel extends AndroidViewModel {

    private CategoriesRepository mRepository ;

    private MutableLiveData<Category> mCategory = new MutableLiveData<>() ;
    private MutableLiveData<List<Product>> mNewProducts ;
    private MutableLiveData<List<Product>> mRatedProducts ;
    private MutableLiveData<List<Category>> mSubCategories ;

    public CategoriesViewModel(@NonNull Application application) {
        super(application);
        mRepository = CategoriesRepository.getInstance(application);
        mNewProducts = mRepository.getNewProducts() ;
        mRatedProducts = mRepository.getRatedProducts() ;
    }

    public MutableLiveData<Category> getCategory() {
        return mCategory;
    }

    public MutableLiveData<List<Product>> getNewProducts() {
        return mNewProducts;
    }

    public MutableLiveData<List<Product>> getRatedProducts() {
        return mRatedProducts;
    }

    public List<Category> getSubCategories(int parentId) {
        return mRepository.getSubCategoires(parentId);}

    public void loadCategoryById(int categoryId){
        mCategory.setValue(mRepository.getCategoryById(categoryId));
    }

    public void loadNewProductListFromApi(int categoryid) {
        mRepository.loadNewProductList(categoryid);
    }

    public void loadRatedProductListFromApi(int categoryid){
        mRepository.loadRatedProductList(categoryid);
    }
}
