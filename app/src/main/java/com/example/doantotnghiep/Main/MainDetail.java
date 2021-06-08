package com.example.doantotnghiep.Main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.doantotnghiep.Class.Cart;
import com.example.doantotnghiep.Class.Sale;
import com.example.doantotnghiep.Class.Product;
import com.example.doantotnghiep.MainActivity;
import com.example.doantotnghiep.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import static com.example.doantotnghiep.Fragment.FragmentHome.listgh;



public class MainDetail extends AppCompatActivity {
    Product sp;
    Sale sl;
    ImageView imgcayct, yt;
   // objgiohang gh;
    TextView ctcay, ctgia, ctmota;
    MaterialToolbar back;
    Integer[] soluong;
    EditText edgiatri;
    Button tang, giam, mg;
    FloatingActionButton call,cart;

    ImageButton addgh;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsanpham);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        Init();

        soluong();
        // muangay();
        // sale();
        AddCart();
        onclick();
//      getdatacay();
        getDataChiTiet();
    }

    private void Init() {
        imgcayct = (ImageView) findViewById(R.id.imageView);
        ctcay = (TextView) findViewById(R.id.tensp);
        ctgia = (TextView) findViewById(R.id.giathanh);
        ctmota = (TextView) findViewById(R.id.dacdiem);
        tang = findViewById(R.id.butontang);
        giam = findViewById(R.id.butongiam);
        edgiatri = findViewById(R.id.edtgiatri);
        call = findViewById(R.id.call);
        back = findViewById(R.id.toolbar);
        mg = findViewById(R.id.muangay);
        addgh = (ImageButton) findViewById(R.id.fab);
        sharedPreferences = this.getSharedPreferences("luutaikhoan", this.MODE_PRIVATE);
        editor = sharedPreferences.edit();
cart= findViewById(R.id.ftb_cart);
     //  yt = findViewById(R.id.like);
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
//        yt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String TenTk = sharedPreferences.getString("taikhoan", "");
//                if (!TextUtils.isEmpty(TenTk)) {
//
//                } else {
//                    Toast.makeText(Chitietsanpham.this, "Bạn cần đăng nhập", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(getApplicationContext(), Login.class));
//
//                    finish();
//
//
//                }
//            }
//        });

    }


    public void onclick() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainDetail. this, MainActivity.class);
                startActivity(intent);
            }
        });
//        call.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final AlertDialog.Builder builder = new AlertDialog.Builder(Chitietsanpham.this);
//                builder.setTitle("Bạn có muốn gọi cho chủ shop ?");
//
//                builder.setNegativeButton("Call", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        Intent intent = new Intent(Intent.ACTION_CALL);
//                        intent.setData(Uri.parse("tel:0979951954"));
//                        if (ActivityCompat.checkSelfPermission(Chitietsanpham.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                            reques();
//                            // TODO: Consider calling
//                            //    Activity#requestPermissions
//                        } else {
//                            startActivity(intent);
//
////                if (trangchu.sp.size() > 0) {
////                    if (thongtin.acc.size() == 0) {
////                        Intent intent = new Intent(chitietsanpham.this, dangnhap.class);
////                        startActivity(intent);
////                    } else {
////                        Intent intent = new Intent(chitietsanpham.this, thanhtoan.class);
////                        startActivity(intent);
////                    }
////
////                }
//
//                        }
//                    }
//                });
//                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                builder.show();
//                //   return false;
//
//            }
//
//            private void reques() {
//                ActivityCompat.requestPermissions(Chitietsanpham.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
//            }
//
//        });

    }
    public void getDataChiTiet() {
        Intent intent = getIntent();
        sp = (Product) intent.getSerializableExtra("sanpham");
        Picasso.get().load(sp.getIgmsp()).into(imgcayct);
        ctcay.setText(sp.getTensp());
        DecimalFormat decimalformat = new DecimalFormat("###,###,###");
        ctgia.setText(decimalformat.format(sp.getGiasp()) + " VNĐ");
        ctmota.setText(sp.getMota());
    }
