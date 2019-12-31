package com.example.finalproject.Utils;

import com.example.finalproject.model.CartProduct;
import com.example.finalproject.model.Product;

public class ProductBasketConverter {

    public static CartProduct convertToCartProduct(Product product) {
        return new CartProduct(product.getName(), product.getId(), product.getImages(), product.getPrice(), product.getDescription());
    }

    public static Product convertToProduct(CartProduct cartProduct) {
        Product product =  new Product();
        product.setName(cartProduct.getName());
        product.setImages(cartProduct.getImages());
        product.setDescription(cartProduct.getDescription());
        product.setPrice(cartProduct.getPrice());

        return product ;
    }

}
