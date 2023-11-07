package com.example.smarkelectronics.api;

import com.example.smarkelectronics.product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("getlisthome.php")
    Call<ArrayList<product>> getlistproduct();
}
