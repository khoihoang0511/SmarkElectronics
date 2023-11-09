package com.example.smarkelectronics.api;

import com.example.smarkelectronics.Model.Cart;
import com.example.smarkelectronics.Model.product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET("getlisthome.php")
    Call<ArrayList<product>> getlistproduct();

    @GET("getlistcart.php")
    Call<ArrayList<Cart>> getlistcart() ;

    @GET("addcart.php")
    Call<String> addcart(@Query("idproduct") int id,@Query("soluong") int soluong);

    @GET("addDress.php")
    Call<String> addDress(@Query("nameaddress") String nameaddress,@Query("phoneaddress") String phoneaddress,@Query("Phuong") String Phuong,@Query("Quan") String Quan,@Query("thanhpho") String thanhpho, @Query("chitiet") String chitiet);

}
