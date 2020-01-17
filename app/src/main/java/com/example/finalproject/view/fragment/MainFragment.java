package com.example.finalproject.view.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.R;
import com.example.finalproject.model.Customer;
import com.example.finalproject.repositories.CustomerRepository;
import com.example.finalproject.utils.Preferences;
import com.example.finalproject.utils.UiUtils;
import com.example.finalproject.view.activity.CategoryDetailActivity;
import com.example.finalproject.view.activity.CategoryListActivity;
import com.example.finalproject.adapter.ProductMainAdapter;
import com.example.finalproject.databinding.FragmentMainBinding;
import com.example.finalproject.databinding.ToolbarMainFragmentBinding;
import com.example.finalproject.model.Category;
import com.example.finalproject.model.Product;
import com.example.finalproject.viewModel.MainFragmentViewModel;
import com.example.finalproject.viewModel.ProductBasketViewModel;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
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
    private ProductMainAdapter latestProductsAdapter, popularProductsAdapter, mostViewedProductAdapter;
    private DrawerLayout drawer;
    private SliderView sliderView;

    private ProductBasketViewModel productBasketViewModel;
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
        mViewModel = ViewModelProviders.of(this).get(MainFragmentViewModel.class);
        productBasketViewModel = ViewModelProviders.of(this).get(ProductBasketViewModel.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);

        initUi();
        setupNavigationView();
        setupRecyclerViews();
        setToolbarMenuListeners();
        setObservers();

        sliderView.setSliderAdapter(new SliderAdapter(getContext()
                , mViewModel.getVipProducts().getValue()));

        TextView textView = mBinding.mainNavigationView.getHeaderView(0).findViewById(R.id.main_nav_tv_username);
        CustomerRepository.getInstance(getContext()).getCustomer().observe(this , customer ->{
            if(customer!= null) {
                mBinding.mainNavigationView.getMenu().findItem(R.id.logut_nav_menu).setVisible(true);
                textView.setText(customer.getEmail());
            }
            else
            {
                textView.setText("");
                mBinding.mainNavigationView.getMenu().findItem(R.id.logut_nav_menu).setVisible(false);
            }
        });

        return mBinding.getRoot();
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
            mBinding.includeMainLayout.categoriesChipGroup.addView(chip);
            chip.setOnClickListener(view -> {
                if (category.getParent() == 0)
                    startActivity(CategoryListActivity.newIntent(getContext(), category.getId()));
                else
                    startActivity(CategoryDetailActivity.newIntent(getContext(), category.getId()));
            });
        }
    }

    private void setToolbarMenuListeners() {
        ToolbarMainFragmentBinding toolbarBinding = mBinding.includeMainLayout.mainFragmentToolbar;

        toolbarBinding.mainToolbarBasketImageview.setOnClickListener(view -> getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, ProductBasketFragment.newInstance())
                .addToBackStack("")
                .commit());

        toolbarBinding.mainToolbarSearchImageView.setOnClickListener(view ->
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, SearchFragment.newInstance())
                        .addToBackStack("transaction")
                        .commit()
        );

        toolbarBinding.mainToolbarNavigationImageview.setOnClickListener(view -> drawer.openDrawer(GravityCompat.START));
    }

    private void setupRecyclerViews() {
        latestProductsAdapter = new ProductMainAdapter((AppCompatActivity) getActivity());
        popularProductsAdapter = new ProductMainAdapter((AppCompatActivity) getActivity());
        mostViewedProductAdapter = new ProductMainAdapter((AppCompatActivity) getActivity());
        latestProductsRecyclerView.setAdapter(latestProductsAdapter);
        popularProductsRecyclerView.setAdapter(popularProductsAdapter);
        mostViewedProductsRecyclerView.setAdapter(mostViewedProductAdapter);
    }

    private void initUi() {
        latestProductsRecyclerView = mBinding.includeMainLayout.latestProductsRecyclerView;
        popularProductsRecyclerView = mBinding.includeMainLayout.popularProductsRecyclerView;
        mostViewedProductsRecyclerView = mBinding.includeMainLayout.mostViewedProductsRecyclerView;
        drawer = mBinding.drawerLayout;
        sliderView = mBinding.includeMainLayout.mainSliderView;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setObservers() {
        mViewModel.getCategoriesList().observe(this, list -> {
            setCategoriesChips(list);
        });

        mViewModel.getNewProductList().observe(this, productList -> {
            latestProductsAdapter.setProducts(productList);
        });

        mViewModel.getRatedProductList().observe(this, list -> {
            popularProductsAdapter.setProducts(list);
        });

        mViewModel.getVisitedProductList().observe(this, productList -> {
            mostViewedProductAdapter.setProducts(productList);
        });

        productBasketViewModel.getCartProductBasketList().observe(this, shoppingBagList -> {
            UiUtils.setBadgeicon(shoppingBagList.size(), mBinding.includeMainLayout.mainFragmentToolbar.mainToolbarBasketTextview);
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id) {
            case R.id.home_navigation_menu:
                closeDrawer();
                break;
            case R.id.bag_navigation_menu:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, ProductBasketFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.categories_navigation_menu:
                startActivity(CategoryListActivity.newIntent(getContext(), 0));
                break;
            case R.id.logut_nav_menu :
                mViewModel.customerLogout();
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupNavigationView() {
        mBinding.mainNavigationView.setNavigationItemSelectedListener(this);
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
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_slider_layout, null);
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
             /*   itemView.setOnClickListener(view ->
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, ProductDetailFragment.newInstance())
                                .addToBackStack("transaction")
                                .commit());
              */

            }
        }
    }
}

