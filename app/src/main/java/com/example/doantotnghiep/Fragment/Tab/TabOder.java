package com.example.doantotnghiep.Fragment.Tab;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doantotnghiep.Main.Bill.MainBill;
import com.example.doantotnghiep.R;

public class TabOder extends Fragment {
TextView bill;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_oder
                , container, false);
        bill= view.findViewById(R.id.historioder);
        SetOnClick();
        return view;
    }
    public void  SetOnClick(){
        bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainBill.class);
                startActivity(intent);
            }
        });
    }
}