//    public void yeuthich() {
//
//        yt.setVisibility(View.INVISIBLE);
//        RequestQueue queue = Volley.newRequestQueue(chitietsanpham.this);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlyt, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d("load ", response.toString());
//                if (response != null) {
//                    //for (int i = 0; i < vc.; i++) {
//                    // String tensp = vc.get(i).getTensp();
//                    Toast.makeText(chitietsanpham.this, "Đã thêm vào danh sách yêu thích!", Toast.LENGTH_SHORT).show();
//                    // startActivity(new Intent(chitietsanpham.this, giohang.class));
//                    // }
//                } else {
//                    Toast.makeText(chitietsanpham.this, "Lỗi thêm ", Toast.LENGTH_SHORT).show();
//                }
//            }
//        },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(chitietsanpham.this, error.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> pra = new HashMap<>();
//                int id = sharedPreferences.getInt("id", 0);
//                pra.put("idtk", String.valueOf(id));
//                pra.put("idyt", String.valueOf(sp.getIdsp()));
//                pra.put("tensp", ctcay.getText().toString().trim());
//                pra.put("gia", String.valueOf(sp.getGt()));
//                pra.put("mota", ctmota.getText().toString().trim());
//                pra.put("img", sp.getImgsp());
//                return pra;
//            }
//        };
//
//        queue.add(stringRequest);
//
//
//    }

    ;



    public void soluong() {
        tang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int slmoi = Integer.parseInt(edgiatri.getText().toString()) + 1;

                edgiatri.setText(slmoi + "");


                if (slmoi > 100) {
                    tang.setVisibility(View.INVISIBLE);
                    giam.setVisibility(View.VISIBLE);
                    edgiatri.setText(slmoi + "");
                } else {
                    tang.setVisibility(View.VISIBLE);
                    giam.setVisibility(View.VISIBLE);
                    edgiatri.setText(slmoi + "");
                }
            }
        });
        giam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int slmoi = Integer.parseInt(edgiatri.getText().toString()) - 1;

                edgiatri.setText(slmoi + "");
                if (slmoi < 1) {
                    tang.setVisibility(View.VISIBLE);
                    giam.setVisibility(View.INVISIBLE);
                    edgiatri.setText(slmoi + "");
                } else {
                    tang.setVisibility(View.VISIBLE);
                    giam.setVisibility(View.VISIBLE);
                    edgiatri.setText(slmoi + "");
                }
            }
        });

    }

    private void AddCart() {
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   if (listgh.size()>0) {
                boolean exist = false;
                for (int i = 0; i < listgh.size(); i++) {
                    if (listgh.get(i).getIdgh() == sp.getIdsp()) {
                        int soLuong = Integer.parseInt(edgiatri.getText().toString().trim());
                        int sl = soLuong + listgh.get(i).getSl();
                        if (sl > 100) {
                            listgh.get(i).setSl(100);
                        } else {
                            listgh.get(i).setSl(sl);
                        }
                        exist = true;
                    }
                }
                if (listgh.size() > 0) {
                    int soLuong = Integer.parseInt(edgiatri.getText().toString().trim());
                    Cart gioHang = new Cart(sp.getIdsp(), sp.getTensp(), sp.getIgmsp(), sp.getGiasp(), soLuong);
                    listgh.add(gioHang);


                } else {
                    int soLuong = Integer.parseInt(edgiatri.getText().toString().trim());
                    Cart gioHang = new Cart(sp.getIdsp(), sp.getTensp(), sp.getIgmsp(), sp.getGiasp(), soLuong);
                    listgh.add(gioHang);
                }
                startActivity(new Intent(MainDetail.this, MainCart.class));
                //  }
               // Toast.makeText(Chitietsanpham.this,"Đã thêm"+listgh.size()+"vào giỏ hàng",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
