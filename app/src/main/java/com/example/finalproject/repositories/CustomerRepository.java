package com.example.finalproject.repositories;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.finalproject.model.Customer;
import com.example.finalproject.network.Api;
import com.example.finalproject.network.RetrofitInstance;
import com.example.finalproject.utils.Preferences;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CustomerRepository {

    private static CustomerRepository mInstance;
    private Context mContext;

    private MutableLiveData<Customer> mCustomer = new MutableLiveData<>();

    private CustomerRepository(Context mContext) {
        this.mContext = mContext;
    }

    public static CustomerRepository getInstance(Context context) {
        if (mInstance == null)
            mInstance = new CustomerRepository(context);

        return mInstance;
    }

    public void registerCustomer(Customer customer) {
        RetrofitInstance.getRetrofit().create(Api.class).registerCustomer(customer).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if (response.isSuccessful()) {
                    mCustomer.setValue(response.body());
                    saveCustomer();
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {

            }
        });
    }

    public void loginCustomer(String email) {
        RetrofitInstance.getRetrofit().create(Api.class).getCustomer(email).enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                if (response.isSuccessful()) {
                    mCustomer.setValue(response.body().get(0));
                    saveCustomer();
                }
            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void saveCustomer() {
        Preferences.setLoginedCustomer(mContext, mCustomer.getValue());
    }

    public void loadLoginnedCustomer() {
        mCustomer.setValue(Preferences.getLoginedCustomer(mContext));
    }

    public void logoutCustomer() {
        mCustomer.setValue(null);
        saveCustomer();
    }

    public MutableLiveData<Customer> getCustomer() {
        return mCustomer;
    }
}
