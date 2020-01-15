package com.example.finalproject.view.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.finalproject.R;
import com.example.finalproject.adapter.ProductBasketAdapter;
import com.example.finalproject.databinding.FragmentProductBasketBinding;
import com.example.finalproject.utils.UiUtils;
import com.example.finalproject.viewModel.ProductBasketViewModel;
import com.google.android.material.button.MaterialButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductBasketFragment extends Fragment {

    private RecyclerView basketRecyclerView;
    private ProductBasketAdapter productBasketAdapter;
    private TextView nullMassageTextView ;
    private MaterialButton loginButton ;

    private ProductBasketViewModel mViewModel;
    private FragmentProductBasketBinding mBinding;

    public static ProductBasketFragment newInstance() {
        Bundle args = new Bundle();
        ProductBasketFragment fragment = new ProductBasketFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ProductBasketFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProductBasketViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_basket, container, false);

        initUi();
        setupBadge();
        setShoppingCartRecyclerView();

        mViewModel.getCartProductBasketList().observe(this , shoppingBagList->{
            mBinding.sumPriceShoppingCartTextView.setText(mViewModel.totalBasketPrice());
            if(shoppingBagList.size() == 0)
            {
                nullMassageTextView.setVisibility(View.VISIBLE);
                loginButton.setVisibility(View.VISIBLE);
                basketRecyclerView.setVisibility(View.INVISIBLE);
            }
            else {
                nullMassageTextView.setVisibility(View.GONE);
                loginButton.setVisibility(View.GONE);
                basketRecyclerView.setVisibility(View.VISIBLE);
                productBasketAdapter.setProducts(shoppingBagList);
                productBasketAdapter.notifyDataSetChanged();
            }
        });

        mBinding.shoppingCartCloseImageview.setOnClickListener(view1 -> getActivity().onBackPressed());

        mBinding.shoppingCartFinalTextView.setOnClickListener(view -> getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container , RegisterFragment.newInstance())
                .addToBackStack(null)
                .commit());

        return mBinding.getRoot() ;
    }

    private void initUi(){
        basketRecyclerView = mBinding.shoppingCartRecyclerView ;
        nullMassageTextView = mBinding.nullMassageShoppingBag ;
        loginButton = mBinding.loginButtonShoppingFragment;
    }

    private void setShoppingCartRecyclerView(){
        productBasketAdapter = new ProductBasketAdapter((AppCompatActivity) getActivity());
        basketRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        basketRecyclerView.setAdapter(productBasketAdapter);
    }

    private void setupBadge(){
        mViewModel.getCartProductBasketList().observe(this , shoppingBagList->{
            UiUtils.setBadgeicon(shoppingBagList.size() , mBinding.shoppingCartIconShoppinFragment.basketTextview);
        });
    }
}
