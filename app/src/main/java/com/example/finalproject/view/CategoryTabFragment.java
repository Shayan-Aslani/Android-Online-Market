package com.example.finalproject.view;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.R;
import com.example.finalproject.controller.adapters.CategoryAdapter;
import com.example.finalproject.databinding.FragmentCategoryTabBinding;
import com.example.finalproject.model.Category;
import com.example.finalproject.repositories.CategoriesRepository;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryTabFragment extends Fragment {

    private static final String CATEGORY_PARENT_ID_ARG = "categoryIdArg";
    private RecyclerView recyclerView ;
    private CategoryAdapter categoryAdapter ;
    private List<Category>  categories ;
    private int parentId ;

    private FragmentCategoryTabBinding mBinding ;

    public static CategoryTabFragment newInstance(int id) {

        Bundle args = new Bundle();
        args.putInt(CATEGORY_PARENT_ID_ARG , id);
        CategoryTabFragment fragment = new CategoryTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CategoryTabFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentId = getArguments().getInt(CATEGORY_PARENT_ID_ARG);
        categories = CategoriesRepository.getInstance(getContext()).getSubCategoires(parentId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding =  DataBindingUtil.inflate(inflater ,R.layout.fragment_category_tab, container, false);
        initUi();
        setupRecyclerView();

        return mBinding.getRoot() ;
    }

    private void initUi(){
        recyclerView = mBinding.categoryListRecyclerView;

    }

    private void setupRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        categoryAdapter = new CategoryAdapter((AppCompatActivity) getActivity());
        categoryAdapter.setCategories(categories);
        recyclerView.setAdapter(categoryAdapter);
    }

}
