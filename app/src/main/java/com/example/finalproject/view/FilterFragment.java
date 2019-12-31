package com.example.finalproject.view;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
import com.example.finalproject.controller.adapters.AttributeTermAdapter;
import com.example.finalproject.controller.adapters.AttributesAdapter;
import com.example.finalproject.model.Attribute;
import com.example.finalproject.repositories.FilterRepository;
import com.example.finalproject.viewModel.ProductListFragmentViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterFragment extends Fragment {

    private TextView doFilterButton;
    private ImageView closeImageView;
    private List<Attribute> attributesList;
    private RecyclerView attributesRecyclerView, termsRecyclerView;
    private AttributesAdapter attributeAdapter;
    private AttributeTermAdapter termAdapter;

    private ProductListFragmentViewModel mProductListViewModel;

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
        attributesList = FilterRepository.getInstance(getContext()).getAttributes().getValue();
        mProductListViewModel = ViewModelProviders.of(this).get(ProductListFragmentViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_filter, container, false);
        initUi(view);
        setRecyclerViews();

        closeImageView.setOnClickListener(view1 -> getActivity().getSupportFragmentManager()
                .popBackStack());
        doFilterButton.setOnClickListener(view12 -> doFilter());

        FilterRepository.getInstance(getContext()).getAttributes().observe(this , attributeList -> {
            attributeAdapter.setAttributes(attributeList);
        });
        return view;
    }

    private void initUi(View view) {
        doFilterButton = view.findViewById(R.id.dofilter_filter_fragment);
        closeImageView = view.findViewById(R.id.close_imageView_filter_fragment);
        attributesRecyclerView = view.findViewById(R.id.attributes_recyclerView_filter);
        termsRecyclerView = view.findViewById(R.id.terms_recyclerView_filter);

    }

    private void setRecyclerViews() {
        termAdapter = new AttributeTermAdapter((AppCompatActivity) getActivity());
        attributeAdapter = new AttributesAdapter((AppCompatActivity) getActivity(), attributesList ,termAdapter);
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

}
