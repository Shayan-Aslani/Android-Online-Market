package com.example.finalproject.controller.fragment;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.finalproject.R;
import com.example.finalproject.controller.activity.MainActivity;
import com.example.finalproject.model.Product;
import com.example.finalproject.model.Repository;
import com.example.finalproject.network.Api;
import com.example.finalproject.network.RetrofitInstance;
import com.google.android.material.button.MaterialButton;

import java.io.IOException;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment extends Fragment {


    private LottieAnimationView mInternetAnimationView;
    private MaterialButton tryAgainButton ;
    private ProgressBar progressBar ;
    public static StartFragment newInstance() {

        Bundle args = new Bundle();

        StartFragment fragment = new StartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public StartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_start, container, false);

        initUi(view);

        startInit();

        tryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startInit();
            }
        });
        return view;
    }

    private void onNetworkUnavailable(){
        tryAgainButton.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void startInit(){
        if(isNetworkAvailable()){
            tryAgainButton.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            new InitProductsAsynceTask().execute();
        }
        else
            onNetworkUnavailable();

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void initUi(View view){
        tryAgainButton = view.findViewById(R.id.tryagain_Button);
        progressBar = view.findViewById(R.id.progressBar2);
    }


    private class InitProductsAsynceTask extends AsyncTask<Void, String, Void> {

        private Boolean result  = true ;

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Repository.getInstance().setNewProducts(generateLists("date"));
                Repository.getInstance().setAllProducts(generateLists("date"));
                Repository.getInstance().setRatedProducts(generateLists("rating"));
                Repository.getInstance().setVisitedProducts(generateLists("popularity"));
               Repository.getInstance().setAllCategories(RetrofitInstance.getRetrofit().create(Api.class)
                       .getAllCategories().execute().body());
            } catch (IOException e) {
                e.printStackTrace();
                publishProgress("خطا در دریافت اطلاعات از دیجی کالا");
            }


            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            String toastString = values[0];
            Toast.makeText(getActivity(), toastString, Toast.LENGTH_SHORT).show();
            result = false;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            startActivity(MainActivity.newIntent(getActivity() , result));
            getActivity().finish();
        }
    }

    private List<Product> generateLists(String type) throws IOException {
        return RetrofitInstance.getRetrofit().create(Api.class)
                .getAllProducts(type , "100").execute().body();
    }

}
