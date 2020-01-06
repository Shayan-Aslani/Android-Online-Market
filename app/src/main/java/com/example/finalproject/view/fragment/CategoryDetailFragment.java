package com.example.finalproject.view.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.R;
import com.example.finalproject.adapter.ProductMainAdapter;
import com.example.finalproject.databinding.FragmentCategoryDetailBinding;
import com.example.finalproject.model.Category;
import com.example.finalproject.model.Product;
import com.example.finalproject.repositories.CategoriesRepository;
import com.example.finalproject.repositories.ProductRepository;
import com.example.finalproject.network.Api;
import com.example.finalproject.network.RetrofitInstance;
import com.example.finalproject.viewModel.CategoryDetailFragmentViewModel;

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
    private Api api;
    private ProductMainAdapter latestProductsAdapter, popularProductsAdapter;
    private TextView cartItemCountTextView ;
    private ProgressBar progressBar;
    private Category mCategory ;

    private FragmentCategoryDetailBinding mBinding ;
    private CategoryDetailFragmentViewModel mViewModel ;


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
        mCategory = CategoriesRepository.getInstance(getContext()).getCategoryById(categoryid);
        mViewModel = ViewModelProviders.of(this).get(CategoryDetailFragmentViewModel.class);
        mViewModel.loadNewProductListFromApi(mCategory.getId());
        mViewModel.loadRatedProductListFromApi(mCategory.getId());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_category_detail , container , false);

        initUi();
        setDetail();
        setupRecyclerViews();

        mViewModel.getRatedProducts().observe(this , productList -> {
            popularProductsAdapter.setProducts(productList);
            progressBar.setVisibility(View.INVISIBLE);
        });
        mViewModel.getNewProducts().observe(this , productList -> {
            latestProductsAdapter.setProducts(productList);
            progressBar.setVisibility(View.INVISIBLE);

        });

        ProductRepository.getInstance(getContext()).getBasketProducts().observe(this , shoppingBagList-> {
            setBadgeicon(shoppingBagList.size());
        });

        mBinding.backCateogryDetailImageview.setOnClickListener(view -> getActivity().onBackPressed());
        mBinding.shoppingCartIconCategoryFragment.basketImageview.setOnClickListener(view -> getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container , ProductBasketFragment.newInstance())
                .addToBackStack("transaction")
                .commit());
        return mBinding.getRoot();
    }

    private void initUi(){
        latestProductsRecyclerView = mBinding.lateestProductsRecyclerViewDetail;
        popularProductsRecyclerView = mBinding.popularProductsRecyclerViewDetail;
        progressBar = mBinding.progressBarCategoryDetail;
        cartItemCountTextView = mBinding.shoppingCartIconCategoryFragment.basketTextview;
    }

    private void setDetail(){
       mBinding.titleTextviewCategoryDetail.setText(mCategory.getName());
    }

    private void setupRecyclerViews() {
        latestProductsAdapter = new ProductMainAdapter((AppCompatActivity) getActivity());
        popularProductsAdapter = new ProductMainAdapter((AppCompatActivity) getActivity());
        latestProductsRecyclerView.setAdapter(latestProductsAdapter);
        popularProductsRecyclerView.setAdapter(popularProductsAdapter);
    }

    private void setBadgeicon (int bagSize){
        if (cartItemCountTextView != null) {
            if (bagSize == 0) {
                    cartItemCountTextView.setVisibility(View.GONE);
            } else {
                cartItemCountTextView.setText(String.valueOf(Math.min(bagSize, 99)));
                cartItemCountTextView.setVisibility(View.VISIBLE);
            }
        }
    }

}
