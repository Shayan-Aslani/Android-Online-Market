package com.example.finalproject.controller.fragment;


import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.finalproject.R;
import com.example.finalproject.Utils.ShoppingCartPreferences;
import com.example.finalproject.controller.activity.CategoryDetailActivity;
import com.example.finalproject.controller.activity.CategoryListActivity;
import com.example.finalproject.controller.activity.SearchActivity;
import com.example.finalproject.controller.adapters.ProductAdapter;
import com.example.finalproject.model.CartProduct;
import com.example.finalproject.model.Category;
import com.example.finalproject.model.Repository;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */

public class MainFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {


    private RecyclerView latestProductsRecyclerView, popularProductsRecyclerView, mostViewedProductsRecyclerView;
    private TextView cartItemCountTextView ;
    private ProductAdapter latestProductsAdapter, popularProductsAdapter, mostViewedProductAdapter;
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
        setHasOptionsMenu(true);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initUi(view);
        toolbar.setTitle("");
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        setupNavigationView();
        setupRecyclerViews();
        setCategoriesChips();



        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu , menu);

        MenuItem item = menu.findItem(R.id.action_cart);
        item.getActionView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container , ShoppingBagFragment.newInstance())
                        .addToBackStack("")
                        .commit();
            }
        });

        FrameLayout cartActionView = (FrameLayout) item.getActionView();
        cartItemCountTextView = cartActionView.findViewById(R.id.cart_badge_counter_textView);
        setupBadge();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.app_bar_search: {
                startActivity(SearchActivity.newIntent(getContext()));
                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceAsColor")
    private void setCategoriesChips() {
        List<Category> categoryList = Repository.getInstance().getAllCategories();
        for (final Category category : categoryList) {
            Chip chip = new Chip(getContext());
            ChipDrawable chipDrawable = (ChipDrawable) chip.getChipDrawable();
            chipDrawable.setChipBackgroundColorResource(R.color.green);
            chip.setElevation((float) 8.0);
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
        latestProductsAdapter = new ProductAdapter((AppCompatActivity) getActivity(), Repository.getInstance().getNewProducts());
        popularProductsAdapter = new ProductAdapter((AppCompatActivity) getActivity(), Repository.getInstance().getRatedProducts());
        mostViewedProductAdapter = new ProductAdapter((AppCompatActivity) getActivity(), Repository.getInstance().getVisitedProducts());
        latestProductsRecyclerView.setAdapter(latestProductsAdapter);
        popularProductsRecyclerView.setAdapter(popularProductsAdapter);
        mostViewedProductsRecyclerView.setAdapter(mostViewedProductAdapter);
    }

    private void initUi(View view) {
        latestProductsRecyclerView = view.findViewById(R.id.latest_Products_RecyclerView);
        popularProductsRecyclerView = view.findViewById(R.id.popular_Products_RecyclerView);
        mostViewedProductsRecyclerView = view.findViewById(R.id.most_Viewed_Products_RecyclerView);
        mainNavigationView = view.findViewById(R.id.main_navigation_view);
        categoriesChipGroup = view.findViewById(R.id.categories_chip_group);
        toolbar = view.findViewById(R.id.toolbar);
        drawer = view.findViewById(R.id.drawer_layout);


    }

    private void setupBadge (){

        List<CartProduct> list = ShoppingCartPreferences.getProductList(getContext()) ;

        Repository.getInstance().getShoppingCartProducts().setValue(list);
        Repository.getInstance().getShoppingCartProducts().observe(this , shoppingBagList->{
            int bagSize = shoppingBagList.size() ;
            if (cartItemCountTextView != null) {
                if (bagSize == 0) {
                    if (cartItemCountTextView.getVisibility() != View.GONE) {
                        cartItemCountTextView.setVisibility(View.GONE);
                    }
                } else {
                    cartItemCountTextView.setText(String.valueOf(Math.min(bagSize, 99)));
                    if (cartItemCountTextView.getVisibility() != View.VISIBLE) {
                        cartItemCountTextView.setVisibility(View.VISIBLE);
                    }
                }
            }
            ShoppingCartPreferences.setProductList(getContext() , shoppingBagList);
        });


    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.home_navigation_menu) {

        } else if (id == R.id.bag_navigation_menu) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container , ShoppingBagFragment.newInstance())
                    .addToBackStack("transaction")
                    .commit();
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
}

