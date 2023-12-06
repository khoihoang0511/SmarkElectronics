package com.example.smarkelectronics.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.smarkelectronics.R;

public class PaymentSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);

        Button btnGoBackToTheMainPage = findViewById(R.id.btnGoBackToTheMainPage);
        Button btnViewOrders = findViewById(R.id.btnViewOrders);

        btnViewOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PaymentSuccess.this,OderActivity.class));
            }
        });

        btnGoBackToTheMainPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PaymentSuccess.this,MainActivity.class));
            }
        });
    }
}