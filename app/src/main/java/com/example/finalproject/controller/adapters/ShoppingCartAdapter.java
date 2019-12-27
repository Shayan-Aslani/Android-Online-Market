package com.example.finalproject.controller.adapters;


import android.app.Activity;
import android.content.DialogInterface;
import android.icu.text.Collator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.controller.activity.productDetailActivity;
import com.example.finalproject.model.CartProduct;
import com.example.finalproject.model.Repository;
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
        private TextView mTextViewPrice;
        private TextView deletetextView ;
        private ImageView imageView ;
        private CartProduct mCartProduct;
        public ProductHolder(@NonNull final View itemView) {
            super(itemView);

            mTextViewTitle = itemView.findViewById(R.id.product_title_cart);
         mTextViewPrice = itemView.findViewById(R.id.product_price_textview_cart);
            imageView = itemView.findViewById(R.id.product_image_cart);
            deletetextView = itemView.findViewById(R.id.delete_textview_cart);

        }


        public void bind(final CartProduct product) {

            mTextViewTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mActivity.startActivity(productDetailActivity.newIntent(mActivity , product.getId()));
                }
            });

            deletetextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog alertDialog = new AlertDialog.Builder(mActivity)
                            .setTitle(R.string.are_you_sure_for_delete)
                            .setPositiveButton(R.string.yes , new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Repository.getInstance(mActivity).deleteCartproduct(mCartProduct);
                                }
                            })
                            .setNegativeButton(R.string.no , null)
                            .create();
                    alertDialog.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                    alertDialog.show();

                }
            });

            mTextViewTitle.setText(product.getName());
            mTextViewPrice.setText(product.getPrice());
            Picasso.get().load(product.getImages().get(0).getSrc()).fit().placeholder(R.drawable.alt).into(imageView);
            this.mCartProduct = product;

        }
    }

}

