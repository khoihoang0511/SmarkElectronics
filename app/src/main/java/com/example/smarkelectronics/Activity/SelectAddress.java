package com.example.smarkelectronics.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.smarkelectronics.Adapter.AdapterSelectAddress;
import com.example.smarkelectronics.Model.SelectAddressModel;
import com.example.smarkelectronics.R;

import java.util.ArrayList;
import java.util.List;

public class SelectAddress extends AppCompatActivity {
    private RecyclerView rcvSelectAddress;
    private List<SelectAddressModel> SelectAddressList;
    private AdapterSelectAddress adapterSelectAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_address);




        rcvSelectAddress = findViewById(R.id.rcvSelectAddress);
        SelectAddressList = new ArrayList<>();

        SelectAddressList.add(new SelectAddressModel("Lê Đình Thái","191 Âu Dương Lân","0931269813","Hồ Chí Minh","Quận 8","Phường 3"));


        adapterSelectAddress = new AdapterSelectAddress(SelectAddressList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvSelectAddress.setLayoutManager(linearLayoutManager);
        rcvSelectAddress.setAdapter(adapterSelectAddress);

    }
}