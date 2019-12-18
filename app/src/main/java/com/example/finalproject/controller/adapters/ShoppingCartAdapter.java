package com.example.finalproject.controller.adapters;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.controller.activity.productDetailActivity;
import com.example.finalproject.model.CartProduct;
import com.example.finalproject.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ProductHolder> {

    private List<CartProduct> mProducts = new ArrayList<>();
    private AppCompatActivity mActivity ;

    public ShoppingCartAdapter(AppCompatActivity mActivity ) {
        this.mActivity = mActivity;
    }

    public ShoppingCartAdapter (AppCompatActivity mActivity , List<CartProduct> productList) {
        this.mActivity = mActivity;
        this.mProducts = productList;
    }

    public void setProducts(List<CartProduct> products) {
        mProducts = products;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Activity activity = (Activity) parent.getContext();
        View view = activity.getLayoutInflater().inflate(R.layout.shopping_cart_list_item, parent, false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        CartProduct product = mProducts.get(position);
        holder.bind(product);
    }


    @Override
    public int getItemCount() {

        return mProducts == null ? 0 : mProducts.size();
    }



    public class ProductHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewTitle;
        private TextView mTextViewDescription;
        private ImageView imageView ;
        private CartProduct mProduct;
        public ProductHolder(@NonNull final View itemView) {
            super(itemView);

            mTextViewTitle = itemView.findViewById(R.id.prduct_name_shopping_cart);
            mTextViewDescription = itemView.findViewById(R.id.product_description_shopping_cart);
            imageView = itemView.findViewById(R.id.product_image_shopping_cart);

        }


        public void bind(final CartProduct product) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mActivity.startActivity(productDetailActivity.newIntent(mActivity , product.getId()));
                }
            });

            mTextViewTitle.setText(product.getName());
            mTextViewDescription.setText(product.getShort_description());
            Picasso.get().load(product.getImages().get(0).getSrc()).fit().placeholder(R.drawable.alt).into(imageView);
            this.mProduct = product;

        }
    }

}

