package com.example.finalproject.view.activity;

import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.finalproject.view.fragment.StartFragment;

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
