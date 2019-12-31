package com.example.finalproject.controller.activity;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.finalproject.view.CategoryDetailFragment;

import static com.example.finalproject.controller.activity.CategoryListActivity.CATEGORY_ID_EXTRA;

public class CategoryDetailActivity extends SingleFragmentActivity {

    private int categoryId ;

    public static Intent newIntent(Context context , int categoryId){
        Intent intent = new Intent(context, CategoryDetailActivity.class);
        intent.putExtra(CATEGORY_ID_EXTRA , categoryId);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return CategoryDetailFragment.newInstance(categoryId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        categoryId = getIntent().getIntExtra(CATEGORY_ID_EXTRA , 0);
        super.onCreate(savedInstanceState);
    }
}
