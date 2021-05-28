package com.example.doantotnghiep.Main.Login;

import android.app.ProgressDialog;
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
import android.widget.ProgressBar;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import com.example.doantotnghiep.MainActivity;
import com.example.doantotnghiep.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    // public static final String TAG = dangnhap.class.getSimpleName();
    // objacc acc;
    private EditText edttk;
    private EditText edtmk;
    private EditText edtEmail;
    private Button dangky;
    private Button btnLogin;
    private ProgressDialog pDialog;
    Toolbar toolbar;
    String tentaikhoan;
    String matkhau;
    ProgressBar loading;
    // public static final String login = ketnoisever.login;

    public static final String KEY_USERNAME = "taikhoan";
    public static final String KEY_PASSWORD = "matkhau";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dangnhap);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        //  EventBus.getDefault().register(this);
//        toolbar = (Toolbar) findViewById(R.id.toolBardangnhap);
        // toolbar.setNavigationIcon(R.drawable.back2);
//        toolbar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//
//        });

        sharedPreferences = this.getSharedPreferences("luutaikhoan", this.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        addControl();
       btdangnhap();

//        dangky.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //  Intent intent = new Intent(dangnhap.this, dangky.class);
//                //startActivity(intent);
//            }
//        });
    }

    private void addControl() {
        edttk = (EditText) findViewById(R.id.taikhoan);
        edtmk = (EditText) findViewById(R.id.matkhau);
        btnLogin =  findViewById(R.id.btn_dangnhap);
     //   dangky = (Button) findViewById(R.id.btdangky);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Đang đăng nhập...");
        pDialog.setCanceledOnTouchOutside(false);
    }

    public void btdangnhap() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //   Login(view);
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }

    public void Login(View view) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("login");
        Map<String, Object> map = new HashMap<>();
        map.put("taikhoan",edttk.getText().toString());
        map.put("matkhau",edtmk.getText().toString());
        if (edttk.getText().toString().equals("")) {
            edttk.setError("Vui lòng nhập tên tài khoản");
        } else if (edtmk.getText().toString().equals("")) {
            edtmk.setError("Vui lòng nhập mật khẩu");
        } else {
            tentaikhoan = edttk.getText().toString().trim();
            matkhau = edtmk.getText().toString().trim();
            progressDialog.setMessage("Đang đăng nhập...");
            progressDialog.show();

        }
    }


}


