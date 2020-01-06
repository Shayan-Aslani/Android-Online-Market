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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.R;
import com.example.finalproject.controller.adapters.ShoppingCartAdapter;
import com.example.finalproject.model.CartProduct;
import com.example.finalproject.model.Repository;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingBagFragment extends Fragment {

    private ImageView closeImageView ;
    private List<CartProduct> cartProductList ;
    private RecyclerView shoppingCartRecyclerView ;
    private ShoppingCartAdapter shoppingCartAdapter ;
    private TextView cartItemCountTextView ;

    public static ShoppingBagFragment newInstance() {

        Bundle args = new Bundle();

        ShoppingBagFragment fragment = new ShoppingBagFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ShoppingBagFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Repository.getInstance().getShoppingCartProducts().observe(this , shoppingBagList->{
            cartProductList = shoppingBagList ;
            shoppingCartAdapter.setProducts(cartProductList);
            shoppingCartAdapter.notifyDataSetChanged();
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_shopping_bag, container, false);
        initUi(view);
        setupBadge();
        setShoppingCartRecyclerView();
        closeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        return view ;
    }

    private void initUi(View view){
        shoppingCartRecyclerView = view.findViewById(R.id.shopping_cart_recyclerView);
        closeImageView = view.findViewById(R.id.shopping_cart_close_imageview);
        cartItemCountTextView = view.findViewById(R.id.cart_badge_counter_textView);
    }

    private void setShoppingCartRecyclerView(){
        shoppingCartAdapter = new ShoppingCartAdapter((AppCompatActivity) getActivity(), cartProductList);
        shoppingCartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        shoppingCartRecyclerView.setAdapter(shoppingCartAdapter);
    }

    private void setupBadge(){
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
            BasketSharedPreferences.setProductList(getContext() , shoppingBagList);
        });
    }

}
