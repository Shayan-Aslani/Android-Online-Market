package com.example.finalproject.controller;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Build;
import android.os.Bundle;

import com.example.finalproject.R;

public class MainActivity extends SingleFragmentActivity {

    private MainFragment mainFragment ;

    @Override
    public Fragment createFragment() {
        return MainFragment.newInstance();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      /*  Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
       */
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if(fragment instanceof MainFragment)
            mainFragment = (MainFragment) fragment;

        if(mainFragment == null)
            super.onBackPressed();
        else if(!mainFragment.closeDrawer())
            super.onBackPressed();
    }


}
