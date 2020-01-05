package com.example.finalproject.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.finalproject.model.Attribute;
import com.example.finalproject.model.Product;
import com.example.finalproject.network.Api;
import com.example.finalproject.network.RetrofitInstance;
import com.example.finalproject.repositories.FilterRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListFragmentViewModel extends AndroidViewModel {

    private MutableLiveData<List<Product>> mProductList = new MutableLiveData<>() ;
    private MutableLiveData<List<Attribute.Term>> mSelectedTerms ;
    private FilterRepository mFilterRepository ;

    public ProductListFragmentViewModel(@NonNull Application application) {
        super(application);
        mSelectedTerms = FilterRepository.getInstance(application).getSelectedTerms() ;
        mFilterRepository = FilterRepository.getInstance(application);
    }

    public MutableLiveData<List<Product>> getProductList() {
        return mProductList;
    }

    public MutableLiveData<List<Attribute.Term>> getSelectedTerms() {
        return mSelectedTerms;
    }

    public void setEmptySelectedTerm(){
       mFilterRepository.getSelectedTerms().setValue(new ArrayList<>());
    }

    public void loadFilteredListFromApi(){
        loadFilteredListFromApi(null , null , null , 1);
    }

    public void loadFilteredListFromApi(String searchText , String orderBy, String orderType , int page){
        String filter = "";
        String attribute = "";
        if(mSelectedTerms.getValue().size() != 0) {
            attribute = mSelectedTerms.getValue().get(0).getAttributeSlug();
            for (Attribute.Term term : mSelectedTerms.getValue()) {
                filter += term.getId() + ",";
            }
        }

        RetrofitInstance.getRetrofit().create(Api.class).
                getSearchedProducts(searchText, attribute, filter , orderBy , orderType ,String.valueOf(page) , 20)
                .enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful())
                    mProductList.postValue(response.body());
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

}
