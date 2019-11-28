package com.example.finalproject.controller;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.finalproject.CategoryDetailActivity;
import com.example.finalproject.R;
import com.example.finalproject.model.Category;
import com.example.finalproject.model.Product;
import com.example.finalproject.model.Repository;
import com.example.finalproject.network.Api;
import com.example.finalproject.network.RetrofitInstance;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */

public class MainFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {


    private RecyclerView latestProductsRecyclerView, popularProductsRecyclerView, mostViewedProductsRecyclerView;
    private Api api;
    private ProductAdapter latestProductsAdapter, popularProductsAdapter, mostViewedProductAdapter;
    private ProgressBar progressBar;
    private DrawerLayout drawer;
    private NavigationView mainNavigationView;
    private List<Chip> categoriesChip = new ArrayList<>();
    Toolbar toolbar;
    private ChipGroup categoriesChipGroup;


    public static MainFragment newInstance() {

        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initUi(view);

        setupNavigationView();
        setupRecyclerViews();


        api = RetrofitInstance.getRetrofit().create(Api.class);

        generateLists();


        // new InitProductsAsynceTask().execute();

        return view;
    }

    @SuppressLint("ResourceAsColor")
    private void setCategoriesChips() {
        List<Category> categoryList = Repository.getInstance().getAllCategories();
        for (final Category category : categoryList) {
            Chip chip = new Chip(getContext());
            ChipDrawable chipDrawable = (ChipDrawable) chip.getChipDrawable();
            chipDrawable.setChipBackgroundColorResource(R.color.green);
            chip.setText(category.getName());
            categoriesChipGroup.addView(chip);
            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (category.getParent() == 0)
                        startActivity(CategoryListActivity.newIntent(getContext(), category.getId()));
                    else
                        startActivity(CategoryDetailActivity.newIntent(getContext(), category.getId()));
                }
            });
        }
    }

    private void setupRecyclerViews() {
        latestProductsAdapter = new ProductAdapter((AppCompatActivity) getActivity());
        popularProductsAdapter = new ProductAdapter((AppCompatActivity) getActivity());
        mostViewedProductAdapter = new ProductAdapter((AppCompatActivity) getActivity());
        latestProductsRecyclerView.setAdapter(latestProductsAdapter);
        popularProductsRecyclerView.setAdapter(popularProductsAdapter);
        mostViewedProductsRecyclerView.setAdapter(mostViewedProductAdapter);
    }

    private void initUi(View view) {
        latestProductsRecyclerView = view.findViewById(R.id.latest_Products_RecyclerView);
        popularProductsRecyclerView = view.findViewById(R.id.popular_Products_RecyclerView);
        mostViewedProductsRecyclerView = view.findViewById(R.id.most_Viewed_Products_RecyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        mainNavigationView = view.findViewById(R.id.main_navigation_view);
        categoriesChipGroup = view.findViewById(R.id.categories_chip_group);
        toolbar = view.findViewById(R.id.toolbar);
        drawer = view.findViewById(R.id.drawer_layout);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.home_navigation_menu) {

        } else if (id == R.id.bag_navigation_menu) {

        } else if (id == R.id.categories_navigation_menu) {
            startActivity(CategoryListActivity.newIntent(getContext(), 0));
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupNavigationView() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        mainNavigationView.setNavigationItemSelectedListener(this);
    }

    public boolean closeDrawer() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return true;
        } else
            return false;
    }

    private void generateLists() {
        api.getAllProducts("date", "20").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    Repository.getInstance().setAllProducts(response.body());
                    latestProductsAdapter.setProducts(Repository.getInstance().getAllProducts());
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
        api.getAllProducts("popularity", "20").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    popularProductsAdapter.setProducts(response.body());
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

        api.getAllProducts("rating", "20").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mostViewedProductAdapter.setProducts(response.body());
                    mostViewedProductAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("network", "onFailure: " + t.getMessage());
                Toast.makeText(getActivity(), "network Failure", Toast.LENGTH_SHORT).show();
            }
        });

        api.getAllCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    Repository.getInstance().setAllCategories(response.body());
                    setCategoriesChips();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("network", "onFailure: " + t.getMessage());
                Toast.makeText(getActivity(), "network Failure", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
