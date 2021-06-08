package com.example.doantotnghiep.Main.Login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.example.doantotnghiep.Class.Profile;
import com.example.doantotnghiep.MainActivity;
import com.example.doantotnghiep.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.eventbus.EventBus;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Login extends AppCompatActivity {

    public SharedPreferences saveInfoAccount;
    Button btn_login, btn_login_admin;
    EditText userName_login, password_login;
    String parentDbName = "login";
    TextView bt_redirect_logout;
    int role_id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap);

        saveInfoAccount = getSharedPreferences("saveInfo", Context.MODE_PRIVATE);
        init();

    }

    public void init() {
        userName_login = findViewById(R.id.txttaikhoan);
        password_login = findViewById(R.id.txtmatkhau);
        btn_login = findViewById(R.id.btn_dangnhap);
        bt_redirect_logout = findViewById(R.id.TxtRegister);
       // btn_login_admin = findViewById(R.id.btn_login_admin);

        bt_redirect_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DangKy.class));
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Login();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });


    }

    private void Login_Admin() {

    }

    private void Login() {
        String userName = userName_login.getText().toString();
        String password = password_login.getText().toString();

        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "Vui lòng nhập tên đăng nhập !!!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Vui lòng nhập mật khẩu !!!", Toast.LENGTH_SHORT).show();
        } else {
            final DatabaseReference data;
            data = FirebaseDatabase.getInstance().getReference();
            data.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.child("login").child(userName).exists()) {
                        Profile dataUsers = snapshot.child("login").child(userName).getValue(Profile.class);
                        if (dataUsers.getTaikhoan().contains(userName)) {
                            if (dataUsers.getMatkhau().contains(password)) {
                                // khai báo
                                String userName = dataUsers.getTaikhoan();
                                String password = dataUsers.getTaikhoan();
                                int phone = dataUsers.getSdt();
                                String mail = dataUsers.getGmail();
                                String birthday = dataUsers.getDated();
                                // lưu dữ liệu vào bộ nhớ SharedPreferences
                                SharedPreferences.Editor editor = saveInfoAccount.edit();
                                editor.putString("taikhoan", userName);
                                editor.putString("matkhau", password);
                                editor.putInt("sdt", phone);
                                editor.putString("gmail", mail);
                                editor.putString("dated", birthday);
                                editor.commit();
                                // kiểm tra đăng nhập
                                Toast.makeText(Login.this, "Đăng nhập thành công !!!!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                //   EventBus.getDefault().post(true,"loginSuccess");
                            }

                        }

                    } else {
                        Toast.makeText(Login.this, "Đăng nhập thất bại:", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


//        forgotTextLink.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                final EditText resetMail = new EditText(v.getContext());
//                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
//                passwordResetDialog.setTitle("Reset Password ?");
//                passwordResetDialog.setMessage("Enter Your Email To Received Reset Link.");
//                passwordResetDialog.setView(resetMail);
//
//                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // extract the email and send reset link
//                        String mail = resetMail.getText().toString();
//                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                Toast.makeText(Login.this, "Reset Link Sent To Your Email.", Toast.LENGTH_SHORT).show();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(Login.this, "Error ! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
//                    }
//                });
//
//                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // close the dialog
//                    }
//                });
//
//                passwordResetDialog.create().show();
//
//            }
//        });
//
//
//    }
    }
}


