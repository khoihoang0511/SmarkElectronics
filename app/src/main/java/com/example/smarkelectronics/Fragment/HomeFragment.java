package com.example.smarkelectronics.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.smarkelectronics.Activity.CartActivity;
import com.example.smarkelectronics.Activity.CategoryActivity;
import com.example.smarkelectronics.Activity.ProductActivity;
import com.example.smarkelectronics.Activity.SignUpActivity;
import com.example.smarkelectronics.Adapter.AdapterProduct;
import com.example.smarkelectronics.Adapter.ImageSliderAdapter;
import com.example.smarkelectronics.R;
import com.example.smarkelectronics.api.API;
import com.example.smarkelectronics.Model.product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {

    ImageSliderAdapter imageSliderAdapter;
    ViewPager2 viewPager2;
    private ViewPager2.OnPageChangeCallback onPageChangeCallback;

    // Api
    private ProgressDialog progressDialog;
    private Handler handlerproduct = new Handler();

    AdapterProduct adapterProduct;

    ArrayList<product> listproduct;
    ArrayList<product> all;
    RecyclerView recyclerView;
    SearchView searchsanpham;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    private int currentPage = 0;
    private final Handler handler = new Handler();
    private final int delay = 2600; // 5 giây (thời gian trễ giữa các trang)


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager2 = view.findViewById(R.id.view_paper);
        recyclerView = view.findViewById(R.id.rcvproduct);
        searchsanpham = view.findViewById(R.id.searchsanpham);
        ImageView imgcartHome = view.findViewById(R.id.imgcartHome);

        Button btnAllCategory = view.findViewById(R.id.btnAllCategory);
        Button btnPhoneCategory = view.findViewById(R.id.btnPhoneCategory);
        Button btnLaptopCategory = view.findViewById(R.id.btnLaptopCategory);
        Button btnIpadCategory = view.findViewById(R.id.btnIpadCategory);
        Button btnEarphoneCategory = view.findViewById(R.id.btnEarphoneCategory);
        Button btnScreenCategory = view.findViewById(R.id.btnScreenCategory);
        Button btnAccessoryCategory = view.findViewById(R.id.btnAccessoryCategory);


        listproduct = new ArrayList<>();
        all = new ArrayList<>();


        //---hiển thị danh sách ảnh
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.img_slider3);
        list.add(R.drawable.img_slider2);
        list.add(R.drawable.img_slider);
        list.add(R.drawable.img_slider4);
        list.add(R.drawable.img_slider5);

        imageSliderAdapter = new ImageSliderAdapter(list);
        viewPager2.setAdapter(imageSliderAdapter);
        //---hiển thị danh sách ảnh



        //----slider tự động
         Runnable runnable = new Runnable() {
            public void run() {
                if (currentPage == imageSliderAdapter.getItemCount() - 1) {
                    currentPage = 0;
                } else {
                    currentPage++;
                }
                viewPager2.setCurrentItem(currentPage);
                handler.postDelayed(this, delay);
            }
        };

        handler.postDelayed(runnable, delay);
        viewPager2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                handler.removeCallbacks(runnable);
                return false;
            }
        });
        //----slider tự động


        //---- sự kiện indicator khi kéo trượt ảnh
        LinearLayout indicator_layout = view.findViewById(R.id.indicator_layout);
        int indicatorCount =  list.size();


        for (int i = 0 ; i < indicatorCount ; i++){
            ImageView imageView = new ImageView(getContext());
            imageView.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.unselected_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(8,0,8,0);//khoảng cách giữa các indicator slider
            imageView.setLayoutParams(params);
            indicator_layout.addView(imageView);
        }

        //set cố định indicator đầu tiên là indicator dc chọn
        ImageView dot = (ImageView) indicator_layout.getChildAt(0);
        dot.setImageDrawable(ContextCompat.getDrawable(
                getContext(),R.drawable.selected_dot
        ));

        // Đặt sự kiện trượt để cập nhật indicator
        onPageChangeCallback = new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                for (int i = 0 ; i < indicatorCount ; i++){
                    ImageView dot = (ImageView) indicator_layout.getChildAt(i);
                    dot.setImageDrawable(ContextCompat.getDrawable(
                            getContext(),
                            i == position ? R.drawable.selected_dot : R.drawable.unselected_dot
                    ));
                }
            }
        };
        //---- sự kiện indicator khi kéo trượt ảnh

        imgcartHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),CartActivity.class));
            }
        });

        adapterProduct = new AdapterProduct(listproduct, getContext(), new AdapterProduct.ItemclickListener() {
            @Override
            public void OnItemclick(int position) {
                Intent intent = new Intent(getActivity(),ProductActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("listproduct",listproduct);
                startActivity(intent);
            }
        });

        //hiển thị danh sách recyclerview
//                StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
//                2, StaggeredGridLayoutManager.VERTICAL
//        );
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapterProduct);
        int numberOfColumns = 2; // Điều này có thể được thay đổi thành số cột bạn muốn
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), numberOfColumns);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterProduct);

        searchsanpham.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()){
                    listproduct.clear();
                    listproduct.addAll(all);
                    adapterProduct.notifyDataSetChanged();
                }else {
                    listproduct.clear();
                    for (product th: all){
                        if (th.getNameproduct().contains(newText)){
                            listproduct.add(th);
                        }
                    }
                    adapterProduct.notifyDataSetChanged();
                }
                return true;
            }
        });


        btnAllCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryActivity.class);
                intent.putExtra("tendanhmuc",btnAllCategory.getText().toString());
                startActivity(intent);
            }
        });
        btnPhoneCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryActivity.class);
                intent.putExtra("tendanhmuc",btnPhoneCategory.getText().toString());
                startActivity(intent);
            }
        });
        btnLaptopCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryActivity.class);
                intent.putExtra("tendanhmuc",btnLaptopCategory.getText().toString());
                startActivity(intent);
            }
        });
        btnIpadCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryActivity.class);
                intent.putExtra("tendanhmuc",btnIpadCategory.getText().toString());
                startActivity(intent);
            }
        });
        btnEarphoneCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryActivity.class);
                intent.putExtra("tendanhmuc",btnEarphoneCategory.getText().toString());
                startActivity(intent);
            }
        });
        btnScreenCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryActivity.class);
                intent.putExtra("tendanhmuc",btnScreenCategory.getText().toString());
                startActivity(intent);
            }
        });
        btnAccessoryCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryActivity.class);
                intent.putExtra("tendanhmuc",btnAccessoryCategory.getText().toString());
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        viewPager2.unregisterOnPageChangeCallback(onPageChangeCallback);
    }

    //hiểnr thị dữ liệu trên php
    @Override
    public void onResume() {
        super.onResume();

        viewPager2.registerOnPageChangeCallback(onPageChangeCallback);

        new Thread(new Runnable() {
            @Override
            public void run() {
                handlerproduct.post(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog = new ProgressDialog(getContext());
                        progressDialog.setMessage("Loading");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                    }
                });
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://khoihoang0511.000webhostapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                API api = retrofit.create(API.class);
                Call<ArrayList<product>> callproduct = api.getlistproduct();
                callproduct.enqueue(new Callback<ArrayList<product>>() {
                    @Override
                    public void onResponse(Call<ArrayList<product>> call, Response<ArrayList<product>> response) {
                        if (response.isSuccessful() && response.body() != null){
                            ArrayList<product> list = response.body();
                            listproduct.clear();
                            listproduct.addAll(list);
                            all.clear();
                            all.addAll(list);
                            adapterProduct.notifyDataSetChanged();
                            progressDialog.dismiss();
                        }else {
                            Toast.makeText(getContext(), "lỗi listproduct", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<product>> call, Throwable t) {
                        Toast.makeText(getContext(), "Kết nối internet không ổn định", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
            }
        }).start();
    }


}