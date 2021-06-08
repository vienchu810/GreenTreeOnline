package com.example.doantotnghiep.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doantotnghiep.Class.Customer;
import com.example.doantotnghiep.Class.Sale;
import com.example.doantotnghiep.MainActivity;
import com.example.doantotnghiep.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AdapterCustomer extends BaseAdapter {
   private Context context;
   private List<Customer> listkh;


    public void setData(Context context,List<Customer> List) {
        this.context = context;
        this.listkh = List;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (listkh != null) {
            return listkh.size();
        }
        else
            return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;

    }

    private class ViewHolder {
        ImageView imageView;
        TextView tvTen, tvsdt, tvdiachi;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.itemkhachhang, null);

            holder.imageView = (ImageView) convertView.findViewById(R.id.lotical);
            holder.tvTen = (TextView) convertView.findViewById(R.id.tv_tenkh);
            holder.tvsdt = (TextView) convertView.findViewById(R.id.tv_sdtkh);
            holder.tvdiachi = (TextView) convertView.findViewById(R.id.diachikh);
            Customer kh = listkh.get(position);

            holder.tvTen.setText(kh.getHoten());
            holder.tvsdt.setText(kh.getSdt());
            holder.tvdiachi.setText(kh.getDiachi());

            convertView.setTag(holder);
        }
        return convertView;
    }
}
