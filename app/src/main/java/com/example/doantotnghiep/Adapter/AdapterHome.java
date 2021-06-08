package com.example.doantotnghiep.Adapter;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.doantotnghiep.Class.Product;
import com.example.doantotnghiep.Main.MainDetail;
import com.example.doantotnghiep.MainActivity;
import com.example.doantotnghiep.R;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.ViewHolder>  {
    private DecimalFormat formatPrice = new DecimalFormat("###,###,###");
    private List<Product> mListProduct;
    private MainActivity home;
    private Context context;
    // Khai báo Interface giúp cho việc click vào phần tử của recycleview
    public interface IClickItemProductListener{
        void onClickItemProduct(Product product);
    }

    public void setData(Context context, List<Product> mList, MainActivity home) {
        this.context= context;
        this.mListProduct = mList;
        this.home = home;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_product,null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHome.ViewHolder holder, int position) {

        Product product = mListProduct.get(position);

        if (product == null) {
            return;
        }
        else {
            Glide.with(holder.imvHinh.getContext()).load(product.getIgmsp()).into(holder.imvHinh);
            holder.tvTen.setText(product.getTensp());
           holder.tvGia.setText(formatPrice.format(product.getGiasp() )+" đ̲");

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
        }
        else
            return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imvHinh;
        public TextView tvTen, tvGia;

        public ViewHolder(View itemView) {
            super(itemView);
            imvHinh = (ImageView) itemView.findViewById(R.id.img_photo_product);
            tvTen = (TextView) itemView.findViewById(R.id.tv_product_name);
            tvGia = (TextView) itemView.findViewById(R.id.tv_product_price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MainDetail.class);
                    intent.putExtra("sanpham", mListProduct.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    // Toast.makeText(context, listcay.get(getPosition()).getProductName(), Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);
                }
            });
        }
    }


}
