package com.example.finalproject.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalproject.database.CustomerAddressModel;
import com.example.finalproject.model.Billing;
import com.example.finalproject.model.CartProduct;
import com.example.finalproject.model.LineItem;
import com.example.finalproject.model.Order;
import com.example.finalproject.model.Shipping;
import com.example.finalproject.repositories.CustomerRepository;
import com.example.finalproject.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderViewModel extends AndroidViewModel {

    private CustomerRepository customerRepository ;
    private ProductRepository productRepository ;

    public OrderViewModel(@NonNull Application application) {
        super(application);
        customerRepository = CustomerRepository.getInstance(application);
        productRepository = ProductRepository.getInstance(application);
    }

    public MutableLiveData<Order> sendOrder(CustomerAddressModel customerAddressModel){
        List<LineItem> lineItemList = new ArrayList<>();
        for(CartProduct cartProduct : productRepository.getBasketProducts().getValue()){
            lineItemList.add(new LineItem(cartProduct.getId() , 1));
        }

        Shipping shipping = new Shipping();
        shipping.setFirst_name(customerAddressModel.getFirstName());
        shipping.setLast_name(customerAddressModel.getLastName());
        shipping.setAddress_1(customerAddressModel.getAddress());

        Billing billing = new Billing();
        billing.setFirst_name(customerAddressModel.getFirstName());
        billing.setLast_name(customerAddressModel.getLastName());
        billing.setAddress_1(customerAddressModel.getAddress());

        Order order = new Order();
        order.setBilling(billing);
        order.setShipping(shipping);
        order.setPaymentMethod("basc");
        order.setPaymentMethodTitle("Direct Bank Transfer");
        order.setLineItems(lineItemList);
        order.setCustomerId(customerAddressModel.getCustomerId());

        return customerRepository.sendOrder(order);
    }

    public void clearBasket(){
        productRepository.getBasketProducts().setValue(new ArrayList<>());
        productRepository.saveBasketProducts();
    }

    public LiveData<List<CustomerAddressModel>> getCustomerAddressList(){
        return customerRepository.getAllCustomerAddress();
    }

    public LiveData<List<CartProduct>> getBasketProducts(){
        return productRepository.getBasketProducts();
    }
}
