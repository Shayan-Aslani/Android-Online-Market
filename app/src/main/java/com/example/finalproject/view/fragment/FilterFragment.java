package com.example.finalproject.view.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.R;
import com.example.finalproject.adapter.AttributeTermAdapter;
import com.example.finalproject.adapter.AttributesAdapter;
import com.example.finalproject.databinding.FragmentFilterBinding;
import com.example.finalproject.model.Attribute;
import com.example.finalproject.repositories.FilterRepository;
import com.example.finalproject.viewModel.ProductListFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterFragment extends Fragment {


    private RecyclerView attributesRecyclerView, termsRecyclerView;
    private AttributesAdapter attributeAdapter;
    private AttributeTermAdapter termAdapter;

    private ProductListFragmentViewModel mProductListViewModel;
    private FragmentFilterBinding mBinding ;

    public static FilterFragment newInstance() {
        Bundle args = new Bundle();
        FilterFragment fragment = new FilterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public FilterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductListViewModel = ViewModelProviders.of(this).get(ProductListFragmentViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_filter, container, false);
        initUi();
        setRecyclerViews();
        setListeners();

        mProductListViewModel.getmAttributes().observe(this , attributeList -> {
            attributeAdapter.setAttributes(attributeList);
        });

        return mBinding.getRoot();
    }

    private void initUi() {
        attributesRecyclerView = mBinding.attributesRecyclerViewFilter;
        termsRecyclerView = mBinding.termsRecyclerViewFilter;
    }

    private void setRecyclerViews() {
        termAdapter = new AttributeTermAdapter((AppCompatActivity) getActivity());
        attributeAdapter = new AttributesAdapter((AppCompatActivity) getActivity(), new ArrayList<>(),termAdapter);
        attributesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        termsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        termsRecyclerView.setAdapter(termAdapter);
        attributesRecyclerView.setAdapter(attributeAdapter);
    }

    private void doFilter() {
        mProductListViewModel.loadFilteredListFromApi();
        getActivity().getSupportFragmentManager()
                .popBackStack();
    }

    private void setListeners(){

        mBinding.closeImageViewFilterFragment.setOnClickListener(view1 -> getActivity().getSupportFragmentManager()
                .popBackStack());

        mBinding.dofilterFilterFragment.setOnClickListener(view12 -> doFilter());
    }

}
