package com.example.finalproject.network;

import com.example.finalproject.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Api {


    @GET("products/?")
    Call<List<Product>> getAllProducts(@Query("orderby") String type , @Query("per_page") String perpage);


    @GET("products/{id}/?")
    Call<Product> getProduct(@Path("id") String productId);

    @GET("products/categories/?per_page=100")
    Call<List<Category>> getAllCategories();



}
