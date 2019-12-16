package com.example.finalproject.controller.activity;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.finalproject.controller.fragment.ProductDetailFragment;

public class productDetailActivity extends SingleFragmentActivity {


    public static final String PRODUCT_ID_EXTRA = "productId";
    private int mProductId ;
    public static Intent newIntent(Context context , int productId){

        Intent intent = new Intent(context, productDetailActivity.class);
        intent.putExtra(PRODUCT_ID_EXTRA , productId);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return ProductDetailFragment.newInstance(mProductId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mProductId = getIntent().getIntExtra(PRODUCT_ID_EXTRA , 0);
        super.onCreate(savedInstanceState);
    }
}
