package com.example.doantotnghiep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.doantotnghiep.Class.Product;
import com.example.doantotnghiep.Fragment.FragmentDashboard;
import com.example.doantotnghiep.Fragment.FragmentHome;
import com.example.doantotnghiep.Fragment.FragmentProfile;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNav;
    private List<Product> listCartProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER,
//                WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,
                    new FragmentHome()).commit();

        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.trangchu:
                           selectedFragment = new FragmentHome();
                            break;
//                        case R.id.dao:
//                            selectedFragment = new dodung();
//                            break;
//                        case R.id.yeuthich:
//                           selectedFragment = new yeuthich();
//                            break;
                        case R.id.taikhoan:
                            selectedFragment = new FragmentDashboard();
                            break;
                        //case R.id.thongke:
                          //  selectedFragment = new admin();
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment,
                            selectedFragment).commit();

                    return true;
                }
            };

 //   }
}