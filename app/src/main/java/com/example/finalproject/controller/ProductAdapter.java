package com.example.finalproject.controller;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private List<Product> mProducts = new ArrayList<>();
    private AppCompatActivity mActivity ;

    public ProductAdapter(AppCompatActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void setProducts(List<Product> products) {
        mProducts = products;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Activity activity = (Activity) parent.getContext();
        View view = activity.getLayoutInflater().inflate(R.layout.product_item, parent, false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Product product = mProducts.get(position);
        holder.bind(product);
    }


    @Override
    public int getItemCount() {

        return mProducts == null ? 0 : mProducts.size();
    }



    public class ProductHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewTitle;
        private TextView mTextViewPrice;
        private ImageView imageView ;
        private Product product;
        public ProductHolder(@NonNull final View itemView) {
            super(itemView);

            mTextViewTitle = itemView.findViewById(R.id.productName_TextView);
            mTextViewPrice = itemView.findViewById(R.id.ProductPrice_TextView);
            imageView = itemView.findViewById(R.id.product_imageView);

        }


        public void bind(final Product product) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mActivity.startActivity(productDetailActivity.newIntent(mActivity , product.getId()));
                }
            });

            mTextViewTitle.setText(product.getName());
            mTextViewPrice.setText(product.getPrice());
            Picasso.get().load(product.getImages().get(0).getSrc()).fit().into(imageView);

            this.product = product;

        }
    }

}
