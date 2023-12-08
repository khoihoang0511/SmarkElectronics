package com.example.smarkelectronics.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarkelectronics.Adapter.OderAdapter;
import com.example.smarkelectronics.Model.OderModel;
import com.example.smarkelectronics.R;
import com.example.smarkelectronics.api.API;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OderActivity extends AppCompatActivity {

    RecyclerView rcvMyOrder;
    OderAdapter oderAdapter;
    ArrayList<OderModel> listOder;
    private ProgressDialog progressDialog;
    private Handler handlerAddress = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oder);

        TextView btnBackMyOrder = findViewById(R.id.btnBackMyOrder);
        rcvMyOrder = findViewById(R.id.rcvMyOrder);

        listOder = new ArrayList<>();
        oderAdapter = new OderAdapter(OderActivity.this, listOder);
        rcvMyOrder.setAdapter(oderAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OderActivity.this);
        rcvMyOrder.setLayoutManager(linearLayoutManager);

        getListOder();

        btnBackMyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OderActivity.this,MainActivity.class));
            }
        });

    }
    private void getListOder(){
        SharedPreferences saveAcc = getSharedPreferences("SaveAcc",MODE_PRIVATE);
        String email = saveAcc.getString("SaveEmail","");
        String password = saveAcc.getString("SavePass","");
        new Thread(new Runnable() {
            @Override
            public void run() {
                handlerAddress.post(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog = new ProgressDialog(OderActivity.this);
                        progressDialog.setMessage("Loanding");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                    }
                });
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://khoihoang0511.000webhostapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                API api = retrofit.create(API.class);
                Call<ArrayList<OderModel>> callDress = api.getListOder(email,password);
                callDress.enqueue(new Callback<ArrayList<OderModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<OderModel>> call, Response<ArrayList<OderModel>> response) {
                        if (response.isSuccessful() && response.body() !=null){
                            ArrayList<OderModel> list = response.body();
                            listOder.clear();
                            listOder.addAll(list);
                            oderAdapter.notifyDataSetChanged();
                            progressDialog.dismiss();
                        }else {
                            progressDialog.dismiss();
                        }
                    }
                    @Override
                    public void onFailure(Call<ArrayList<OderModel>> call, Throwable t) {
                        Toast.makeText(OderActivity.this, "Lỗi mạng", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
            }
        }).start();
    }
}