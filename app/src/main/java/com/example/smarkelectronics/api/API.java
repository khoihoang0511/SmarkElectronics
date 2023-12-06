package com.example.smarkelectronics.api;

import com.example.smarkelectronics.Model.AddressModel;
import com.example.smarkelectronics.Model.Cart;
import com.example.smarkelectronics.Model.CategoryModel;
import com.example.smarkelectronics.Model.Customer;
import com.example.smarkelectronics.Model.Favorite;
import com.example.smarkelectronics.Model.MyReviewModel;
import com.example.smarkelectronics.Model.NotificationModel;
import com.example.smarkelectronics.Model.OderModel;
import com.example.smarkelectronics.Model.ProfileModel;
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
    Call<String> addcart(@Query("soluong") int soluong,@Query("idproduct") int id,@Query("email")String email, @Query("password") String password);

    @GET("addDress.php")
    Call<String> addDress(@Query("nameaddress") String nameaddress,
                          @Query("phoneaddress") String phoneaddress,
                          @Query("Phuong") String Phuong,
                          @Query("Quan") String Quan,
                          @Query("thanhpho") String thanhpho,
                          @Query("chitiet") String chitiet,
                          @Query("email") String email,
                          @Query("password") String password);

    @GET("UpdateDress.php")
    Call<String> updateDress(@Query("idaddress")int idaddress,
                             @Query("nameaddress") String nameaddress,
                             @Query("phoneaddress") String phoneaddress,
                             @Query("Phuong") String Phuong,
                             @Query("Quan") String Quan,
                             @Query("thanhpho") String thanhpho,
                             @Query("chitiet") String chitiet,
                             @Query("email") String email,
                             @Query("password") String password);

    @GET("getlistAddress.php")
    Call<ArrayList<AddressModel>> getlistAddress();

    @GET("deleteItemInCart.php")
    Call<String> delete_ItemInCare(@Query("IDCART")Integer idcart);

    @GET("deleteMyshippingaddress.php")
    Call<String> delete_ItemInAddress(@Query("IDAddress")Integer idaddress);

    @GET("addfavorite.php")
    Call<String> addfavorite(@Query("idproduct") int idproduct, @Query("idcustomer") int idcustomer);

    @GET("getlistfavorite.php")
    Call<ArrayList<Favorite>> getlistfavorite();

    @GET("addcustomer.php")
    Call<String> addcustomer(@Query("namecustomer") String namecustomer,@Query("email") String email,@Query("passwordcustomer") String passwordcustomer);

    @GET("login.php")
    Call<Customer> login(@Query("Email") String email, @Query("Pass") String pass);

    @GET("deleteItemInFavorite.php")
    Call<String> delete_ItemInFavorite(@Query("IDFavorite")Integer idfavorite);

    @GET("Addalltocart.php")
    Call<String> addalltocart(@Query("Addalltocart")String addalltocart);

    @GET("getlistCategory.php")
    Call<ArrayList<CategoryModel>> getlistCategory(@Query("NameCategory")String nameCategory);

    @GET("getprofile.php")
    Call<ProfileModel> getprofile(@Query("email")String email,@Query("password") String password);

    @GET("getlistoder.php")
    Call<ArrayList<OderModel>> getListOder(@Query("email")String email, @Query("password") String password);

    @GET("cancelOder.php")
    Call<String> delete_ItemOder(@Query("IDoder")Integer idoder);

    @GET("pay.php")
    Call<String> Pay(@Query("listPay")String listPay,@Query("txtAddressPay")int txtAddressPay,@Query("payment")Integer payment,@Query("email")String email, @Query("password") String password);

    @GET("getListMyReview.php")
    Call<ArrayList<MyReviewModel>> getListMyReview(@Query("email")String email, @Query("password") String password);
    @GET("getListNotification.php")
    Call<ArrayList<NotificationModel>> getListNotification(@Query("email")String email, @Query("password") String password);
    @GET("addReview.php")
    Call<String> addReview(@Query("numberofstars")int numberofstars,@Query("content")String content,@Query("id")int id,@Query("email")String email, @Query("password") String password);

}
