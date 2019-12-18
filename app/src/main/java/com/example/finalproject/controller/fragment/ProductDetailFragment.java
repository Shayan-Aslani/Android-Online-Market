package com.example.finalproject.controller.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.R;
import com.example.finalproject.ShoppingCartPreferences;
import com.example.finalproject.model.CartProduct;
import com.example.finalproject.model.Product;
import com.example.finalproject.model.Repository;
import com.google.android.material.button.MaterialButton;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailFragment extends Fragment {

    public static final String PRODUCT_ID_ARG = "productIdArg";
    private Product mProduct ;
    private TextView nameTextView , priceTextView , descriptionTextView ;
    private MaterialButton addShoppingCartButton ;
    private int mProductId;
    private SliderView sliderView ;

    public static ProductDetailFragment newInstance(int productId) {
        
        Bundle args = new Bundle();
        args.putSerializable(PRODUCT_ID_ARG , productId);
        ProductDetailFragment fragment = new ProductDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public ProductDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductId = (Integer) getArguments().get(PRODUCT_ID_ARG);
        mProduct = Repository.getInstance().getProductById(mProductId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_product_detail, container, false);
        initUi(view);
        setDetail();

        addShoppingCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<CartProduct> list =  Repository.getInstance().getShoppingBagProducts().getValue();
                list.add(Repository.getInstance().convertToCartProduct(mProduct));
                Repository.getInstance().getShoppingBagProducts().setValue(list);
              /*  List<Integer> idList = ShoppingCartPreferences.getProductList(getContext()) ;
                if(idList == null)
                    idList = new ArrayList<>();
                idList.add(mProduct.getId());
                ShoppingCartPreferences.setProductList(getContext() , idList);

               */
            }
        });

        return view;
    }

    public void initUi(View view)
    {
        nameTextView = view.findViewById(R.id.productName_TextView_Detail);
        priceTextView = view.findViewById(R.id.productPrice_TextView_Detail);
        descriptionTextView = view.findViewById(R.id.productDescription_TextView_Detail);
        sliderView = view.findViewById(R.id.imageSlider);
        addShoppingCartButton = view.findViewById(R.id.add_shopping_cart_Button);
    }

    public void setDetail(){
        nameTextView.setText(mProduct.getName());
        priceTextView.setText(mProduct.getPrice());
        descriptionTextView.setText(mProduct.getDescription());
        sliderView.setSliderAdapter(new SliderAdapter(getContext() , mProduct.getImages()));
    }

    public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

        private Context context;
        private List<Product.Images> imageList ;

        public SliderAdapter(Context context , List list) {
            this.context = context;
            imageList = list ;
        }

        @Override
        public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
            return new SliderAdapterVH(inflate);
        }
        @Override
        public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
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
                imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
                this.itemView = itemView;
            }
        }
    }

}
