package com.example.finalproject.controller;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.finalproject.R;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends SingleFragmentActivity {

    private MainFragment mainFragment ;
    public static final String RESULT_EXTRA = "networkResultExtra";

    public static Intent newIntent(Context context , boolean result ){

        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(RESULT_EXTRA , result);
        return intent;
    }

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
        View view = findViewById(R.id.fragment_container);
  /*      if(!getIntent().getExtras().getBoolean(RESULT_EXTRA))
            Snackbar.make(view , "f" , Snackbar.LENGTH_LONG);

   */

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
