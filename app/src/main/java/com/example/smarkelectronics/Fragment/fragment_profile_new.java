package com.example.smarkelectronics.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarkelectronics.Activity.Addshippingaddress;
import com.example.smarkelectronics.Activity.MyshipPingAddress;
import com.example.smarkelectronics.Activity.OderActivity;
import com.example.smarkelectronics.Activity.Pay;
import com.example.smarkelectronics.Activity.PaymentSuccess;
import com.example.smarkelectronics.Model.ProfileModel;
import com.example.smarkelectronics.R;
import com.example.smarkelectronics.api.API;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class fragment_profile_new extends Fragment {

    private ProgressDialog progressDialog;
    private Handler handlerAddress = new Handler();

    SharedPreferences saveAcc;

    private String email,password;
    private TextView txtNameProfile;
    private TextView txtEmailProfile;
    private TextView txtNumbertransport;
    private TextView txtNumberdelivery;
    private TextView txtNumberEvaluate;

    public fragment_profile_new() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_new, container, false);

        txtNameProfile = view.findViewById(R.id.txtNameProfile);
        txtEmailProfile = view.findViewById(R.id.txtEmailProfile);
        txtNumbertransport = view.findViewById(R.id.txtNumbertransport);
        txtNumberdelivery = view.findViewById(R.id.txtNumberdelivery);
        txtNumberEvaluate = view.findViewById(R.id.txtNumberEvaluate);
        RelativeLayout rltMyOrder = view.findViewById(R.id.rltMyOrder);

        RelativeLayout rcvMyshippingaddress = view.findViewById(R.id.rcvMyshippingaddress);
        saveAcc = getContext().getSharedPreferences("SaveAcc", MODE_PRIVATE);
        email = saveAcc.getString("SaveEmail",null);
        password = saveAcc.getString("SavePass",null);


        rltMyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), OderActivity.class));
            }
        });
        rcvMyshippingaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyshipPingAddress.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadpersonalinformation();
        
    }

    private void loadpersonalinformation(){
        if (email.isEmpty() || password.isEmpty()){
            txtNameProfile.setText("null");
            txtEmailProfile.setText("null");
            txtNumbertransport.setText("0");
            txtNumberdelivery.setText("0");
            txtNumberEvaluate.setText("0");
        }else {
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
                    Call<ProfileModel> callDress = api.getprofile(email,password);
                    callDress.enqueue(new Callback<ProfileModel>() {
                        @Override
                        public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                            if (response.isSuccessful() && response.body()!=null){
                                ProfileModel profileModel = response.body();
                                txtNameProfile.setText(profileModel.getName()+"");
                                txtEmailProfile.setText(profileModel.getEmail()+"");
                                txtNumbertransport.setText(profileModel.getNumbertransport()+"");
                                txtNumberdelivery.setText(profileModel.getNumberdelivery()+"");
                                txtNumberEvaluate.setText(profileModel.getNumberEvaluate()+"");
                                progressDialog.dismiss();
                            }else {
                                progressDialog.dismiss();
                            }
                        }
                        @Override
                        public void onFailure(Call<ProfileModel> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "sài host free thông cảm", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).start();
        }
    }
}