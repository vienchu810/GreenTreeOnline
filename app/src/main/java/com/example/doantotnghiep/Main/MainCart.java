package com.example.doantotnghiep.Main;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.example.doantotnghiep.Adapter.AdapterCart;
import com.example.doantotnghiep.Fragment.FragmentHome;
import com.example.doantotnghiep.MainActivity;
import com.example.doantotnghiep.R;

import java.text.DecimalFormat;

import static com.example.doantotnghiep.Fragment.FragmentHome.listgh;


public class MainCart extends AppCompatActivity {

    public static TextView tvTongTien;
    Button btthanhtoan, ttmuahag;
    ListView lvGioHang;
    AdapterCart adapterGioHang;
    TextView sosanpham;
    String demsoSp;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        sharedPreferences = this.getSharedPreferences("luutaikhoan", this.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        anhxa();
        xoa();
        onclick();
        tongtien();
    }

    public void anhxa() {
        lvGioHang = (ListView) findViewById(R.id.lvGioHang);
        tvTongTien = (TextView) findViewById(R.id.tongtien);
        ttmuahag = (Button) findViewById(R.id.ttmuahang);
        btthanhtoan = (Button) findViewById(R.id.thanhtoan);
        //dem so san pham trong cart
        sosanpham = findViewById(R.id.numbercart);
        int dem = listgh.size();
        Log.d( "anhxa: ", String.valueOf(dem));
      //  demsoSp = sosanpham.getText().toString();
      //  if (demsoSp.trim().isEmpty()) {
           // demsoSp = "(" + dem + ")";
//            Log.d("demsoSp ", demsoSp);
            sosanpham.setText("("+dem+")");
       // }
        adapterGioHang = new AdapterCart(getApplicationContext(), listgh);
        lvGioHang.setAdapter(adapterGioHang);
    }

    public void onclick() {
        ttmuahag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainCart.this, MainActivity.class);
                startActivity(intent);

            }
        });
        btthanhtoan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (FragmentHome.listgh.size() > 0) {
//                    if (thongtin.acc.size() == 0) {
//                        Intent intent = new Intent(giohang.this, dangnhap.class);
//                        startActivity(intent);
//                    } else {
//                        Intent intent = new Intent(giohang.this, thanhtoan.class);
//                        startActivity(intent);
//                    }
//                } else {
//                    Toast.makeText(giohang.this, "Bạn chưa nhập sản phẩm nào cho giỏ hàng!", Toast.LENGTH_SHORT).show();
//                    return;

                    String TenTk = sharedPreferences.getString("taikhoan", "");
                    if (!TextUtils.isEmpty(TenTk)) {
                        // startActivity(new Intent(getApplicationContext(),thanhtoan.class));
                    } else {
                        Toast.makeText(MainCart.this, "Bạn cần đăng nhập", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainOder.class));

                        finish();


                    }
                }

            }
        });

    }


    public void xoa() {
        lvGioHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainCart.this);
                builder.setTitle("Bạn có muốn xoá sản phẩm này?");

                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listgh.remove(position);
                        adapterGioHang.notifyDataSetChanged();
                        tongtien();
                    }
                });
                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                return false;
            }
        });
    }

    public static void tongtien() {

        int tongTien = 0;
        for (int i = 0; i < listgh.size(); i++) {
            tongTien += listgh.get(i).tongTien();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvTongTien.setText("đ " + decimalFormat.format(tongTien));
        //}
    }


}

