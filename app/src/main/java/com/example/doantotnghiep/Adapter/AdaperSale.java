package com.example.doantotnghiep.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.doantotnghiep.Class.Sale;
import com.example.doantotnghiep.Class.Product;
import com.example.doantotnghiep.MainActivity;
import com.example.doantotnghiep.R;

import java.text.DecimalFormat;
import java.util.List;

public class AdaperSale extends RecyclerView.Adapter<AdaperSale.ViewHolder> {
    private DecimalFormat formatPrice = new DecimalFormat("###,###,###");
    private List<Sale> mListProduct;
    private MainActivity home;

    // Khai báo Interface giúp cho việc click vào phần tử của recycleview
    public interface IClickItemProductListener {
        void onClickItemProduct(Product product);
    }

    public void setData(List<Sale> mList, MainActivity home) {
        this.mListProduct = mList;
        this.home = home;
        notifyDataSetChanged();
    }


    @Override
    public AdaperSale.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.itemsale, null);
        return new AdaperSale.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Sale product = mListProduct.get(position);

        if (product == null) {
            return;
        } else {
            Glide.with(holder.imageView.getContext()).load(product.getIgmcay()).into(holder.imageView);
            holder.tvTen.setText(product.getTencay());
           holder.tvgia.setText("đ "+formatPrice.format(product.getsale()));
           holder.tvsale.setText(product.getDirty()+" %");
            holder.tvgiacu.setText("đ "+formatPrice.format(product.getGia()));
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
        TextView tvTen, tvgiacu, tvgia, tvsale;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img_sanpham);
            tvTen = (TextView) itemView.findViewById(R.id.tvTenSpsale);
            tvgiacu = (TextView) itemView.findViewById(R.id.tvGiacu);
            tvgia = (TextView) itemView.findViewById(R.id.tvGiamoisale);
            tvsale = (TextView) itemView.findViewById(R.id.sale);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

}
