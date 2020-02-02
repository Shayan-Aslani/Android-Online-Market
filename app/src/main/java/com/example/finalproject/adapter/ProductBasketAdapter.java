package com.example.finalproject.adapter;


import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.utils.ProductBasketConverter;
import com.example.finalproject.databinding.ItemProductBasketBinding;
import com.example.finalproject.model.CartProduct;
import com.example.finalproject.model.Product;
import com.example.finalproject.repositories.ProductRepository;
import com.example.finalproject.view.fragment.ProductDetailFragment;
import com.example.finalproject.viewModel.ProductDetailFragmentViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductBasketAdapter extends RecyclerView.Adapter<ProductBasketAdapter.ProductHolder> {

    private List<CartProduct> mProducts = new ArrayList<>();
    private AppCompatActivity mActivity ;

    public ProductBasketAdapter(AppCompatActivity mActivity ) {
        this.mActivity = mActivity;
    }

    public ProductBasketAdapter(AppCompatActivity mActivity , List<CartProduct> productList) {
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
        ItemProductBasketBinding binding = DataBindingUtil.inflate(activity.getLayoutInflater(),
                R.layout.item_product_basket, parent, false);
        return new ProductHolder(binding);
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
        private ImageView imageView ;
        private Product mProduct ;
        private ItemProductBasketBinding mBinding ;
        private ProductDetailFragmentViewModel detailFragmentViewModel ;

        private CartProduct mCartProduct;
        public ProductHolder(@NonNull ItemProductBasketBinding binding ) {
            super(binding.getRoot());
            mBinding = binding ;
            detailFragmentViewModel = ViewModelProviders.of(mActivity).get(ProductDetailFragmentViewModel.class);
            imageView = mBinding.productImageCart ;
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        public void bind(final CartProduct product) {

                this.mCartProduct = product;
                mProduct = ProductBasketConverter.convertToProduct(mCartProduct);
                detailFragmentViewModel.getProduct().setValue(mProduct);
                mBinding.setProductDetailsViewModel(detailFragmentViewModel);
                mBinding.executePendingBindings();
             //   mProduct = ProductRepository.getInstance(mActivity).getProductById(mProduct.getId()).getValue();

            mBinding.getRoot().setOnClickListener(view -> {
                detailFragmentViewModel.getProduct().setValue(mProduct);
                   mActivity.getSupportFragmentManager()
                           .beginTransaction()
                           .replace(R.id.fragment_container ,
                                   ProductDetailFragment.newInstance())
                           .addToBackStack("transaction")
                           .commit() ;

               });

            mBinding.deleteTextviewCart.setOnClickListener(view -> {

                AlertDialog alertDialog = new AlertDialog.Builder(mActivity)
                        .setTitle(R.string.are_you_sure_for_delete)
                        .setPositiveButton(R.string.yes , (dialogInterface, i) -> ProductRepository.getInstance(mActivity).deleteCartproduct(mCartProduct))
                        .setNegativeButton(R.string.no , null)
                        .create();
                alertDialog.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                alertDialog.show();

            });

            Picasso.get().load(product.getImages().get(0).getSrc()).placeholder(R.drawable.alt).into(imageView);
        }
    }

}

