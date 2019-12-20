package com.example.finalproject.network;

import androidx.annotation.Nullable;

import com.example.finalproject.model.Attribute;
import com.example.finalproject.model.Category;
import com.example.finalproject.model.Product;

import org.w3c.dom.Attr;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Api {


    @GET("products")
    Call<List<Product>> getAllProducts(@Query("orderby") String type , @Query("per_page") String perpage);


    @GET("products/{id}")
    Call<Product> getProduct(@Path("id") String productId);

    @GET("products/categories/?per_page=100")
    Call<List<Category>> getAllCategories();

    @GET("products")
    Call<List<Product>> getProductsSubCategoires(@Query("category") String categoryId , @Query("orderby") String orderBy);

    @GET("products")
    Call<List<Product>> searchProducts(@Query("search") String productName);

    @GET("products")
    Call<List<Product>> getFilteredProducts(@Query("search") String search ,@Query("attribute") String attribute, @Query("attribute_term") String terms);

    @GET("products/attributes")
    Call<List<Attribute>> getAttributes();

    @GET("products/attributes/{id}/terms")
    Call<List<Attribute.Term>> getTerms(@Path("id") String id);


}
