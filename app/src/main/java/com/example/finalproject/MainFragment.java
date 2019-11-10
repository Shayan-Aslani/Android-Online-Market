package com.example.finalproject;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.model.Product;
import com.example.finalproject.network.Api;
import com.example.finalproject.network.RetrofitInstance;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {



    private RecyclerView recyclerView ;
    private List<Product> list = new ArrayList<>();
    private Api api ;
    private ProductAdapter adapter = new ProductAdapter(list);
    private ProgressBar progressBar ;

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_main, container, false);

        initUi(view);
     //   startInit();
        api = RetrofitInstance.getRetrofit().create(Api.class);
        api.getAllProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call , Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    Repository.getInstance().setAllProducts(response.body());
                    adapter.setProducts(Repository.getInstance().getAllProducts());
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }



            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("network", "onFailure: " + t.getMessage());
                Toast.makeText(getActivity(), "network Failure", Toast.LENGTH_SHORT).show();
            }
        });




      // new InitProductsAsynceTask().execute();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return view ;
    }

    private void initUi(View view){
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
    }
/*
    private List<Product> generateLists(String type) throws IOException {
       return RetrofitInstance.getRetrofit().create(Api.class)
                .getAllProducts("https://woocommerce.maktabsharif.ir/wp-json/wc/v3/products?consumer_key=ck_120a89c914da239359b2683859fb36ce3c94fc0a&consumer_secret=cs_0dabb4ea47c464969eaad199a30370b9e7cb7e7b&orderby=date").execute().body();
    }

 */

    private class ProductHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewTitle;
        private TextView mTextViewDate;
        private ImageView imageView ;
        private Product product;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewTitle = itemView.findViewById(R.id.textView);
            mTextViewDate = itemView.findViewById(R.id.textView2);
            imageView = itemView.findViewById(R.id.imageView);
        }

        public void bindCrime(Product product) {
            mTextViewTitle.setText(product.getName());
            mTextViewDate.setText(product.getPrice());
            Picasso.get().load(product.getImages().get(0).getSrc()).resize(200 , 200 ).centerCrop().into(imageView);

            this.product = product;
        }
    }

    private class ProductAdapter extends RecyclerView.Adapter<ProductHolder> {

        private List<Product> mProducts;

        public ProductAdapter(List<Product> crimes) {
            mProducts = crimes;
        }

        public void setProducts(List<Product> products) {
            mProducts = products;
        }

        @NonNull
        @Override
        public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.row, parent, false);
            ProductHolder productHolder = new ProductHolder(view);
            return productHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ProductHolder holder, int position) {

            holder.bindCrime(mProducts.get(position));
        }

        @Override
        public int getItemCount() {
            return mProducts.size();
        }
    }



}
