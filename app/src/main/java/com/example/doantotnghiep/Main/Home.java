package com.example.doantotnghiep.Main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.doantotnghiep.Adapter.AdaperSale;
import com.example.doantotnghiep.Adapter.SlidePhotoAdapter;
import com.example.doantotnghiep.Adapter.AdapterHome;
import com.example.doantotnghiep.Class.Cart;
import com.example.doantotnghiep.Class.Sale;
import com.example.doantotnghiep.Class.SlidePhoto;
import com.example.doantotnghiep.Class.Product;
import com.example.doantotnghiep.MainActivity;
import com.example.doantotnghiep.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class Home extends Fragment {

    private ViewPager viewPagerSlidePhoto;
    private Timer mTimer;
    private View mView;

    private RecyclerView rcvProduct, rcvSale;
    private CircleIndicator circleIndicator;
    private MainActivity home;
    ///Class
    List<SlidePhoto> listSlidePhoto = new ArrayList<>();
    private List<Product> productList;
    private List<Sale> listsale;
    ///Adapter
    //private AdaperSlide adaperSlide;
   private SlidePhotoAdapter slidePhotoAdapter;
    private AdapterHome adapterhome;
    private AdaperSale adapterSale;
///
public static ArrayList<Cart> listgh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_home, container, false);

        // Khởi tạo các item
        initItem();

        // Set Adapter cho viewPagerSlidePhoto
        setDataSlidePhotoAdapter();

        // Set Adapter cho rcvProduct
        setDataProductAdapter();

        //Set Adapter cho rcvSale
        setDataSaleAdapter();
        return mView;
    }

    // Khởi tạo các item
    private void initItem() {
        rcvProduct = mView.findViewById(R.id.rcv_product);
        rcvSale = mView.findViewById(R.id.review);

        viewPagerSlidePhoto = mView.findViewById(R.id.vp_slide_photo);
        circleIndicator = mView.findViewById(R.id.circle_indicator);
        //atcProductSearch = mView.findViewById(R.id.atc_product_search);

        listSlidePhoto = getListSlidePhoto();
        productList = getDataProduct();
        listsale= getDataSale();

        if (listgh != null) {
        } else {
            listgh = new ArrayList<com.example.doantotnghiep.Class.Cart>();
        }
    }

    // Set Adapter cho viewPagerSlidePhoto
    private void setDataSlidePhotoAdapter() {


        slidePhotoAdapter = new SlidePhotoAdapter(listSlidePhoto, this);
        viewPagerSlidePhoto.setAdapter(slidePhotoAdapter);
       circleIndicator.setViewPager(viewPagerSlidePhoto);
        slidePhotoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());

        // Auto chuyển các slide photo
        autoSildeImage();
    }

    // Lấy Product để vào slide
    private List<SlidePhoto> getListSlidePhoto() {

        listSlidePhoto.add(new SlidePhoto("https://cdn.thongtinduan.com/uploads/posts/2019-08/uu-nhuoc-diem-va-cach-bo-tri-5-kieu-tieu-canh-duoc-nhieu-gia-chu-chuong-dung-nhat-9.jpg"));
        listSlidePhoto.add(new SlidePhoto("https://sendalongphung.com/wp-content/uploads/2018/09/0-cay-canh-trong-trong-nha.jpg"));
        listSlidePhoto.add(new SlidePhoto("https://bancongxanh.com/wp-content/uploads/2019/04/Gi%C3%A1-c%C3%A2y-c%E1%BA%A3nh-v%C4%83n-ph%C3%B2ng-%C4%90%C3%A0-N%E1%BA%B5ng-m%E1%BB%9Bi-nh%E1%BA%A5t-2019.jpg"));
        listSlidePhoto.add(new SlidePhoto("https://ncppb.com/wp-content/uploads/2019/04/top-10-website-ban-cay-canh.jpg"));
        listSlidePhoto.add(new SlidePhoto("https://afamilycdn.com/2019/9/5/33-1567649196717622170194-crop-15676492145021350614745.jpg"));
        listSlidePhoto.add(new SlidePhoto("https://afamilycdn.com/2019/10/2/20190530beginnerplants0007-1570002818813385100229-crop-157000282538490310658.jpg"));
        listSlidePhoto.add(new SlidePhoto("https://kienviet.net/wp-content/uploads/2019/05/H1-e1559014099672.jpg"));
      //  slidePhotoAdapter = new AdaperSlide(listSlidePhoto, this);
      //  viewPagerSlidePhoto.setAdapter(slidePhotoAdapter,this);
      //  adaperSlide.registerDataSetObserver(circleIndicator.getDataSetObserver());
        return listSlidePhoto;


    }



    private void autoSildeImage() {
        if (listSlidePhoto == null || listSlidePhoto.isEmpty() || viewPagerSlidePhoto == null) {
            return;
        }
        if (mTimer == null) {
            mTimer = new Timer();
        }
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPagerSlidePhoto.getCurrentItem();
                        int totalItem = listSlidePhoto.size() - 1;

                        // Nếu item hiện tại chưa phải cuối cùng
                        if (currentItem < totalItem) {
                            currentItem++;
                            viewPagerSlidePhoto.setCurrentItem(currentItem);
                        } else {
                            viewPagerSlidePhoto.setCurrentItem(0);
                        }
                    }
                });
            }

            // xử lý thêm để set time
        }, 500, 3000);
    }

    // Set Adapter cho rcvProduct
    private void setDataProductAdapter() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(home, 2);
        rcvProduct.setLayoutManager(gridLayoutManager);

        adapterhome = new AdapterHome();
        adapterhome.setData(getContext(), productList, home);

        rcvProduct.setAdapter(adapterhome);
    }

    private List<Product> getDataProduct() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("sanpham");

        List<Product> mListProduct = new ArrayList<>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adapterhome.notifyDataSetChanged();

                for (DataSnapshot data : snapshot.getChildren()) {
                    Product product = data.getValue(Product.class);
                    Log.d("onDataChange: ", String.valueOf(product));
                    product.setIdsp(Integer.parseInt(data.getKey()));
                    mListProduct.add(product);
                    Log.d("aaa", data.getKey());
                }
                //  setProductSearchAdapter(mListProduct);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Không tải được dữ liệu từ firebase"
                        + error.toString(), Toast.LENGTH_LONG).show();
                Log.d("MYTAG", "onCancelled" + error.toString());
            }
        });
        return mListProduct;
    }

    // Set Adapter cho rcvProduct
    private void setDataSaleAdapter() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(home, 1);
        rcvSale.setLayoutManager(gridLayoutManager);

        adapterSale = new AdaperSale();
        adapterSale.setData(listsale, home);

        rcvSale.setAdapter(adapterSale);
    }

    private List<Sale> getDataSale() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("getgiamgia");

        List<Sale> mListSale = new ArrayList<>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adapterSale.notifyDataSetChanged();

                for (DataSnapshot data : snapshot.getChildren()) {
                    Sale sale = data.getValue(Sale.class);
                    Log.d("onDataChange: ", String.valueOf(sale));
                    sale.setId(data.getKey());
                    mListSale.add(sale);
                    Log.d("aaa", data.getKey());
                }
                //  setProductSearchAdapter(mListProduct);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                rcvSale.setLayoutManager(layoutManager);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Không tải được dữ liệu từ firebase"
                        + error.toString(), Toast.LENGTH_LONG).show();
                Log.d("MYTAG", "onCancelled" + error.toString());
            }

        }
        );

        return mListSale;
    }
}