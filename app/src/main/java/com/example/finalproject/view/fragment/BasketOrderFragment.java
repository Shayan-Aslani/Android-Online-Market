package com.example.finalproject.view.fragment;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.R;
import com.example.finalproject.adapter.AddressAdapter;
import com.example.finalproject.adapter.OrderProductAdapter;
import com.example.finalproject.adapter.ProductMainAdapter;
import com.example.finalproject.databinding.FragmentBasketOrderBinding;
import com.example.finalproject.repositories.CustomerRepository;
import com.example.finalproject.repositories.ProductRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class BasketOrderFragment extends Fragment {

    private FragmentBasketOrderBinding mBinding ;

    public static BasketOrderFragment newInstance() {

        Bundle args = new Bundle();

        BasketOrderFragment fragment = new BasketOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public BasketOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_basket_order , container , false) ;
        setShoppingCartRecyclerView();
        mBinding.addNewAddress.setOnClickListener(view -> getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container , AddEditAddressFragment.newInstance())
                .addToBackStack(null)
                .commit());
        return mBinding.getRoot();
    }

    private void setShoppingCartRecyclerView() {
        RecyclerView addressRecyclerView = mBinding.addressRecyclerView ;
        RecyclerView shippingRecyclerView = mBinding.shippingRecyclerView ;
        shippingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL , true));
        OrderProductAdapter productMainAdapter = new OrderProductAdapter((AppCompatActivity) getActivity() ,
                ProductRepository.getInstance(getContext()).getBasketProducts().getValue());
        shippingRecyclerView.setAdapter(productMainAdapter);
        AddressAdapter addressAdapter = new AddressAdapter((AppCompatActivity) getActivity() ,
                CustomerRepository.getInstance(getContext()).getAllCustomerAddress
                        (CustomerRepository.getInstance(getContext()).getCustomer().getValue().getId()));
        addressRecyclerView.setLayoutManager(new LinearLayoutManager(getContext() , RecyclerView.HORIZONTAL , true));
        addressRecyclerView.setAdapter(addressAdapter);
    }
}
