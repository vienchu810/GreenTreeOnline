package com.example.doantotnghiep.Main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import com.example.doantotnghiep.Adapter.AdapterOder;
import com.example.doantotnghiep.Adapter.AdapterTransport;
import com.example.doantotnghiep.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.example.doantotnghiep.Main.Home.listgh;


public class MainOder extends AppCompatActivity {
    Button btkhachhang, btthanhtoan;
    ListView listView;
    TextView donhang, sdt, dc, tendv, date, chon;
    public static TextView gia, phiship, ten, tamtinh;
    Toolbar toolbartt;
   // objkhachhang kh;
    Random random;
    Spinner spinner;
    //adapvc vc;
  //  public static List<objvanchuyen> vct = new ArrayList<>();

 //   String urldonhang = ketnoisever.adddhoadon;
  //  String urlchitiet = ketnoisever.adddchitiethoadon;
    //        String urldonhang = "http://192.168.0.118/server/hoadon.php";
//    String urlchitiet = "http://192.168.0.118/server/chitiethoadon.php";
    AdapterOder adapterGioHang;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhtoan);
        sharedPreferences = this.getSharedPreferences("luutaikhoan", this.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        toolbartt = findViewById(R.id.toolBarthanhtoan);
        toolbartt.setNavigationIcon(R.drawable.back);
        toolbartt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainOder.this, MainCart.class);
                startActivity(intent);
            }

        });

        anhxa();
        getDataChiTiet();
        giatien();
        date();
        //   getdonvi();
        tamtinh();
    }

//    private void getdonvi() {
//        tendv.setText(sharedPreferences.getString("tendonvi", ""));
//        phiship.setText(sharedPreferences.getString("phiship", ""));
//        Intent intent = getIntent();
//        String ten = intent.getStringExtra("tendonvi");
//        tendv.setText(ten);
//        String phis = intent.getStringExtra("phiship");
//        phiship.setText(phis);

    // }
    private void date() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
        final int month = calendar.get(calendar.MONTH);
        int day = calendar.get(calendar.DAY_OF_MONTH);
        date.setText(new StringBuffer().append(year).append("-").append(month).append("-").append(day));
    }

    private void getDataChiTiet() {
        Intent intent = getIntent();
        String ten1 = intent.getStringExtra("hoten");
        ten.setText(ten1);

        String sdt1 = intent.getStringExtra("sdt");
        sdt.setText(sdt1);
        String diachi1 = intent.getStringExtra("diachi");
        dc.setText(diachi1);
        ;
    }

    public static void giatien() {

        int tongTien = 0;

        for (int i = 0; i < listgh.size(); i++) {
            tongTien += listgh.get(i).tongTien();
        }

        // DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        gia.setText(tongTien + 30000 + " VND");

        //}
    }

    public static void tamtinh() {
        int tongTien = 0;

        for (int i = 0; i < listgh.size(); i++) {
            tongTien += listgh.get(i).tongTien();
        }
        // DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tamtinh.setText(tongTien + "đ");
    }


    public void anhxa() {
        btkhachhang = findViewById(R.id.ttkhachhang);
        listView = (ListView) findViewById(R.id.lvthanhtoan);
        donhang = (TextView) findViewById(R.id.donhang);
        ten = findViewById(R.id.tthoten);
        date = findViewById(R.id.date);
        sdt = findViewById(R.id.ttsdt);
        dc = findViewById(R.id.ttdc);
        chon = findViewById(R.id.chondv);
        gia = findViewById(R.id.sotien);
        //tendv = findViewById(R.id.ten);
        tamtinh = findViewById(R.id.tamtinh);
        phiship = findViewById(R.id.tvphisip);


        spinner = findViewById(R.id.vanchuyen);
        String[] dcs = {"Ninja Van", "Viettel Express", "Grap Express", "NowShip", "Hay để tớ ship"};
        int flags[] = {R.drawable.ninjavan, R.drawable.viettel, R.drawable.grap, R.drawable.now, R.drawable.vienbbb};
       AdapterTransport vc = new AdapterTransport(getApplicationContext(), flags, dcs);
      // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, dc);
      spinner.setAdapter(vc);
        btthanhtoan = findViewById(R.id.bthoadon);
        random = new Random();
        donhang.setText(String.valueOf(random.nextInt(100000)));
        adapterGioHang = new AdapterOder(getApplicationContext(), listgh);
        listView.setAdapter(adapterGioHang);


        btthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ThanhToan();
                doThanhToan();

            }
        });
        btkhachhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(thanhtoan.this, khachhang.class);
             //   startActivity(intent);
            }
        });
//        chon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent1 = new Intent(thanhtoan.this, vanchuyen.class);
////                startActivity(intent1);
//            }
//        });
    }

    private void doThanhToan() {
        final String dh = donhang.getText().toString().trim();
        final String ten1 = ten.getText().toString();
        final String sdt1 = sdt.getText().toString();
        final String diachi = dc.getText().toString();
        final String date1 = date.getText().toString().trim();
        final String phiship1 = phiship.getText().toString().trim();
        final String tamtinh1 = tamtinh.getText().toString().trim();
        final String tien = gia.getText().toString().trim();

        if (ten1.isEmpty() || sdt1.isEmpty() || diachi.isEmpty()) {
            Toast.makeText(this, "Bạn chưa nhập đủ dữ liệu!", Toast.LENGTH_SHORT).show();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Xác nhận thanh toán ");


        builder.setNegativeButton("Không ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
             //   startActivity(new Intent(getApplicationContext(), thanhtoan.class));
            }
        });
        builder.show();
    }

}