package com.example.finalproject.controller;

import androidx.fragment.app.Fragment;

import android.os.Bundle;

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
