package com.example.finalproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.finalproject.R;
import com.example.finalproject.model.Product;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageSliderAdapter extends SliderViewAdapter<ImageSliderAdapter.SliderAdapterVH> {

    private Context context;
    private List<Product.Images> imageList;

    public ImageSliderAdapter(Context context, List list) {
        this.context = context;
        imageList = list;
    }

    @Override
    public ImageSliderAdapter.SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_slider_layout, null);
        return new ImageSliderAdapter.SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(ImageSliderAdapter.SliderAdapterVH viewHolder, int position) {
        Picasso.get().load(imageList.get(position).getSrc()).placeholder(R.drawable.alt)
                .into(viewHolder.imageViewBackground);
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {
        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.auto_image_slider);
            this.itemView = itemView;
        }
    }
}
