package com.example.doantotnghiep.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.doantotnghiep.Class.SlidePhoto;
import com.example.doantotnghiep.Class.Product;
import com.example.doantotnghiep.Main.Home;
import com.example.doantotnghiep.MainActivity;
import com.example.doantotnghiep.R;

import java.util.List;

public class AdaperSlide extends RecyclerView.Adapter<AdaperSlide.ViewHolder> {
    private List<SlidePhoto> mListProduct;
    private MainActivity home1;
    private Home home;

    public AdaperSlide(List<SlidePhoto> listSlidePhoto, Home home) {
        this.mListProduct = listSlidePhoto;
        this.home = home;
        notifyDataSetChanged();
    }


    // Khai báo Interface giúp cho việc click vào phần tử của recycleview
    public interface IClickItemProductListener {
        void onClickItemProduct(Product product);
    }

    public void setData(List<SlidePhoto> mList, MainActivity home) {
        this.mListProduct = mList;
        this.home1 = home;
        notifyDataSetChanged();
    }


    @Override
    public AdaperSlide.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_slide_photo, null);
        return new AdaperSlide.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SlidePhoto product = mListProduct.get(position);

        if (product == null) {
            return;
        } else {
            Glide.with(holder.imageView.getContext()).load(product.getResourceId()).into(holder.imageView);

//            holder.setItemClickListener(new IClickItemProductListener() {
//                @Override
//                public void onClickItemProduct(objhome product) {
//                    home.toDetailProductFragment(product);
//                }
//            });
        }
    }

    @Override
    public int getItemCount() {
        if (mListProduct != null) {
            return mListProduct.size();
        } else
            return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;


        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img_slide_photo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

}
