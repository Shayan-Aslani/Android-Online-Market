package com.example.finalproject.controller.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.databinding.ProductMainListItemBinding;
import com.example.finalproject.model.Product;
import com.example.finalproject.view.ProductDetailFragment;
import com.example.finalproject.viewModel.ProductDetailFragmentViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductMainAdapter extends RecyclerView.Adapter<ProductMainAdapter.ProductHolder> {

    private List<Product> mProducts = new ArrayList<>();
    private AppCompatActivity mActivity ;

    public ProductMainAdapter(AppCompatActivity mActivity ) {
        this.mActivity = mActivity;
    }

    public ProductMainAdapter(AppCompatActivity mActivity , List<Product> productList) {
        this.mActivity = mActivity;
        this.mProducts = productList;
    }

    public void setProducts(List<Product> products) {
        mProducts = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Activity activity = (Activity) parent.getContext();
        ProductMainListItemBinding binding = DataBindingUtil.inflate(activity.getLayoutInflater(),
                R.layout.product_main_list_item, parent, false);

        return new ProductHolder(binding);
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
        private ImageView imageView ;
        private ProductDetailFragmentViewModel mViewModel ;
        private ProductMainListItemBinding mBinding ;
        private Product mProduct;

        public ProductHolder(@NonNull ProductMainListItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding ;
            mViewModel = ViewModelProviders.of(mActivity).get(ProductDetailFragmentViewModel.class);
            imageView = mBinding.holderIvProductImage ;

        }


        public void bind(Product product) {

            mProduct = product ;
            mViewModel.getProduct().setValue(mProduct);
            mBinding.setProductDetailsViewModel(mViewModel);
            mBinding.executePendingBindings();

            itemView.setOnClickListener(view ->{
                mViewModel.getProduct().setValue(mProduct);
                    mActivity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container , ProductDetailFragment.newInstance())
                    .addToBackStack("transaction")
                    .commit();
                    });

            Picasso.get().load(product.getImages().get(0).getSrc()).fit().placeholder(R.drawable.alt).into(imageView);

        }
    }

}
