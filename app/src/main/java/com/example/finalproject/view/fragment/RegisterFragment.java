package com.example.finalproject.view.fragment;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.R;
import com.example.finalproject.databinding.FragmentRegisterBinding;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {


    private FragmentRegisterBinding mBinding ;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_register , container , false);

        mBinding.toolbarCartRegisterIvBackButton.setOnClickListener(view -> getActivity().onBackPressed());
        return mBinding.getRoot();
    }

}
