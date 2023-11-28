package com.example.smarkelectronics.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarkelectronics.Model.Customer;
import com.example.smarkelectronics.R;
import com.example.smarkelectronics.api.API;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private Handler handlerAddress = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextInputEditText edtEmailLogin = findViewById(R.id.edtEmailLogin);
        TextInputEditText edtPasswordLogin = findViewById(R.id.edtPasswordLogin);
        Button btnLogin = findViewById(R.id.btnLogin);
        TextView txtRegister = findViewById(R.id.txtRegister);

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = edtEmailLogin.getText().toString();
                String pass = edtPasswordLogin.getText().toString();

                if (email.isEmpty()||pass.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Email và Password không được để trống", Toast.LENGTH_SHORT).show();
                }else{
                    LoginAccount(email,pass);
                }
            }
        });
    }
    private void LoginAccount(String email,String pass){
        new Thread(new Runnable() {
            @Override
            public void run() {
                handlerAddress.post(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog = new ProgressDialog(LoginActivity.this);
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
                Call<Customer> callDress =api.login(email, pass);
                callDress.enqueue(new Callback<Customer>() {
                    @Override
                    public void onResponse(Call<Customer> call, Response<Customer> response) {
                        if (response.isSuccessful() && response.body()!=null){
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            progressDialog.dismiss();
                        }else {
                            Toast.makeText(LoginActivity.this, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                    @Override
                    public void onFailure(Call<Customer> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Mua host đi", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
            }
        }).start();
    }
}