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

import com.example.doantotnghiep.Class.Cart;
import com.example.doantotnghiep.Class.DetailOder;
import com.example.doantotnghiep.Main.Bill.MainInfoBill;
import com.example.doantotnghiep.Main.MainDetail;
import com.example.doantotnghiep.MainActivity;
import com.example.doantotnghiep.R;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterBill extends RecyclerView.Adapter<AdapterBill.HistoryProductViewHolder> {

    private DecimalFormat formatPrice = new DecimalFormat("###,###,###");
    private List<DetailOder> listDetailOrder;
    private List<Cart> listOrder;
    private DetailOder orderInfo;
    private MainActivity home;
    private Context context;

    public void setData(Context context,List<DetailOder> listDetailOrder, List<Cart> listOrder,MainActivity home) {
        this.listDetailOrder = listDetailOrder;
        this.listOrder = listOrder;
        this.home = home;
        this.context= context;
        notifyDataSetChanged();
    }

    public void setData(Context context,List<DetailOder> listDetailOrder) {
        this.listDetailOrder = listDetailOrder;
        this.context= context;

    }
    @NonNull
    @Override
    public HistoryProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orderinfo,parent,false);
        return new  HistoryProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryProductViewHolder holder, int position) {
        DetailOder detailOrder = listDetailOrder.get(position);
        if (detailOrder == null){
            return;
        }
        else {
          //  Glide.with(holder.imgHitoryProduct.getContext()).load(detailOrder.getImgsp()).into(holder.imgHitoryProduct);
            holder.tvHitoryProductOrderNo.setText(detailOrder.getMadonhang());
            holder.tvHitoryProductName.setText(detailOrder.getTensp());
            holder.tvHitoryProductNum.setText(detailOrder.getDiachi());
            holder.tvHitoryProductPrice.setText(detailOrder.getTongtien());
            holder.tvHitoryProductDate.setText(detailOrder.getNgay());
            holder.tvHitoryProductStatus.setText(detailOrder.getStatus());


            for (DetailOder order : listDetailOrder){
                //if (order.getIdgh().equals(detailOrder.getIdct() ) ){
                  //  holder.tvHitoryProductDate.setText(detailOrder.getNgay());
                  //  break;
                }
            }

            holder.tvHitoryProductOrderNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (Cart order : listOrder){
                        if (String.valueOf(order.getIdgh()).equals(detailOrder.getMadonhang() ) ){
                            orderInfo = order;
                            break;
                        }
                    }

                    for (DetailOder itemDetailOrder :listDetailOrder){
                        if (detailOrder.getMadonhang().equals(itemDetailOrder.getMadonhang())){
                            orderInfo.addToListDetailOrder(itemDetailOrder);


                        }
                    }
                   // home.toOrderInfoFragment(orderInfo);
                }
            });
        }

    @Override
    public int getItemCount() {
        if (listDetailOrder!=null){
            return listDetailOrder.size();
        }else {
            return 0;
        }
    }



    public class HistoryProductViewHolder extends RecyclerView.ViewHolder{

        ImageView imgHitoryProduct;
        TextView tvHitoryProductName,tvHitoryProductNum,tvHitoryProductPrice,tvHitoryProductDate
                ,tvHitoryProductStatus,tvHitoryProductOrderNo;

        public HistoryProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHitoryProduct = itemView.findViewById(R.id.img_hitory_product);
            tvHitoryProductName = itemView.findViewById(R.id.tv_hitory_product_name);
            tvHitoryProductNum = itemView.findViewById(R.id.tv_hitory_product_num);
            tvHitoryProductPrice = itemView.findViewById(R.id.tv_hitory_product_price);
            tvHitoryProductDate = itemView.findViewById(R.id.tv_hitory_product_date);
            tvHitoryProductStatus = itemView.findViewById(R.id.tv_hitory_product_status);
            tvHitoryProductOrderNo = itemView.findViewById(R.id.tv_hitory_product_orderNo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MainInfoBill.class);
                    intent.putExtra("bill", listDetailOrder.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    // Toast.makeText(context, listcay.get(getPosition()).getProductName(), Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);
                }
            });
        }

    }
}
