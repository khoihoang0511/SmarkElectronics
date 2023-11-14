package com.example.smarkelectronics.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarkelectronics.Adapter.AdapterAddress;
import com.example.smarkelectronics.Adapter.AdapterProduct;
import com.example.smarkelectronics.Adapter.AdapterSelectAddress;
import com.example.smarkelectronics.Model.AddressModel;
import com.example.smarkelectronics.Model.SelectAddressModel;
import com.example.smarkelectronics.Model.product;
import com.example.smarkelectronics.R;
import com.example.smarkelectronics.api.API;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SelectAddress extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private Handler handlerproduct = new Handler();

    AdapterAddress adapterAddress;
    ArrayList<AddressModel> listAddress;

    RecyclerView recyclerView;
    private RecyclerView rcvSelectAddress;
//    private ArrayList<SelectAddressModel> SelectAddressList;
//    private AdapterSelectAddress adapterSelectAddress;
    private Handler handlerAddress = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_address);

        listAddress = new ArrayList<>();
//        SelectAddressList = new ArrayList<>();
        TextView tvback = findViewById(R.id.tvback);

        tvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAddress.this, Pay.class);
                startActivity(intent);
            }
        });




    }
    //hiểnr thị dữ liệu trên php
    @Override
    public void onResume() {
        super.onResume();

        new Thread(new Runnable() {
            @Override
            public void run() {
                handlerAddress.post(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog = new ProgressDialog(SelectAddress.this);
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
                Call<ArrayList<AddressModel>> callAddress = api.getlistAddress();
                callAddress.enqueue(new Callback<ArrayList<AddressModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<AddressModel>> call, Response<ArrayList<AddressModel>> response) {
                        if (response.isSuccessful() && response.body() != null){
                            ArrayList<AddressModel> list = response.body();
                            listAddress.clear();
                            listAddress.addAll(list);
                            adapterAddress.notifyDataSetChanged();
                            progressDialog.dismiss();
                            Log.e("--------------->",list.size()+"");

                        }else {
                            Toast.makeText(SelectAddress.this, "lỗi listproduct", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<AddressModel>> call, Throwable t) {
                        Toast.makeText(SelectAddress.this, "ccccccccccccc", Toast.LENGTH_SHORT).show();
                        Log.e("--------------->",t+"");

                    }
                });
            }
        }).start();
        rcvSelectAddress = findViewById(R.id.rcvSelectAddress);
        adapterAddress = new AdapterAddress(listAddress, SelectAddress.this, new AdapterAddress.ItemclickListenerAddress() {
            @Override
            public void OnItemclickAddress(int position) {

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvSelectAddress.setLayoutManager(linearLayoutManager);
        rcvSelectAddress.setAdapter(adapterAddress);


    }

}