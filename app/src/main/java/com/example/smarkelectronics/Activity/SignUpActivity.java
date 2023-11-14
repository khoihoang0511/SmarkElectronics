package com.example.smarkelectronics.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.smarkelectronics.R;
import com.example.smarkelectronics.api.API;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {
    //api đẩy dữ liệu lên php
    private ProgressDialog progressDialog;
    private Handler handlerproduct = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        TextInputEditText edtName = findViewById(R.id.edtName);
        TextInputEditText edtEmail = findViewById(R.id.edtEmail);
        TextInputEditText edtPassword = findViewById(R.id.edtPassword);
        TextInputEditText edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        Button btnDangKy = findViewById(R.id.btnDangKy);


        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                String confirmpassword = edtConfirmPassword.getText().toString();
                if (password.equals(confirmpassword)) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            handlerproduct.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog = new ProgressDialog(SignUpActivity.this);
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
                            Call<String> callcustomer = api.addcustomer(name,email,password);
                            callcustomer.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    if (response.isSuccessful() && response.body() != null) {
                                        progressDialog.dismiss();
                                        Toast.makeText(SignUpActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {
                                    Toast.makeText(SignUpActivity.this, "Kê nối không ổn định", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).start();
                }
           }
        });
    }
}