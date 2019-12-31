package com.example.finalproject.Utils;

import com.example.finalproject.model.CartProduct;
import com.example.finalproject.model.Product;

public class ProductBasketConverter {

    public static CartProduct convertToCartProduct(Product product) {
        return new CartProduct(product.getName(), product.getId(), product.getImages(), product.getPrice(), product.getShort_description());
    }

}
