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
import com.example.finalproject.model.CartProduct;
import com.example.finalproject.viewModel.ProductBasketViewModel;
import com.google.android.material.button.MaterialButton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductBasketFragment extends Fragment {

    private List<CartProduct> cartProductList ;
    private RecyclerView shoppingCartRecyclerView ;
    private ProductBasketAdapter productBasketAdapter;
    private TextView nullMassageTextView ;
    private MaterialButton loginButton ;
    private TextView basketCountTextView;

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
            if(shoppingBagList.size() == 0)
            {
                nullMassageTextView.setVisibility(View.VISIBLE);
                loginButton.setVisibility(View.VISIBLE);
                shoppingCartRecyclerView.setVisibility(View.INVISIBLE);
                mBinding.sumPriceShoppingCartTextView.setText(0);
            }
            else {
                nullMassageTextView.setVisibility(View.GONE);
                loginButton.setVisibility(View.GONE);
                shoppingCartRecyclerView.setVisibility(View.VISIBLE);
                cartProductList = shoppingBagList;
                productBasketAdapter.setProducts(cartProductList);
                productBasketAdapter.notifyDataSetChanged();
            }
        });

        mBinding.shoppingCartCloseImageview.setOnClickListener(view1 -> getActivity().onBackPressed());

        return mBinding.getRoot() ;
    }

    private void initUi(){
        shoppingCartRecyclerView = mBinding.shoppingCartRecyclerView ;
        basketCountTextView = mBinding.shoppingCartIconShoppinFragment.basketTextview;
        nullMassageTextView = mBinding.nullMassageShoppingBag ;
        loginButton = mBinding.loginButtonShoppingFragment;
    }

    private void setShoppingCartRecyclerView(){
        productBasketAdapter = new ProductBasketAdapter((AppCompatActivity) getActivity(), cartProductList);
        shoppingCartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        shoppingCartRecyclerView.setAdapter(productBasketAdapter);

    }

    private void setupBadge(){
        mViewModel.getCartProductBasketList().observe(this , shoppingBagList->{
            int bagSize = shoppingBagList.size() ;
            if (bagSize == 0) {
                basketCountTextView.setVisibility(View.GONE);
            } else {
                basketCountTextView.setText(String.valueOf(Math.min(bagSize, 99)));
                basketCountTextView.setVisibility(View.VISIBLE);
            }
        });
    }

}
