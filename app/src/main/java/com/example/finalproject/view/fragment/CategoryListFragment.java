package com.example.finalproject.view.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.R;
import com.example.finalproject.databinding.FragmentCategoryListBinding;
import com.example.finalproject.model.Category;
import com.example.finalproject.repositories.CategoriesRepository;
import com.google.android.material.tabs.TabLayout;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryListFragment extends Fragment {

    public static final String CURRENT_CATEGORY_ID_ARG = "currentCategoryid";
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private CategoryViewPagerAdapter mPagerAdapter;
    private int currentId;

    private FragmentCategoryListBinding mBinding;


    public static CategoryListFragment newInstance(int currentId) {

        Bundle args = new Bundle();
        args.putInt(CURRENT_CATEGORY_ID_ARG, currentId);
        CategoryListFragment fragment = new CategoryListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CategoryListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentId = getArguments().getInt(CURRENT_CATEGORY_ID_ARG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_category_list, container, false);

        initUi();
        setupViewPager();

        mBinding.buttonBack.setOnClickListener(view -> getActivity().onBackPressed());

        return mBinding.getRoot();
    }

    private void initUi() {
        mViewPager = mBinding.categoriesViewpager;
        mTabLayout = mBinding.tablayout;
    }

    private void setupViewPager() {
        mTabLayout.setupWithViewPager(mViewPager);
        mPagerAdapter = new CategoryViewPagerAdapter(getFragmentManager());
        mPagerAdapter.setParentList(CategoriesRepository.getInstance(getContext()).getParentCategories().getValue());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(CategoriesRepository.getInstance(getContext()).getParentCategories().getValue()
                .indexOf(CategoriesRepository.getInstance(getContext()).getCategoryById(currentId)));
    }


    private class CategoryViewPagerAdapter extends FragmentStatePagerAdapter {

        private List<Category> mParentList;

        public void setParentList(List<Category> parentList) {
            mParentList = parentList;
        }

        public CategoryViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return CategoryTabFragment.newInstance(mParentList.get(i).getId());
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mParentList.get(position).getName();
        }

        @Override
        public int getCount() {
            return mParentList.size();
        }
    }


}
