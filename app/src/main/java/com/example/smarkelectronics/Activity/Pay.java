package com.example.smarkelectronics.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.smarkelectronics.R;

public class Pay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        RelativeLayout SelectAddress = findViewById(R.id.SelectAddress);

        SelectAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pay.this, "cccccccccccccccccccccccccc", Toast.LENGTH_SHORT).show();
            }
        });
    }
}