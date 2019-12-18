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

import com.example.finalproject.R;
import com.example.finalproject.ShoppingCartPreferences;
import com.example.finalproject.controller.adapters.ShoppingCartAdapter;
import com.example.finalproject.model.CartProduct;
import com.example.finalproject.model.Product;
import com.example.finalproject.model.Repository;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingBagFragment extends Fragment {

    private List<Product> productList ;
    private List<Integer> productIdList ;
    private List<CartProduct> cartProductList ;
    private RecyclerView shoppingCartRecyclerView ;
    private ShoppingCartAdapter shoppingCartAdapter ;


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

       // cartProductList =  ShoppingCartPreferences.getProductList(getContext());
        cartProductList = Repository.getInstance().getShoppingBagProducts().getValue();
        //productList = Repository.getInstance().getShoppingBagProducts();
        /*for(Integer id : productIdList) {
            RetrofitInstance.getRetrofit().create(Api.class)
                    .getProduct(String.valueOf(id)).enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {
                    productList.add(response.body());
                    Repository.getInstance().getShoppingBagProducts().postValue(productList);
                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {

                }
            });
        }

         */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_shopping_bag, container, false);
        initUi(view);
        setShoppingCartRecyclerView();
        return view ;
    }

    private void initUi(View view){
        shoppingCartRecyclerView = view.findViewById(R.id.shopping_cart_recyclerView);
    }

    private void setShoppingCartRecyclerView(){
        shoppingCartAdapter = new ShoppingCartAdapter((AppCompatActivity) getActivity(), cartProductList);
        shoppingCartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        shoppingCartRecyclerView.setAdapter(shoppingCartAdapter);
    }

}
