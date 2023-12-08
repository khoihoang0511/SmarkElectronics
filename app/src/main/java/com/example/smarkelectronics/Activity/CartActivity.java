package com.example.smarkelectronics.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarkelectronics.Adapter.AdapterCart;
import com.example.smarkelectronics.Model.Cart;
import com.example.smarkelectronics.Model.CartCheckBoxModel;
import com.example.smarkelectronics.Model.product;
import com.example.smarkelectronics.R;
import com.example.smarkelectronics.api.API;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CartActivity extends AppCompatActivity {


    TextView tvtongthanhtoancart,tvback,btnthanhtoancart;

    private ProgressDialog progressDialog;
    private Handler handlercart = new Handler();
    AdapterCart adapterCart;
    ArrayList<Cart> list;
    RecyclerView recyclerViewcart;

    private ArrayList<CartCheckBoxModel> list_CartCheckBoxModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerViewcart = findViewById(R.id.rcvcart);
        tvback = findViewById(R.id.tvbackcart);
        btnthanhtoancart = findViewById(R.id.btnbuycart);
        tvtongthanhtoancart = findViewById(R.id.tvTongThanhToancart);
        list = new ArrayList<>();
        list_CartCheckBoxModels = new ArrayList<>();

        tvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this,MainActivity.class));
            }
        });


        btnthanhtoancart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Cart> listPay = new ArrayList<>();
                for (CartCheckBoxModel a: list_CartCheckBoxModels){
                    if (a.isCheck()){
                        listPay.add(a);
                    }
                }
                if (listPay.size()>0){
                    Intent intent = new Intent(CartActivity.this, Pay.class);
                    intent.putExtra("sendproduct",listPay);
                    startActivity(intent);
                }else
                    Toast.makeText(CartActivity.this, "Không có sản phẩm để thanh toán", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            SharedPreferences saveAcc = getSharedPreferences("SaveAcc",MODE_PRIVATE);
            String email = saveAcc.getString("SaveEmail","");
            String password = saveAcc.getString("SavePass","");
            @Override
            public void run() {
                handlercart.post(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog = new ProgressDialog(CartActivity.this);
                        progressDialog.setMessage("Loading");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                    }
                });
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://khoihoang0511.000webhostapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                API api = retrofit.create(API.class);
                Call<ArrayList<Cart>> callcart = api.getlistcart(email,password);
                callcart.enqueue(new Callback<ArrayList<Cart>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Cart>> call, Response<ArrayList<Cart>> response) {
                        if (response.isSuccessful() && response.body() != null){
                            ArrayList<Cart> listcart = response.body();
                            list.clear();
                            list.addAll(listcart);
                            list_CartCheckBoxModels.clear();
                            addList();
                            adapterCart.notifyDataSetChanged();
                            progressDialog.dismiss();

                        }else {
                            Toast.makeText(CartActivity.this, "Lỗi listCart", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Cart>> call, Throwable t) {
                        Toast.makeText(CartActivity.this, "Bạn chưa thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
            }
        }).start();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapterCart = new AdapterCart(this,list_CartCheckBoxModels);
        recyclerViewcart.setLayoutManager(linearLayoutManager);
        recyclerViewcart.setAdapter(adapterCart);
        adapterCart.setOnClickProduct(new AdapterCart.OnClickCartCheckbox() {
            @Override
            public void clickcheckBox(boolean check, int vitri) {
                list_CartCheckBoxModels.get(vitri).setCheck(check);
                adapterCart.notifyDataSetChanged();
                setPrice();
            }
        });
        adapterCart.SetUpdatecartQuantity(new AdapterCart.UpdatecartQuantity() {
            @Override
            public void CartQuantity(int position, int Quantity) {
                list_CartCheckBoxModels.get(position).setQuanlitycart(Quantity);
                adapterCart.notifyDataSetChanged();
                setPrice();
            }
        });
        adapterCart.OnDeleteItemCart(new AdapterCart.DeleteItemCart() {
            @Override
            public void deleteItemCart(int position) {
               list_CartCheckBoxModels.remove(position);
               adapterCart.notifyDataSetChanged();
            }
        });
    }
    private void setPrice(){
        tvtongthanhtoancart.setText("0");
        int sum = 0;
        for (CartCheckBoxModel aa : list_CartCheckBoxModels){
            if (aa.isCheck()){
                sum += aa.getPriceproduct()*aa.getQuanlitycart();
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,### đ");
        tvtongthanhtoancart.setText(decimalFormat.format(sum));
    }
    private void addList(){
        for (Cart a: list){
            list_CartCheckBoxModels.add(new CartCheckBoxModel(a.getIdcart(),a.getImgavatar(),a.getImgavatar2(),a.getImgavatar3(),a.getNameproduct(),a.getPriceproduct(),a.getQuanlitycart(),false));
        }
    }
}