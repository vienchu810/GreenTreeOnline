package com.example.doantotnghiep.Main.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doantotnghiep.Class.Profile;
import com.example.doantotnghiep.MainActivity;
import com.example.doantotnghiep.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;


public class DangKy extends AppCompatActivity {
    EditText userName_register, password_register, phone_register, fulName_register, email_register, birthDay_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        init();
    }

    public void init() {
        fulName_register = (EditText) findViewById(R.id.fullName);
        password_register = (EditText) findViewById(R.id.password);
        phone_register = (EditText) findViewById(R.id.phone);
        email_register = (EditText) findViewById(R.id.Email);
        birthDay_register = (EditText) findViewById(R.id.date);
        Button btn_register = (Button) findViewById(R.id.registerBtn);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

    }

    public void createUser() {
        String userName = fulName_register.getText().toString();
        String password = password_register.getText().toString();
        String phone = phone_register.getText().toString();
        String mail = email_register.getText().toString();
        String birthDay = birthDay_register.getText().toString();
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "Vui lòng nhập tên đăng nhập", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Vui lòng nhập sô điện thoại", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(mail)) {
            Toast.makeText(this, "Vui lòng nhập gmail", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(birthDay)) {
            Toast.makeText(this, "Vui lòng nhập năm sinh", Toast.LENGTH_SHORT).show();
        }else {
            postUser(userName,password,phone,birthDay,mail);
        }
    }

    private void postUser(String fulName, String password, String phone, String birthDay, String mail) {
        DatabaseReference data;
        data = FirebaseDatabase.getInstance().getReference();
        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("login").child(fulName).exists())){
                    HashMap<String,Object> userdataMap =new HashMap<>();
                    userdataMap.put("taikhoan",fulName);
                    userdataMap.put("matkhau",password);
                    userdataMap.put("sdt",phone);
                    userdataMap.put("dated",birthDay);
                    userdataMap.put("mail",mail);
                    data.child("login").child(fulName).updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(DangKy.this, "Thành công", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),Login.class));
                            }else {
                                Toast.makeText(DangKy.this, "Kết nối thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else {
                    Toast.makeText(DangKy.this, "Thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
