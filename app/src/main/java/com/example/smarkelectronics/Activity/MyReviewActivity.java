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
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarkelectronics.Adapter.AdapterMyReview;
import com.example.smarkelectronics.Fragment.HomeFragment;
import com.example.smarkelectronics.Model.MyReviewModel;
import com.example.smarkelectronics.R;
import com.example.smarkelectronics.api.API;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyReviewActivity extends AppCompatActivity {

    public ArrayList<MyReviewModel> listMyReview;
    public AdapterMyReview adapterMyReview;
    private ProgressDialog progressDialog;
    private Handler handlerAddress = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_review);

        TextView tvbackMyreview = findViewById(R.id.tvbackMyreview);
        ImageView opensearchMyreview = findViewById(R.id.opensearchMyreview);
        RecyclerView rcvMyReview = findViewById(R.id.rcvMyReview);

        listMyReview = new ArrayList<>();
        adapterMyReview = new AdapterMyReview(MyReviewActivity.this, listMyReview);
        rcvMyReview.setAdapter(adapterMyReview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyReviewActivity.this);
        rcvMyReview.setLayoutManager(linearLayoutManager);

        tvbackMyreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyReviewActivity.this, MainActivity.class));
            }
        });

        getListMyreview();
    }

    private void getListMyreview() {
        SharedPreferences saveAcc = getSharedPreferences("SaveAcc", MODE_PRIVATE);
        String email = saveAcc.getString("SaveEmail", "");
        String password = saveAcc.getString("SavePass", "");
        new Thread(new Runnable() {
            @Override
            public void run() {
                handlerAddress.post(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog = new ProgressDialog(MyReviewActivity.this);
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
                Call<ArrayList<MyReviewModel>> callDress = api.getListMyReview(email, password);
                callDress.enqueue(new Callback<ArrayList<MyReviewModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<MyReviewModel>> call, Response<ArrayList<MyReviewModel>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            ArrayList<MyReviewModel> list = response.body();
                            listMyReview.clear();
                            listMyReview.addAll(list);
                            adapterMyReview.notifyDataSetChanged();
                            progressDialog.dismiss();
                        } else {
                            progressDialog.dismiss();
                            Log.e("=------->", response.body() + "");
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<MyReviewModel>> call, Throwable t) {
                        Toast.makeText(MyReviewActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MyReviewActivity.this, PaymentSuccess.class));
                        progressDialog.dismiss();
                    }
                });
            }
        }).start();
    }
}