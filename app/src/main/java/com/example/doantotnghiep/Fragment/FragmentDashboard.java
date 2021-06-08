package com.example.doantotnghiep.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.doantotnghiep.Adapter.AdapterDashboard;
import com.example.doantotnghiep.Adapter.AdapterHome;
import com.example.doantotnghiep.Class.Profile;
import com.example.doantotnghiep.Main.Login.Login;
import com.example.doantotnghiep.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.concurrent.Executor;

import javax.annotation.Nullable;


public class FragmentDashboard extends Fragment {
    public static TextView hoten1, gioitinh, date, mail, sdt1, dc, cho;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    Button resendCode;
    Button resetPassLocal,changeProfileImage;
    FirebaseUser user;
    ImageView profileImage;
    StorageReference storageReference;
    public static ArrayList<Profile> sp;
    AdapterHome adapcay;
    TabLayout tabLayout;
ViewPager viewPager;
    //public static List<objacc> acc = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    //////////////////////////////////
    private SharedPreferences sharedPreferences1;
    private SharedPreferences.Editor editor1;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_dashboard, container, false);
        tabLayout=view.findViewById(R.id.tabs);
        viewPager=view.findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Đơn hàng"));
        tabLayout.addTab(tabLayout.newTab().setText("Thông tin"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final AdapterDashboard adapter = new AdapterDashboard(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        return view;
    }


    public void Init() {
}


}
