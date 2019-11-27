package com.example.finalproject.controller;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;

import com.example.finalproject.R;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar =  findViewById(R.id.toolbar);
       setSupportActionBar(myToolbar);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_frame_layout , MainFragment.newInstance())
                .commit();
    }

    @Override
    public void onBackPressed() {
       /* if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        */
    }


}
