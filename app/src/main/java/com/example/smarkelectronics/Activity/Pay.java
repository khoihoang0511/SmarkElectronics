package com.example.smarkelectronics.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarkelectronics.R;

public class Pay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        RelativeLayout SelectAddress = findViewById(R.id.SelectAddress);
        TextView txtAddnNewAddress = findViewById(R.id.txtAddnNewAddress);

        txtAddnNewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pay.this,Addshippingaddress.class);
                startActivity(intent);
            }
        });

        SelectAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pay.this, SelectAddress.class);
                startActivity(intent);
            }
        });
    }
}