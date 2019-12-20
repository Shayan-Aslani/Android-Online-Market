package com.example.finalproject.controller.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.R;
import com.google.android.material.button.MaterialButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterFragment extends Fragment {

    private MaterialButton doFilterButton ;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_filter, container, false);
        initUi(view);

        doFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doFilter();
            }
        });
        return view ;
    }

    private void initUi(View view){
        doFilterButton = view.findViewById(R.id.dofilter_button_filter_fragment);

    }

    private void doFilter(){
        getActivity().getSupportFragmentManager()
                .popBackStack();

    }

}
