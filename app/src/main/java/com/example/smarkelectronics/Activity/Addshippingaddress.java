package com.example.smarkelectronics.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

import com.example.smarkelectronics.R;

public class Addshippingaddress extends AppCompatActivity {

    private Spinner spinnerAddshippingaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addshippingaddress);

        spinnerAddshippingaddress = findViewById(R.id.spinnerAddshippingaddress);
    }
}