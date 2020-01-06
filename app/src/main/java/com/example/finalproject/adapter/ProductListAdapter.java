package com.example.finalproject.adapter;

import android.app.Activity;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.databinding.ItemProductListBinding;
import com.example.finalproject.model.Product;
import com.example.finalproject.view.fragment.ProductDetailFragment;
import com.example.finalproject.viewModel.ProductDetailFragmentViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductHolder> {

    private List<Product> mProducts = new ArrayList<>();
    private AppCompatActivity mActivity ;

    public ProductListAdapter(AppCompatActivity mActivity ) {
        this.mActivity = mActivity;
    }

    public ProductListAdapter(AppCompatActivity mActivity , List<Product> productList) {
        this.mActivity = mActivity;
        this.mProducts = productList;
    }

    public void setProducts(List<Product> products) {
        mProducts = products;
        notifyDataSetChanged();
    }

    public void addProductToList(List<Product> products){
        mProducts.addAll(products);
        notifyDataSetChanged();
    }

    public List<Product> getProductList(){
        return mProducts ;
    }


    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Activity activity = (Activity) parent.getContext();
        ItemProductListBinding binding = DataBindingUtil.inflate(activity.getLayoutInflater(),
                R.layout.item_product_list, parent, false);
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

        private ItemProductListBinding mbinding ;
        private ProductDetailFragmentViewModel detailFragmentViewModel ;

        private Product mProduct;
        public ProductHolder(@NonNull ItemProductListBinding binding) {
            super(binding.getRoot());
            mbinding = binding ;
            detailFragmentViewModel = ViewModelProviders.of(mActivity).get(ProductDetailFragmentViewModel.class);

        }


        public void bind(final Product product) {

            this.mProduct = product;
            detailFragmentViewModel.getProduct().setValue(mProduct);
            mbinding.setProductDetailsViewModel(detailFragmentViewModel);
            mbinding.executePendingBindings();

            Picasso.get().load(product.getImages().get(0).getSrc()).fit().placeholder(R.drawable.alt).into(mbinding.imageViewProductList);

            mbinding.getRoot().setOnClickListener(view -> {
               detailFragmentViewModel.getProduct().setValue(mProduct);
                mActivity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, ProductDetailFragment.newInstance())
                        .addToBackStack("transaction")
                        .commit();

            });


        }
    }

}

