package com.example.finalproject.controller.fragment;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.finalproject.R;
import com.example.finalproject.eventBus.ProductListSortMassage;

import org.greenrobot.eventbus.EventBus;


/**
 * A simple {@link Fragment} subclass.
 */
public class SortDialogFragment extends DialogFragment {

    private static final String CURRENT_SORT_ARG = "CURRENT_SORT_ARG";
    private int mCurrentSort;
    private RadioGroup mSortRadioGroup;

    public static SortDialogFragment newInstance(Integer currentSort) {

        Bundle args = new Bundle();
        args.putInt(CURRENT_SORT_ARG , currentSort);
        SortDialogFragment fragment = new SortDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SortDialogFragment() {
        // Required empty public constructor
    }

    public enum Sorts {
        NEWEST(0),
        RATED(1),
        VISITED(2),
        HIGH_TO_LOW(3),
        LOW_TO_HIGH(4);

        int index;

        public int getIndex() {
            return index;
        }

        Sorts(int i) {
            index = i;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrentSort = getArguments().getInt(CURRENT_SORT_ARG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sort_dialog, container, false);

        Sorts currentSort = getEnumSorts(mCurrentSort);
        switch (currentSort) {
            case NEWEST:
                mSortRadioGroup.check(R.id.sort_dialog_newest);
                break;
            case RATED:
                mSortRadioGroup.check(R.id.sort_dialog_best_seller);
                break;
            case VISITED:
                mSortRadioGroup.check(R.id.sort_dialog_most_view);
                break;
            case HIGH_TO_LOW:
                mSortRadioGroup.check(R.id.sort_dialog_price_ascending);
                break;
            case LOW_TO_HIGH:
                mSortRadioGroup.check(R.id.sort_dialog_price_descending);
                break;
        }


        mSortRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selected = 0;
                switch (checkedId) {
                    case R.id.sort_dialog_newest:
                        selected = Sorts.NEWEST.getIndex();
                        break;
                    case R.id.sort_dialog_best_seller:
                        selected = Sorts.RATED.getIndex();
                        break;
                    case R.id.sort_dialog_most_view:
                        selected = Sorts.VISITED.getIndex();
                        break;
                    case R.id.sort_dialog_price_ascending:
                        selected = Sorts.HIGH_TO_LOW.getIndex();
                        break;
                    case R.id.sort_dialog_price_descending:
                        selected = Sorts.LOW_TO_HIGH.getIndex();
                        break;
                }

                if (selected == mCurrentSort) {
                    dismiss();
                } else {
                    EventBus.getDefault().post(new ProductListSortMassage(selected));
                    dismiss();
                }
            }
        });


        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_sort_dialog, null, false);

        // Dialog Box

        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
        mSortRadioGroup = view.findViewById(R.id.sort_radiogroup);

        return dialog;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public static Sorts getEnumSorts(int currentSort) {
        Sorts sort = null;
        for (Sorts sorts : Sorts.values()) {
            if (sorts.getIndex() == currentSort)
                sort = sorts;
        }
        return sort;
    }
}
