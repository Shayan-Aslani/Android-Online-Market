package com.example.finalproject.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.finalproject.R;

public class CategoryListActivity extends AppCompatActivity {

    public static final String CATEGORY_ID_EXTRA = "categoryIdExtra";
    private int currentCategoryId ;

    public static Intent newIntent(Context context , int categoryId){

        Intent intent = new Intent(context, CategoryListActivity.class);
        intent.putExtra(CATEGORY_ID_EXTRA , categoryId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        currentCategoryId = getIntent().getIntExtra(CATEGORY_ID_EXTRA ,0 );
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.category_List_Frame_Layout , CategoryListFragment.newInstance(currentCategoryId))
                .commit();

    }



}
