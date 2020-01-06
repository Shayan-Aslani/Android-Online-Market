package com.example.finalproject.controller.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.R;
import com.example.finalproject.controller.adapters.CategoryAdapter;
import com.example.finalproject.model.Category;
import com.example.finalproject.model.Repository;

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
        categories = Repository.getInstance().getSubCategoires(parentId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_category_tab, container, false);
        initUi(view);
        setupRecyclerView();

        return view ;
    }

    private void initUi(View view){
        recyclerView = view.findViewById(R.id.category_list_recyclerView);

    }

    private void setupRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        categoryAdapter = new CategoryAdapter((AppCompatActivity) getActivity());
        categoryAdapter.setCategories(categories);
        recyclerView.setAdapter(categoryAdapter);
    }

}
