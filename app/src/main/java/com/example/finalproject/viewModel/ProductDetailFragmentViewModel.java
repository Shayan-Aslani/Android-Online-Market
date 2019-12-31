package com.example.finalproject.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.finalproject.Utils.ProductBasketConverter;
import com.example.finalproject.model.CartProduct;
import com.example.finalproject.model.Product;
import com.example.finalproject.repositories.ProductRepository;

import java.util.List;

public class ProductDetailFragmentViewModel extends AndroidViewModel {

    private MutableLiveData<Product> mProduct ;
    private MutableLiveData<List<CartProduct>> mShoppingCartList ;
    private ProductRepository mProductRepository;

    public ProductDetailFragmentViewModel(@NonNull Application application) {
        super(application);
        mProductRepository = ProductRepository.getInstance(application);
        mShoppingCartList = mProductRepository.getBasketProducts() ;
    }

    public void addProductToShoppingCart(Product product){
        CartProduct cartProduct = ProductBasketConverter.convertToCartProduct(product);
        List<CartProduct> list = mShoppingCartList.getValue() ;
        list.add(cartProduct) ;
        mShoppingCartList.postValue(list);
        mProductRepository.saveBasketProducts();
    }

    public MutableLiveData<List<CartProduct>> getShoppingCartList() {
        return mShoppingCartList;
    }


}
