package com.example.smarkelectronics.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.smarkelectronics.Activity.MyReviewActivity;
import com.example.smarkelectronics.Activity.PaymentSuccess;
import com.example.smarkelectronics.Adapter.AdapterNotification;
import com.example.smarkelectronics.Model.MyReviewModel;
import com.example.smarkelectronics.Model.NotificationModel;
import com.example.smarkelectronics.R;
import com.example.smarkelectronics.api.API;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class fragment_notification_new extends Fragment {

    public ArrayList<NotificationModel> listNotification;
    public AdapterNotification adapterNotification;
    private ProgressDialog progressDialog;
    private Handler handlerAddress = new Handler();

    public fragment_notification_new() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification_new, container, false);

        RecyclerView rcvNOTIFICATION = view.findViewById(R.id.rcvNOTIFICATION);

        listNotification = new ArrayList<>();
        adapterNotification = new AdapterNotification(getContext(),listNotification);
        rcvNOTIFICATION.setAdapter(adapterNotification);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvNOTIFICATION.setLayoutManager(linearLayoutManager);

        getlistNotification();

        return view;
    }
    private void getlistNotification(){
        SharedPreferences saveAcc = getContext().getSharedPreferences("SaveAcc", MODE_PRIVATE);
        String email = saveAcc.getString("SaveEmail", "");
        String password = saveAcc.getString("SavePass", "");
        new Thread(new Runnable() {
            @Override
            public void run() {
                handlerAddress.post(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog = new ProgressDialog(getContext());
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
                Call<ArrayList<NotificationModel>> callDress = api.getListNotification(email, password);
                callDress.enqueue(new Callback<ArrayList<NotificationModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<NotificationModel>> call, Response<ArrayList<NotificationModel>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            ArrayList<NotificationModel> list = response.body();
                            listNotification.clear();
                            listNotification.addAll(list);
                            adapterNotification.notifyDataSetChanged();
                            progressDialog.dismiss();
                        } else {
                            progressDialog.dismiss();
                            Log.e("=------->", response.body() + "");
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<NotificationModel>> call, Throwable t) {
                        Toast.makeText(getContext(), "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
            }
        }).start();
    }
}