package com.example.finalproject.repositories;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.finalproject.model.Category;
import com.example.finalproject.model.Product;
import com.example.finalproject.network.Api;
import com.example.finalproject.network.RetrofitInstance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoriesRepository {

    private static CategoriesRepository mInstance;
    private Context mContext ;

    private MutableLiveData<List<Category>> mCategories = new MutableLiveData<>();
    private MutableLiveData<List<Category>> mParentCategories = new MutableLiveData<>() ;

    private CategoriesRepository(Context mContext) {
        this.mContext = mContext;
    }

    public static CategoriesRepository getInstance(Context context) {
        if (mInstance == null)
            mInstance = new CategoriesRepository(context);

        return mInstance;
    }

    public MutableLiveData<List<Category>> getAllCategories() {
        return mCategories;
    }

    public MutableLiveData<List<Category>> getParentCategories() {
        return mParentCategories;
    }

    public void loadCategoriesList() throws IOException {
        mCategories.postValue(RetrofitInstance.getRetrofit().create(Api.class)
                .getAllCategories().execute().body());
    }

    public void generateParentList() {
        List list = new ArrayList();
        for (Category category : mCategories.getValue()) {
            if (category.getParent() == 0)
                list.add(category);
        }
        mParentCategories.postValue(list);
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
}
