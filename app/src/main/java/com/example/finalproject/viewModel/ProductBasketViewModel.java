package com.example.finalproject.viewModel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.finalproject.model.CartProduct;
import com.example.finalproject.repositories.ProductRepository;

import java.util.List;

public class ProductBasketViewModel extends AndroidViewModel {

    private MutableLiveData<List<CartProduct>> cartProductBasketList ;


    public ProductBasketViewModel(@NonNull Application application) {
        super(application);
        cartProductBasketList = ProductRepository.getInstance(application).getBasketProducts() ;
    }

    public MutableLiveData<List<CartProduct>> getCartProductBasketList() {
        return cartProductBasketList;
    }

    public String totalBasketPrice(){
        double totalPrice = 0 ;

        for(CartProduct cartProduct : cartProductBasketList.getValue())
        {
            totalPrice += Double.valueOf(cartProduct.getPrice());
        }
        return Double.toString(totalPrice);
    }
}
