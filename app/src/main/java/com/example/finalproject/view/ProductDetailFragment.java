package com.example.finalproject.view;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.R;
import com.example.finalproject.controller.adapters.ImageSliderAdapter;
import com.example.finalproject.databinding.FragmentProductDetailBinding;
import com.example.finalproject.model.Product;
import com.example.finalproject.repositories.ProductRepository;
import com.example.finalproject.viewModel.ProductDetailFragmentViewModel;
import com.google.android.material.button.MaterialButton;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailFragment extends Fragment {

   // public static final String PRODUCT_ID_ARG = "productIdArg";
    private Product mProduct;
    private TextView cartItemCountTextView;
    private MaterialButton addShoppingCartButton;
    private SliderView sliderView;

    private ProductDetailFragmentViewModel mViewModel;
    private FragmentProductDetailBinding mBinding;

    public static ProductDetailFragment newInstance() {
        Bundle args = new Bundle();
      //  args.putSerializable(PRODUCT_ID_ARG, product);
        ProductDetailFragment fragment = new ProductDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ProductDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   //     mProduct = (Product) getArguments().get(PRODUCT_ID_ARG);
        mViewModel = ViewModelProviders.of(getActivity()).get(ProductDetailFragmentViewModel.class);
        mProduct = mViewModel.getProduct().getValue() ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail, container, false);
        initUi();
        mBinding.setProductDetailsViewModel(mViewModel);
        mViewModel.getProduct().observe(this , product -> {
            setDetail();
        });

        addShoppingCartButton.setOnClickListener(view12 -> {
            mViewModel.addProductToShoppingCart(mProduct);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, ProductBasketFragment.newInstance())
                    .addToBackStack("trans")
                    .commit();
        });

        mBinding.backDetailImageview.setOnClickListener(view1 -> getActivity().onBackPressed());

        mViewModel.getShoppingCartList().observe(this, shoppingBagList -> {
            int bagSize = shoppingBagList.size();
            setBadgeicon(bagSize);
        });

        return mBinding.getRoot();
    }

    public void initUi() {
        sliderView = mBinding.imageSlider;
        addShoppingCartButton = mBinding.addShoppingCartButton;
        cartItemCountTextView = mBinding.shoppingCartIconDetailFragment.basketTextview;
    }

    public void setDetail() {
        sliderView.setSliderAdapter(new ImageSliderAdapter(getContext(), mProduct.getImages()));
    }

    private void setBadgeicon(int bagSize) {

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
    }


}
