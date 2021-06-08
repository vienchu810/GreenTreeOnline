package com.example.doantotnghiep.Main.Bill;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doantotnghiep.Adapter.AdapterBill;
import com.example.doantotnghiep.Class.Cart;
import com.example.doantotnghiep.Class.DetailOder;
import com.example.doantotnghiep.MainActivity;
import com.example.doantotnghiep.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainBill extends AppCompatActivity {

    // region Variable

    private MainActivity home;
    private List<Cart> listOrder;
    private List<DetailOder> listDetailOrder;

    private View mView;
//    private EditText edtHistoryPhone;
//    private Button btnHistorySearch;
    private RecyclerView rcvHitorySearch;

    private AdapterBill historyProductAdapter;

    // endregion Variable

    public MainBill() {
    }

    @Override
    public void onResume() {
        super.onResume();

        // Khi quay lại từ fragment OrderInfo sẽ thực hiện tìm kiếm lại
//        if (!edtHistoryPhone.getText().toString().trim().isEmpty()){
//            findOrder();
//        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        initItem();

        setDataHistoryProductAdapter();
    }

    // region Private menthod

    // Khởi tạo các item
    private void initItem(){
        listOrder = new ArrayList<>();
        listDetailOrder = new ArrayList<>();

      //  home = getClass();

        historyProductAdapter = new AdapterBill();

     //   edtHistoryPhone = findViewById(R.id.edt_history_phone);

        rcvHitorySearch = findViewById(R.id.rcv_hitory_search);
        findOrder();
//        btnHistorySearch = findViewById(R.id.btn_history_search);
//        btnHistorySearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                findOrder();
//            }
//        });
    }

    // set data cho HistoryProductAdapter
    private void setDataHistoryProductAdapter(){
        historyProductAdapter.setData(this,listDetailOrder,listOrder,home);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(home, RecyclerView.VERTICAL,false);
        rcvHitorySearch.setLayoutManager(linearLayoutManager);
        rcvHitorySearch.setAdapter(historyProductAdapter);
    }

    // Lấy thông tin order
    private void findOrder(){

        // Clear các list dữ liệu khi tìm kiếm
      //  listOrder.clear();
        listDetailOrder.clear();

        // Kết nối tới data base
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Order");

        // Lấy thông tin order
//        myRef.orderByChild("custPhone").equalTo(edtHistoryPhone.getText().toString())
//                .addValueEventListener(new ValueEventListener() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
              historyProductAdapter.notifyDataSetChanged();
                for (DataSnapshot dataOrder : snapshot.getChildren()){
                    DetailOder order = dataOrder.getValue(DetailOder.class);
                   Log.d("onDataChange: ", String.valueOf(order));
                    order.setMadonhang(dataOrder.getKey());
                    listDetailOrder.add(order);
                    Log.d("voo đâyy   ", dataOrder.getKey());
                }

                if (listDetailOrder.size() > 0){
                    // Lấy thông tin detail order
                  // findDetailOrder(myRef);
               }
               else {
                   // findDetailOrder(myRef);
                    Toast.makeText(MainBill.this,"Không tìm thấy lịch sử đặt hàng",Toast.LENGTH_SHORT).show();
               }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainBill.this,"Không lấy được thông tin đơn hàng từ firebase",Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Lấy thông tin detail order
//    private void findDetailOrder( DatabaseReference myRef){
//        if (listDetailOrder.size() > 0){
//            for (int i = 0; i<listOrder.size(); i++){
//                DetailOder order = listDetailOrder.get(i);
//                myRef.child(String.valueOf(order.getMadonhang())).child("detail").addValueEventListener(new ValueEventListener() {
//
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for (DataSnapshot dataDetail : snapshot.getChildren()){
//                            historyProductAdapter.notifyDataSetChanged();
//                            DetailOder detailOrder = dataDetail.getValue(DetailOder.class);
//                            listDetailOrder.add(detailOrder);
//                            Log.d(String.valueOf(detailOrder), "ơ đây: ");
//                        }
//
//                        // set data HistoryProductAdapter
//                        if (listDetailOrder.size() > 0){
//                            setDataHistoryProductAdapter();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Toast.makeText(MainBill.this,"Không lấy được chi tiết đơn hàng từ firebase",Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        }
//    }

    // endregion Private menthod

}