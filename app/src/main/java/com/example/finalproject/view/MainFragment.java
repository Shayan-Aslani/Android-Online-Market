package com.example.finalproject.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.R;
import com.example.finalproject.Utils.ShoppingCartPreferences;
import com.example.finalproject.controller.activity.CategoryDetailActivity;
import com.example.finalproject.controller.activity.CategoryListActivity;
import com.example.finalproject.controller.activity.SearchActivity;
import com.example.finalproject.controller.activity.productDetailActivity;
import com.example.finalproject.controller.adapters.ProductAdapter;
import com.example.finalproject.controller.fragment.ShoppingBagFragment;
import com.example.finalproject.databinding.FragmentMainBinding;
import com.example.finalproject.model.CartProduct;
import com.example.finalproject.model.Category;
import com.example.finalproject.model.Product;
import com.example.finalproject.model.Repository;
import com.example.finalproject.viewModel.MainFragmentViewModel;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */

public class MainFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {


    private RecyclerView latestProductsRecyclerView, popularProductsRecyclerView, mostViewedProductsRecyclerView;
    private TextView cartItemCountTextView;
    private ProductAdapter latestProductsAdapter, popularProductsAdapter, mostViewedProductAdapter;
    private DrawerLayout drawer;
    private NavigationView mainNavigationView;
    private List<Product> vipProducts;
    Toolbar toolbar;
    private SliderView sliderView;
    private ChipGroup categoriesChipGroup;

    private MainFragmentViewModel mViewModel;
    private FragmentMainBinding mBinding;

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
        mViewModel = ViewModelProviders.of(this).get(MainFragmentViewModel.class);

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        initUi();
        toolbar.setTitle("");
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        setupNavigationView();
        setupRecyclerViews();

        mViewModel.getCategoriesList().observe(this, list -> {
            setCategoriesChips(list);
        });

        mViewModel.getNewProductList().observe(this, productList -> {
            latestProductsAdapter.setProducts(productList);
            latestProductsAdapter.notifyDataSetChanged();
        });

        mViewModel.getRatedProductList().observe(this, list -> {
            popularProductsAdapter.setProducts(list);
            popularProductsAdapter.notifyDataSetChanged();
        });

        mViewModel.getVisitedProductList().observe(this, productList -> {
            mostViewedProductAdapter.setProducts(productList);
            mostViewedProductAdapter.notifyDataSetChanged();
        });


        sliderView.setSliderAdapter(new SliderAdapter(getContext()
                , Repository.getInstance(getContext()).getVipProducts()));

        return mBinding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);

        MenuItem item = menu.findItem(R.id.action_cart);
        item.getActionView().setOnClickListener(view -> getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, ShoppingBagFragment.newInstance())
                .addToBackStack("")
                .commit());

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
    private void setCategoriesChips(List<Category> categoryList) {
        for (final Category category : categoryList) {
            Chip chip = new Chip(getContext());
            ChipDrawable chipDrawable = (ChipDrawable) chip.getChipDrawable();
            chipDrawable.setChipBackgroundColorResource(R.color.green);
            chip.setTextColor(Color.WHITE);
            chip.setElevation((float) 8.0);
            chip.setText(category.getName());
            categoriesChipGroup.addView(chip);
            chip.setOnClickListener(view -> {
                if (category.getParent() == 0)
                    startActivity(CategoryListActivity.newIntent(getContext(), category.getId()));
                else
                    startActivity(CategoryDetailActivity.newIntent(getContext(), category.getId()));
            });
        }
    }

    private void setupRecyclerViews() {
        latestProductsAdapter = new ProductAdapter((AppCompatActivity) getActivity(), mViewModel.getNewProductList().getValue());
        popularProductsAdapter = new ProductAdapter((AppCompatActivity) getActivity(), mViewModel.getRatedProductList().getValue());
        mostViewedProductAdapter = new ProductAdapter((AppCompatActivity) getActivity(), mViewModel.getVisitedProductList().getValue());
        latestProductsRecyclerView.setAdapter(latestProductsAdapter);
        popularProductsRecyclerView.setAdapter(popularProductsAdapter);
        mostViewedProductsRecyclerView.setAdapter(mostViewedProductAdapter);
    }

    private void initUi() {
        latestProductsRecyclerView = mBinding.includeMainLayout.latestProductsRecyclerView;
        popularProductsRecyclerView = mBinding.includeMainLayout.popularProductsRecyclerView;
        mostViewedProductsRecyclerView = mBinding.includeMainLayout.mostViewedProductsRecyclerView;
        mainNavigationView = mBinding.mainNavigationView;
        categoriesChipGroup = mBinding.includeMainLayout.categoriesChipGroup;
        toolbar = mBinding.includeMainLayout.toolbar;
        drawer = mBinding.drawerLayout;
        sliderView = mBinding.includeMainLayout.mainSliderView;
    }

    private void setupBadge() {


        Repository.getInstance(getContext()).getShoppingCartProducts().observe(this, shoppingBagList -> {
            int bagSize = shoppingBagList.size();
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
        });


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.home_navigation_menu) {

        } else if (id == R.id.bag_navigation_menu) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, ShoppingBagFragment.newInstance())
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

    public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

        private Context context;
        private List<Product> productList;

        public SliderAdapter(Context context, List<Product> productList) {
            this.context = context;
            this.productList = productList;
        }

        @Override
        public SliderAdapter.SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
            return new SliderAdapter.SliderAdapterVH(inflate);
        }

        @Override
        public void onBindViewHolder(SliderAdapter.SliderAdapterVH viewHolder, int position) {
            viewHolder.bind(productList.get(position));
        }

        @Override
        public int getCount() {
            return productList.size();
        }

        class SliderAdapterVH extends SliderViewAdapter.ViewHolder {
            View itemView;
            ImageView imageViewSlider;
            Product mProduct;

            public SliderAdapterVH(View itemView) {
                super(itemView);
                imageViewSlider = itemView.findViewById(R.id.auto_image_slider);
                this.itemView = itemView;
            }

            public void bind(final Product product) {

                this.mProduct = product;
                Picasso.get().load(mProduct.getImages().get(0).getSrc()).placeholder(R.drawable.alt)
                        .into(imageViewSlider);
                itemView.setOnClickListener(view -> startActivity(productDetailActivity.newIntent(context, mProduct.getId())));

            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewModel.saveShoppingCartProducts();
    }
}

