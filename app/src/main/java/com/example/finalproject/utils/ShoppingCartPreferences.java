package com.example.finalproject.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.finalproject.model.CartProduct;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartPreferences {

    private static final String PREF_NAME = "Digikala";

    private static final String PREF_PRODUCT_ID = "query";

    private static SharedPreferences getShoppingCartSharedPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static List<CartProduct> getProductList(Context context) {
        SharedPreferences prefs = getShoppingCartSharedPreferences(context);

        if (prefs == null)
            return null;

        List<CartProduct> cartProductList = new ArrayList<>();
        Type type = new TypeToken<List<CartProduct>>() {}.getType();
        Gson gson = new Gson();
        String jsonText =  prefs.getString(PREF_PRODUCT_ID, null) ;
        if(gson.fromJson(jsonText , type) instanceof  List)
            cartProductList = gson.fromJson(jsonText, type);

        return cartProductList;
    }

    public static void setProductList(Context context, List<CartProduct> cartProductList) {
        SharedPreferences prefs = getShoppingCartSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String jsonText = gson.toJson(cartProductList);
        editor.putString(PREF_PRODUCT_ID, jsonText);
        editor.apply();
    }


}

