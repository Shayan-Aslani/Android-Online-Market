package com.example.finalproject.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.finalproject.model.Customer;
import com.example.finalproject.repositories.CustomerRepository;

import java.util.List;
import java.util.ListIterator;

public class LoginRegisterViewModel extends AndroidViewModel {

    private MutableLiveData<Customer> mCustomer ;

    private CustomerRepository mRepository ;

    public LoginRegisterViewModel(@NonNull Application application) {
        super(application);
        mRepository = CustomerRepository.getInstance(application);
        mCustomer = mRepository.getCustomer();
    }

    public boolean registerCustomer(String email)
    {
        Customer customer = new Customer();
        customer.setEmail(email);
        mRepository.registerCustomer(customer);

        return true;
    }

    public boolean loginCustomer(String email){
        mRepository.loginCustomer(email);
        return true;
    }
}
