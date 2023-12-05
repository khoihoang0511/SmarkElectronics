package com.example.smarkelectronics.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarkelectronics.Adapter.AdapterAddress;
import com.example.smarkelectronics.Adapter.AdapterMyShippingAddress;
import com.example.smarkelectronics.Model.AddressModel;
import com.example.smarkelectronics.R;
import com.example.smarkelectronics.api.API;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyshipPingAddress extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private Handler handlerproduct = new Handler();

    AdapterMyShippingAddress adapterMyShippingAddress;
    ArrayList<AddressModel> listAddress;
    private RecyclerView rcvMyshippingaddress;

    private Handler handlerAddress = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myship_ping_address);

        listAddress = new ArrayList<>();
        TextView tvback = findViewById(R.id.tvback);
        ImageView imgAddAddress = findViewById(R.id.imgAddAddress);

        imgAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyshipPingAddress.this,Addshippingaddress.class));
            }
        });

        tvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    @Override
    public void onResume() {
        super.onResume();

        new Thread(new Runnable() {
            @Override
            public void run() {
                handlerAddress.post(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog = new ProgressDialog(MyshipPingAddress.this);
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
                            adapterMyShippingAddress.notifyDataSetChanged();
                            progressDialog.dismiss();
                            Log.e("--------------->",response.body() +"");
                            adapterMyShippingAddress.OnDeleteItemAddress(new AdapterMyShippingAddress.DeleteItemAddress() {
                                @Override
                                public void DeleteAddress(int position) {
                                    listAddress.remove(position);
                                    adapterMyShippingAddress.notifyDataSetChanged();
                                }
                            });
                        }else {
                            Toast.makeText(MyshipPingAddress.this, "không có dữ liệu", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<AddressModel>> call, Throwable t) {
                        Toast.makeText(MyshipPingAddress.this, "không có dữ liệu", Toast.LENGTH_SHORT).show();
                        Log.e("--------------->",t+"");
                        progressDialog.dismiss();

                    }
                });
            }
        }).start();

        rcvMyshippingaddress = findViewById(R.id.rcvMyshippingaddress);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvMyshippingaddress.setLayoutManager(linearLayoutManager);
        adapterMyShippingAddress = new AdapterMyShippingAddress(listAddress,MyshipPingAddress.this);
        rcvMyshippingaddress.setAdapter(adapterMyShippingAddress);
    }
}