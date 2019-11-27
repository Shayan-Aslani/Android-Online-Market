package com.example.finalproject;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.model.Product;
import com.example.finalproject.network.Api;
import com.example.finalproject.network.RetrofitInstance;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */

public class MainFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {



    private RecyclerView recyclerView , recyclerView2 , recyclerView3;
    private List<Product> list = new ArrayList<>();
    private Api api ;
    private ProductAdapter adapter , adapter2 , adapter3 ;
    private ProgressBar progressBar ;
    private DrawerLayout drawer;
    private NavigationView navigationView;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_main, container, false);

        initUi(view);

        drawer =  view.findViewById(R.id.drawer_layout);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);










        adapter = new ProductAdapter((AppCompatActivity) getActivity());
        adapter2 = new ProductAdapter((AppCompatActivity) getActivity());

        api = RetrofitInstance.getRetrofit().create(Api.class);
        api.getAllProducts("date",  "20").enqueue(new Callback<List<Product>>() {
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

        api.getAllProducts("popularity" , "20").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call , Response<List<Product>> response) {
                if (response.isSuccessful()) {
               //     Repository.getInstance().setAllProducts(response.body());
                    adapter2.setProducts(response.body());
                    adapter2.notifyDataSetChanged();
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

      //  recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
      //  recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView2.setAdapter(adapter2);
        recyclerView3.setLayoutManager(new LinearLayoutManager(getActivity()));
     //   recyclerView3.setAdapter(adapter3);

        return view ;
    }



    private void initUi(View view){
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView2 = view.findViewById(R.id.recyclerView2);
        recyclerView3 = view.findViewById(R.id.recyclerView3);
        progressBar = view.findViewById(R.id.progressBar);
        navigationView = view.findViewById(R.id.main_navigation_view);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.home_navigation_menu) {
            // Handle the camera action
        } else if (id == R.id.bag_navigation_menu) {

        } else if (id == R.id.bag_navigation_menu) {

        } else if (id == R.id.categories_navigation_menu) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
/*
    private List<Product> generateLists(String type) throws IOException {
       return RetrofitInstance.getRetrofit().create(Api.class)
                .getAllProducts("https://woocommerce.maktabsharif.ir/wp-json/wc/v3/products?consumer_key=ck_120a89c914da239359b2683859fb36ce3c94fc0a&consumer_secret=cs_0dabb4ea47c464969eaad199a30370b9e7cb7e7b&orderby=date").execute().body();
    }

 */


}
