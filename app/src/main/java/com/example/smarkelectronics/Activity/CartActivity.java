package com.example.smarkelectronics.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarkelectronics.Adapter.AdapterCart;
import com.example.smarkelectronics.Model.Cart;
import com.example.smarkelectronics.Model.product;
import com.example.smarkelectronics.R;
import com.example.smarkelectronics.api.API;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CartActivity extends AppCompatActivity {


    TextView tvtongthanhtoancart;

    //hiển thị dữ liệu giỏ hàng từ php //api
    private ProgressDialog progressDialog;
    private Handler handlercart = new Handler();
    AdapterCart adapterCart;
    ArrayList<Cart> list;
    RecyclerView recyclerViewcart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerViewcart = findViewById(R.id.rcvcart);
        TextView tvback = findViewById(R.id.tvbackcart);
        tvtongthanhtoancart = findViewById(R.id.tvTongThanhToancart);
        list = new ArrayList<>();

        tvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                handlercart.post(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog = new ProgressDialog(CartActivity.this);
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
                Call<ArrayList<Cart>> callcart = api.getlistcart();
                callcart.enqueue(new Callback<ArrayList<Cart>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Cart>> call, Response<ArrayList<Cart>> response) {
                        if (response.isSuccessful() && response.body() != null){
                            ArrayList<Cart> listcart = response.body();
                            list.clear();
                            list.addAll(listcart);
                            adapterCart.notifyDataSetChanged();
                            progressDialog.dismiss();

                        }else {
                            Toast.makeText(CartActivity.this, "Lỗi listCart", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Cart>> call, Throwable t) {
//                        Toast.makeText(CartActivity.this, "Kết nối intener không ổn định", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
            }
        }).start();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapterCart = new AdapterCart(this,list);
        recyclerViewcart.setLayoutManager(linearLayoutManager);
        recyclerViewcart.setAdapter(adapterCart);
        adapterCart.setOnClickProduct(new AdapterCart.OnClickCartCheckbox() {
            @Override
            public void clickcheckBox(int tongtien) {
                int tongtiencart = tongtien;
                DecimalFormat decimalFormat = new DecimalFormat("###,### đ");
                tvtongthanhtoancart.setText(decimalFormat.format(tongtiencart));
            }
        });
    }
}