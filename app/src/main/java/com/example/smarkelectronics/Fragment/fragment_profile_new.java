package com.example.smarkelectronics.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.smarkelectronics.Activity.MyshipPingAddress;
import com.example.smarkelectronics.Activity.Pay;
import com.example.smarkelectronics.Activity.PaymentSuccess;
import com.example.smarkelectronics.R;

public class fragment_profile_new extends Fragment {



    public fragment_profile_new() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_new, container, false);

        RelativeLayout rcvMyshippingaddress = view.findViewById(R.id.rcvMyshippingaddress);

        rcvMyshippingaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyshipPingAddress.class);
                startActivity(intent);
            }
        });
        return view;
    }
}