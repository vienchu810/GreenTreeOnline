package com.example.doantotnghiep.Main.Bill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doantotnghiep.Adapter.AdapterBill;
import com.example.doantotnghiep.Adapter.AdapterOder;
import com.example.doantotnghiep.Class.DetailOder;
import com.example.doantotnghiep.Class.Product;
import com.example.doantotnghiep.MainActivity;
import com.example.doantotnghiep.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.List;

import static com.example.doantotnghiep.Fragment.FragmentHome.listgh;

public class MainInfoBill extends AppCompatActivity {

    // region Variable

    private DecimalFormat formatPrice = new DecimalFormat("###,###,###");
    public static final String TAG = MainInfoBill.class.getName();
    private DetailOder order;
    private MainActivity home;

    private View mView;
    private Button btnOrderInfoBack;
    private TextView tvOrderInfoNo,tvOrderInfoDate,tvOrderInfoCustName,tvOrderInfoCustAddress
            ,tvOrderInfoCustPhone,tvOrderInfoNum,tvOrderInfoTotal,tvOrderInfoStatus;
    private ListView rcvOrderInfo;

    private AdapterOder orderInfoAdapter;
    private List<DetailOder> listDetailOrder;
    // endregion Variable


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailbill);

        // Khởi tạo các item
        initItem();

        // Set nội dung cho các item
        setContentOrder();

        // Set data cho OrderInfoAdapter
        setDataOrderInfoAdapter();

        findDetailOrder();
    }

    // region Private menthod

    // Khởi tạo các item
    private void initItem(){
        orderInfoAdapter = new AdapterOder(getApplicationContext(), listgh);
        // home = (Home) getActivity();
        tvOrderInfoNo = findViewById(R.id.ctdonhang );
        tvOrderInfoDate = findViewById(R.id.date);
        tvOrderInfoCustName = findViewById(R.id.cthoten);
        tvOrderInfoCustAddress = findViewById(R.id.ctdc);
        tvOrderInfoCustPhone =findViewById(R.id.ctsdt);
        tvOrderInfoNum = findViewById(R.id.cttamtinh);
        tvOrderInfoTotal = findViewById(R.id.ctsotien);
        tvOrderInfoStatus = findViewById(R.id.tinhtrang);
        rcvOrderInfo = findViewById(R.id.lvdonhang);
        //    btnOrderInfoBack = mView.findViewById(R.id.btn_order_info_back);
//        btnOrderInfoBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (getFragmentManager() != null){
//                    getFragmentManager().popBackStack();
//                }
//            }
//        });
    }

    // Set nội dung cho các item
    private void setContentOrder(){
        Intent intent = getIntent();
        order = (DetailOder) intent.getSerializableExtra("bill");
        tvOrderInfoNo.setText(order.getMadonhang().toUpperCase());
        tvOrderInfoDate.setText(order.getNgay());
        tvOrderInfoCustName.setText(order.getTenkh());
        tvOrderInfoCustAddress.setText(order.getDiachi());
        tvOrderInfoCustPhone.setText(order.getSdt());
        tvOrderInfoNum.setText(String.valueOf(order.getTamtinh()));
        tvOrderInfoTotal.setText(order.getTongtien());
        tvOrderInfoStatus.setText(order.getStatus());
    }

    // Set data cho OrderInfoAdapter
    private void setDataOrderInfoAdapter(){
     //   orderInfoAdapter.setData(this,order.getListDetailOrder(),order.getCartList(),home);
      //  LinearLayoutManager linearLayoutManager = new LinearLayoutManager(home, RecyclerView.VERTICAL,false);
       // rcvOrderInfo.setLayoutManager(linearLayoutManager);
        orderInfoAdapter = new AdapterOder(getApplicationContext(), listgh);
        rcvOrderInfo.setAdapter(orderInfoAdapter);
    }
    private void findDetailOrder( ){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Order");

        //if (order.getListDetailOrder().size() > 0){
            //for (int i = 0; i<order.getCartList().size(); i++){
              //  DetailOder order = dataOrder.getValue(DetailOder.class);
                myRef.child(String.valueOf(order.getMadonhang())).child("detail").addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        orderInfoAdapter.notifyDataSetChanged();

                        for (DataSnapshot data : snapshot.getChildren()) {
                            DetailOder product = data.getValue(DetailOder.class);
                            Log.d("onDataChange: ", String.valueOf(product));
                            product.setMadonhang(data.getKey());
                            Log.d("aaa", data.getKey());
                        }
                        //  setProductSearchAdapter(mListProduct);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainInfoBill.this, "Không tải được dữ liệu từ firebase" + error.toString(), Toast.LENGTH_LONG).show();
                        Log.d("MYTAG", "onCancelled" + error.toString());
                    }
                });

    }

   // }
    // endregion Private menthod

}