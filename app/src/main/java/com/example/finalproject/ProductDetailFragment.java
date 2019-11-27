package com.example.finalproject;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.model.Product;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailFragment extends Fragment {

    public static final String PRODUCT_ID_ARG = "productIdArg";
    private Product mProduct ;
    private TextView nameTextView , priceTextView , descriptionTextView ;
    private ImageView imageView ;
    private int mProductId;

    public static ProductDetailFragment newInstance(int productId) {
        
        Bundle args = new Bundle();
        args.putSerializable(PRODUCT_ID_ARG , productId);
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
        mProductId = (Integer) getArguments().get(PRODUCT_ID_ARG);
        mProduct = Repository.getInstance().getProductById(mProductId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_product_detail, container, false);
        initUi(view);
        setDetail();
        return view;
    }

    public void initUi(View view)
    {
        nameTextView = view.findViewById(R.id.productName_TextView_Detail);
        priceTextView = view.findViewById(R.id.productPrice_TextView_Detail);
        descriptionTextView = view.findViewById(R.id.productDescription_TextView_Detail);
        imageView = view.findViewById(R.id.imageView);
    }

    public void setDetail(){
        nameTextView.setText(mProduct.getName());
        priceTextView.setText(mProduct.getPrice());
        descriptionTextView.setText(mProduct.getDescription());
        Picasso.get().load(mProduct.getImages().get(0).getSrc()).into(imageView);
    }

}
