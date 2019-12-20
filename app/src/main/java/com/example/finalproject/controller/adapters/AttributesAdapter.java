package com.example.finalproject.controller.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.controller.activity.productDetailActivity;
import com.example.finalproject.model.Attribute;

import java.util.ArrayList;
import java.util.List;

public class AttributesAdapter extends RecyclerView.Adapter<AttributesAdapter.AttributeHolder> {

    private List<Attribute> attributeList = new ArrayList<>();
    private AppCompatActivity mActivity ;
    private AttributeTermAdapter attributeTermAdapter ;

    public AttributesAdapter(AppCompatActivity mActivity ) {
        this.mActivity = mActivity;
    }

    public AttributesAdapter(AppCompatActivity mActivity ,List<Attribute> attributeList ,  AttributeTermAdapter attributeTermAdapter) {
        this.mActivity = mActivity;
        this.attributeList = attributeList ;
        this.attributeTermAdapter = attributeTermAdapter;
    }

    public void setAttributes(List<Attribute> attributes) {
        attributeList = attributes;
    }

    @NonNull
    @Override
    public AttributeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Activity activity = (Activity) parent.getContext();
        View view = activity.getLayoutInflater().inflate(R.layout.filter_attributes_item, parent, false);
        return new AttributeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttributeHolder holder, int position) {
        Attribute attribute = attributeList.get(position);
        holder.bind(attribute);
    }


    @Override
    public int getItemCount() {

        return attributeList == null ? 0 : attributeList.size();
    }



    public class AttributeHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewTitle;
        private Attribute mAttribute;
        public AttributeHolder(@NonNull final View itemView) {
            super(itemView);

            mTextViewTitle = itemView.findViewById(R.id.attribute_title_textView);

        }


        public void bind(final Attribute attribute) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    attributeTermAdapter.setmAttribute(attribute);
                    attributeTermAdapter.setTerms(attribute.getTerms());
                    attributeTermAdapter.notifyDataSetChanged();
                }
            });

            mTextViewTitle.setText(attribute.getName());
            this.mAttribute = attribute;

        }
    }

}

