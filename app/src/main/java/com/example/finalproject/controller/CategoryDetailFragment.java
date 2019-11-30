package com.example.finalproject.controller;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.R;
import com.example.finalproject.model.Category;
import com.example.finalproject.model.Product;
import com.example.finalproject.model.Repository;
import com.example.finalproject.network.Api;
import com.example.finalproject.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryDetailFragment extends Fragment {


    public static final String CATEGORY_ID_ARG = "categoryIdArg";

    private int categoryid;
    private RecyclerView latestProductsRecyclerView, popularProductsRecyclerView;
    private TextView categoryTitleTextView ;
    private Api api;
    private ProductAdapter latestProductsAdapter, popularProductsAdapter;
    private ProgressBar progressBar;
    private List<Product> latestProductList , popularProductList ;
    private Category mCategory ;

    public static CategoryDetailFragment newInstance(int categoryid) {

        Bundle args = new Bundle();
        args.putInt(CATEGORY_ID_ARG , categoryid);
        CategoryDetailFragment fragment = new CategoryDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CategoryDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryid = getArguments().getInt(CATEGORY_ID_ARG);
        mCategory = Repository.getInstance().getCategoryById(categoryid);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_category_detail, container, false);

        initUi(view);
        setDetail();
        setupRecyclerViews();

        api = RetrofitInstance.getRetrofit().create(Api.class);
        api.getProductsSubCategoires(String.valueOf(categoryid) , "popularity").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    popularProductList = response.body();
                    popularProductsAdapter.setProducts(popularProductList);
                    popularProductsAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("network", "onFailure: " + t.getMessage());
                Toast.makeText(getActivity(), "network Failure", Toast.LENGTH_SHORT).show();
            }
        });
        api.getProductsSubCategoires(String.valueOf(categoryid) , "date").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    latestProductList = response.body();
                    latestProductsAdapter.setProducts(latestProductList);
                    latestProductsAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("network", "onFailure: " + t.getMessage());
                Toast.makeText(getActivity(), "network Failure", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void initUi(View view){
        latestProductsRecyclerView = view.findViewById(R.id.lateest_Products_RecyclerView_Detail);
        popularProductsRecyclerView = view.findViewById(R.id.popular_Products_RecyclerView_Detail);
        categoryTitleTextView = view.findViewById(R.id.caegory_title_textView_detail);
        progressBar = view.findViewById(R.id.progressBar_Category_Detail);
    }

    private void setDetail(){
        categoryTitleTextView.setText(mCategory.getName());
    }

    private void setupRecyclerViews() {
        latestProductsAdapter = new ProductAdapter((AppCompatActivity) getActivity());
        popularProductsAdapter = new ProductAdapter((AppCompatActivity) getActivity());
        latestProductsRecyclerView.setAdapter(latestProductsAdapter);
        popularProductsRecyclerView.setAdapter(popularProductsAdapter);
    }

}
