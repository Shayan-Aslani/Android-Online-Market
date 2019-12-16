package com.example.finalproject.controller.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.example.finalproject.R;
import com.example.finalproject.controller.adapters.ProductAdapter;
import com.example.finalproject.controller.adapters.ProductListAdapter;
import com.example.finalproject.model.Product;
import com.example.finalproject.network.Api;
import com.example.finalproject.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends Fragment {

    public static final String SEARCH_STRING_ARG = "searchStringArg";
    public static final String SEARCHABLE_ARG = "searchableArg";

    private List<Product> productList ;

    private ProductListAdapter productAdapter ;
    private RecyclerView recyclerView;
    private String searchText ;
    private Boolean searchable ;

    public static ProductListFragment newInstance(String searchText , Boolean searchable) {
        
        Bundle args = new Bundle();
        args.putString(SEARCH_STRING_ARG, searchText);
        args.putBoolean(SEARCHABLE_ARG, searchable);
        ProductListFragment fragment = new ProductListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ProductListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchable = getArguments().getBoolean(SEARCHABLE_ARG);
        searchText = getArguments().getString(SEARCH_STRING_ARG);

        showSearchList(searchText);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_product_list, container, false);
        initUi(view);
        setRecyclerView();

        return view;
    }

    private void initUi(View view){
        recyclerView = view.findViewById(R.id.product_list_recyclerView);
    }

    private void setRecyclerView(){
        productAdapter = new ProductListAdapter((AppCompatActivity) getActivity(), productList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(productAdapter);
    }

    private void showSearchList(String query){
         RetrofitInstance.getRetrofit().create(Api.class).
                 searchProducts( query).enqueue(new Callback<List<Product>>() {
             @Override
             public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                productList =  response.body();
                productAdapter.setProducts(productList);
                productAdapter.notifyDataSetChanged();
             }

             @Override
             public void onFailure(Call<List<Product>> call, Throwable t) {

             }
         });
    }

}
