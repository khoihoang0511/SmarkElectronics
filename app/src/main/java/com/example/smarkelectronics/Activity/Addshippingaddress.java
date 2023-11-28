package com.example.smarkelectronics.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.smarkelectronics.R;
import com.example.smarkelectronics.api.API;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Addshippingaddress extends AppCompatActivity {

    private Spinner spinnerAddshippingaddress;
    EditText edtNameAddShip,edtAddressAddShip,edtPhoneNumber,edtCityAddShip,edtDistrictAddShip,edtWardAddShip;
    Button btnSaveAddressAddShip;
    ImageView imageBackAddShipPing;
    private ProgressDialog progressDialog;
    private Handler handlerAddress = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addshippingaddress);

         edtNameAddShip = findViewById(R.id.edtNameAddShip);
         edtAddressAddShip = findViewById(R.id.edtAddressAddShip);
         edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
         edtCityAddShip = findViewById(R.id.edtCityAddShip);
         edtDistrictAddShip = findViewById(R.id.edtDistrictAddShip);
         edtWardAddShip = findViewById(R.id.edtWardAddShip);
         btnSaveAddressAddShip = findViewById(R.id.btnSaveAddressAddShip);
         imageBackAddShipPing = findViewById(R.id.imageBackAddShipPing);

        imageBackAddShipPing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSaveAddressAddShip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aveAddressAddShip();
            }
        });
    }
    public void aveAddressAddShip(){
        String name = edtNameAddShip.getText().toString();
        String address = edtAddressAddShip.getText().toString();
        String phonenumber = edtPhoneNumber.getText().toString();
        String city = edtCityAddShip.getText().toString();
        String district = edtDistrictAddShip.getText().toString();
        String ward = edtWardAddShip.getText().toString();

        if (edtNameAddShip.getText().toString().isEmpty()){
            Toast.makeText(Addshippingaddress.this, "Bạn chưa nhâp họ và tên", Toast.LENGTH_SHORT).show();
        }
        if (edtAddressAddShip.getText().toString().isEmpty()){
            Toast.makeText(Addshippingaddress.this, "Bạn chưa nhập địa chỉ", Toast.LENGTH_SHORT).show();
        }
        if (edtPhoneNumber.getText().toString().isEmpty()){
            Toast.makeText(Addshippingaddress.this, "Bạn chưa nhâp số điện thoại", Toast.LENGTH_SHORT).show();
        }
        if (edtCityAddShip.getText().toString().isEmpty()){
            Toast.makeText(Addshippingaddress.this, "Bạn chưa nhâp thành phố", Toast.LENGTH_SHORT).show();
        }
        if (edtDistrictAddShip.getText().toString().isEmpty()){
            Toast.makeText(Addshippingaddress.this, "Bạn chưa nhâp quận/huyện", Toast.LENGTH_SHORT).show();
        }
        if (edtWardAddShip.getText().toString().isEmpty()){
            Toast.makeText(Addshippingaddress.this, "Bạn chưa nhâp phường/xã", Toast.LENGTH_SHORT).show();
        }

        if((name.isEmpty() && address.isEmpty() && phonenumber.isEmpty() && city.isEmpty() && district.isEmpty() && ward.isEmpty()) ){
            Toast.makeText(Addshippingaddress.this, "hehe", Toast.LENGTH_SHORT).show();
        }
        else  {
            Getaddressdata(name,phonenumber,ward,district,city,address);
        }
    }
    private void Getaddressdata(String name, String phonenumber, String ward, String district, String city, String address){
        new Thread(new Runnable() {
            @Override
            public void run() {
                handlerAddress.post(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog = new ProgressDialog(Addshippingaddress.this);
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
                Call<String> callDress = api.addDress(name,phonenumber,ward,district,city,address);
                callDress.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful() && response.body() != null){
                            progressDialog.dismiss();
                            Toast.makeText(Addshippingaddress.this, "Lưu địa chỉ thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Log.e("=------->",response.body()+"");
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(Addshippingaddress.this, "Kết nối không ổn định", Toast.LENGTH_SHORT).show();
                        Log.e("=------->",t+"");
                    }
                });
            }
        }).start();
    }
}