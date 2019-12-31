package com.example.finalproject.controller.activity;

import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.finalproject.view.StartFragment;

public class StartActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return StartFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
