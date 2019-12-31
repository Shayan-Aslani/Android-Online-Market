package com.example.finalproject.view;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.finalproject.R;
import com.example.finalproject.controller.adapters.ProductListAdapter;
import com.example.finalproject.databinding.FragmentProductListBinding;
import com.example.finalproject.eventBus.ProductListSortMassage;
import com.example.finalproject.repositories.FilterRepository;
import com.example.finalproject.viewModel.ProductListFragmentViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends Fragment {

    public static final String SEARCH_STRING_ARG = "searchStringArg";
    public static final String SEARCHABLE_ARG = "searchableArg";

    private ProductListAdapter productAdapter;
    private RecyclerView recyclerView;
    private String searchText;
    private Boolean searchable;
    private String mOrderType, mOrderBy;
    private TextView sortTextView;
    private int mSortType;
    private ProgressBar progressBar;

    private FragmentProductListBinding mBinding ;
    private ProductListFragmentViewModel mViewModel ;

    public static ProductListFragment newInstance(String searchText, Boolean searchable) {

        Bundle args = new Bundle();
        args.putString(SEARCH_STRING_ARG, searchText);
        args.putBoolean(SEARCHABLE_ARG, searchable);
        ProductListFragment fragment = new ProductListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ProductListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchable = getArguments().getBoolean(SEARCHABLE_ARG);
        searchText = getArguments().getString(SEARCH_STRING_ARG);
        mViewModel = ViewModelProviders.of(this).get(ProductListFragmentViewModel.class);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false);
        initUi();
        setRecyclerView();

        mBinding.titleToolbarProductList.setText(searchText);

        setListeners();

        mViewModel.getProductList().observe(this , productList -> {
            productAdapter.setProducts(productList);
            productAdapter.notifyDataSetChanged();
            progressBar.setVisibility(View.INVISIBLE);
            mBinding.nullMassageProductListTextview.setVisibility(View.INVISIBLE);
            if (productList.size() == 0) {
                mBinding.nullMassageProductListTextview.setVisibility(View.VISIBLE);
            }
        });

        return mBinding.getRoot();
    }

    private void initUi() {
        recyclerView = mBinding.productListRecyclerView ;
        sortTextView = mBinding.sortModeTextView ;
        progressBar = mBinding.productListProgressBar ;
    }

    private void setRecyclerView() {
        productAdapter = new ProductListAdapter((AppCompatActivity) getActivity(), mViewModel.getProductList().getValue());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(productAdapter);
        mViewModel.loadFilteredListFromApi(searchText, mOrderBy, mOrderType);
    }

    private void setListeners(){
        mBinding.backToolbarProductList.setOnClickListener(view1 -> getActivity().onBackPressed());
        mBinding.sortRelative.setOnClickListener(view12 -> {
            DialogFragment dialogFragment = SortDialogFragment.newInstance(mSortType);
            dialogFragment.show(getFragmentManager(), null);
        });

        mBinding.filterRelative.setOnClickListener(view -> getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, android.R.anim.fade_out)
                .replace(R.id.fragment_container, FilterFragment.newInstance())
                .addToBackStack("FilterTransaction")
                .commit());
    }




    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSortChanged(ProductListSortMassage productListSortMassage) {

        mSortType = productListSortMassage.getEnumIndex();
        SortDialogFragment.Sorts sort = SortDialogFragment.getEnumSorts(mSortType);
        switch (sort) {
            case NEWEST:
                mOrderType = "date";
                sortTextView.setText("جدیدترین");
                break;
            case RATED:
                mOrderType = "popularity";
                sortTextView.setText("پربازدیدترین");
                break;
            case VISITED:
                mOrderType = "rating";
                sortTextView.setText("پرفروش ترین");
                break;
            case LOW_TO_HIGH:
                mOrderType = "price";
                mOrderBy = "asc";
                sortTextView.setText("قیمت از کم به زیاد");
                break;
            case HIGH_TO_LOW:
                mOrderType = "price";
                mOrderBy = "desc";
                sortTextView.setText("قیمت از زیاد به کم");
                break;
        }
        mViewModel.loadFilteredListFromApi(searchText, mOrderType, mOrderBy);

    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FilterRepository.getInstance(getContext()).getSelectedTerms().setValue(new ArrayList<>());
    }
}
