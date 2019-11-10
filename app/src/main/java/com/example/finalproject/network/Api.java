package com.example.finalproject.network;

import com.example.finalproject.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Api {


    @GET("products")
    Call<List<Product>> getAllProducts();


    @GET("products/{id}/?")
    Call<Product> getProduct(@Path("id") String productId);
    @GET("products/?")
    Call<List<Product>> getProductsSubCategoires(@Query("page") String pageNumber, @Query("category") String categoryId
            , @Query("orderby") String orderBy, @Query("order") String order, @Query("attribute_term") String... attributes);

    @GET("products/?")
    Call<List<Product>> searchProducts(@Query("page") String pageNumber, @Query("search") String productName
            , @Query("orderby") String orderBy, @Query("order") String order, @Query("attribute_term") String... attributes);


    @GET("products/?")
    Call<List<Product>> getReleatedProducts(@Query("include") String... releateds);


    @GET("products/?")
    Call<List<Product>> getAllProductWithPage(@Query("page") String pageNumber, @Query("orderby") String orderBy, @Query("order") String order,
                                              @Query("attribute_term") String... attributes);



}
