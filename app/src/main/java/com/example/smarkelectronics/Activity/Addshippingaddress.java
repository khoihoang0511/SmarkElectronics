package com.example.smarkelectronics.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.smarkelectronics.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Addshippingaddress extends AppCompatActivity {

    private Spinner spinnerAddshippingaddress;
    private ProgressDialog progressDialog;
    private Handler handlerAddress = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addshippingaddress);

        EditText edtNameAddShip = findViewById(R.id.edtNameAddShip);
        EditText edtAddressAddShip = findViewById(R.id.edtAddressAddShip);
        EditText edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        EditText edtCityAddShip = findViewById(R.id.edtCityAddShip);
        EditText edtDistrictAddShip = findViewById(R.id.edtDistrictAddShip);
        EditText edtWardAddShip = findViewById(R.id.edtWardAddShip);
        Button btnSaveAddressAddShip = findViewById(R.id.btnSaveAddressAddShip);

        btnSaveAddressAddShip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        handlerAddress.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                progressDialog = new ProgressDialog(Addshippingaddress.this);
//                                progressDialog.setMessage("Loanding");
//                                progressDialog.setCancelable(false);
//                                progressDialog.show();
//                            }
//                        });
//                        Retrofit retrofit = new Retrofit.Builder()
//                                .baseUrl()
//                                .addCallAdapterFactory(GsonConverterFactory.create());
//                    }
//                });
            }
        });
    }
}