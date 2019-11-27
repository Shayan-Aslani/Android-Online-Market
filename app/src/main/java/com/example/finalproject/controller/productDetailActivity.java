package com.example.finalproject.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.finalproject.R;

public class productDetailActivity extends AppCompatActivity {


    public static final String PRODUCT_ID_EXTRA = "productId";
    private int mProductId ;
    public static Intent newIntent(Context context , int productId){

        Intent intent = new Intent(context, productDetailActivity.class);
        intent.putExtra(PRODUCT_ID_EXTRA , productId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        mProductId = getIntent().getIntExtra(PRODUCT_ID_EXTRA , 0);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.product_detail_activity_frame_layout , ProductDetailFragment.newInstance(mProductId))
                .commit();
    }
}
