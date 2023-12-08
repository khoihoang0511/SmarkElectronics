package com.example.smarkelectronics.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarkelectronics.Adapter.AdapterAddress;
import com.example.smarkelectronics.Adapter.AdapterProduct;
import com.example.smarkelectronics.Adapter.AdapterProductPay;
import com.example.smarkelectronics.Model.AddressModel;
import com.example.smarkelectronics.Model.Cart;
import com.example.smarkelectronics.Model.product;
import com.example.smarkelectronics.R;
import com.example.smarkelectronics.api.API;
import com.example.smarkelectronics.zalopay.CreateOrder;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class Pay extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private Handler handlerAddress = new Handler();
    AdapterAddress adapterAddress;
    ArrayList<AddressModel> listAddress;
    TextView txtNamePay;
    TextView txtAddressPay;
    TextView txtSdtPay;
    RecyclerView rcvPayProduct;
    AdapterProductPay adapterProductPay;
    Button btnPay;
    ArrayList<Cart> listProductPay;
    String tongtien;

    TextView txtTotalcostofgoodsDisplay;
    TextView txtTransportfeeDisplay;
    TextView txtTotalsettlementDisplay;
    RadioButton rbtnOnline,rbtnOffline;
    Integer idaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        RelativeLayout SelectAddress = findViewById(R.id.SelectAddress);
        TextView txtAddnNewAddress = findViewById(R.id.txtAddnNewAddress);
        ImageView imgBackPay = findViewById(R.id.imgBackPay);

        txtTotalcostofgoodsDisplay = findViewById(R.id.txtTotalcostofgoodsDisplay);
        txtTransportfeeDisplay = findViewById(R.id.txtTransportfeeDisplay);
        txtTotalsettlementDisplay = findViewById(R.id.txtTotalsettlementDisplay);
        btnPay = findViewById(R.id.btnPay);

        listAddress = new ArrayList<>();
        txtNamePay = findViewById(R.id.txtNamePay);
        txtAddressPay = findViewById(R.id.txtAddressPay);
        txtSdtPay = findViewById(R.id.txtSdtPay);
        rbtnOnline = findViewById(R.id.rbtnOnline);
        rbtnOffline = findViewById(R.id.rbtnOffline);

        //zalopay
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ZaloPaySDK.init(2553, Environment.SANDBOX);

        listProductPay = new ArrayList<>();
        Intent getProduct = getIntent();
        if (getProduct !=null){
            listProductPay = (ArrayList<Cart>) getProduct.getSerializableExtra("sendproduct");
        }

        rcvPayProduct = findViewById(R.id.rcvPayProduct);
        LinearLayoutManager linearLayoutManagerPay = new LinearLayoutManager(Pay.this);
        rcvPayProduct.setLayoutManager(linearLayoutManagerPay);
        adapterProductPay= new AdapterProductPay(Pay.this,listProductPay);
        rcvPayProduct.setAdapter(adapterProductPay);

        updatecost();

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtAddressPay.getText().toString().isEmpty()){
                    Toast.makeText(Pay.this, "Bạn chưa chọn địa chỉ giao hàng", Toast.LENGTH_SHORT).show();
                }else if (!rbtnOnline.isChecked() && !rbtnOffline.isChecked()){
                    Toast.makeText(Pay.this, "Bạn chưa chọn phương thức thanh toán", Toast.LENGTH_SHORT).show();
                }else if (rbtnOffline.isChecked()){
                    pay(new Gson().toJson(listProductPay));
                }else {
                    payByZalo();
                }

            }
        });

        imgBackPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Pay.this,CartActivity.class));
            }
        });

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
                Dialog dialog = new Dialog(Pay.this);
                View view = LayoutInflater.from(Pay.this).inflate(R.layout.activity_select_address,null);
                dialog.setContentView(view);
                RecyclerView rcvSelectAddress = view.findViewById(R.id.rcvSelectAddress);
                TextView tvback = view.findViewById(R.id.tvback);

                tvback.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                new Thread(new Runnable() {
                    SharedPreferences saveAcc = getSharedPreferences("SaveAcc",MODE_PRIVATE);
                    String email = saveAcc.getString("SaveEmail","");
                    String password = saveAcc.getString("SavePass","");
                    @Override
                    public void run() {
                        handlerAddress.post(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog = new ProgressDialog(Pay.this);
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
                        Call<ArrayList<AddressModel>> callAddress = api.getlistAddress(email,password);
                        callAddress.enqueue(new Callback<ArrayList<AddressModel>>() {
                            @Override
                            public void onResponse(Call<ArrayList<AddressModel>> call, Response<ArrayList<AddressModel>> response) {
                                if (response.isSuccessful() && response.body() != null){
                                    ArrayList<AddressModel> list = response.body();
                                    listAddress.clear();
                                    listAddress.addAll(list);
                                    adapterAddress.notifyDataSetChanged();
                                    progressDialog.dismiss();
                                    Log.e("--------------->",list.size()+"");

                                    adapterAddress.OnItemclickListenerAddress(new AdapterAddress.ItemclickListenerAddress() {
                                        @Override
                                        public void OnItemclickAddress(String name, String adress, String sdt,int id) {
                                            txtNamePay.setText(name + "");
                                            txtAddressPay.setText(adress + "");
                                            txtSdtPay.setText(sdt + "");
                                            dialog.cancel();
                                            idaddress = id;
                                        }
                                    });

                                }else {
                                    Toast.makeText(Pay.this, "lỗi listproduct", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            }
                            @Override
                            public void onFailure(Call<ArrayList<AddressModel>> call, Throwable t) {
                                Toast.makeText(Pay.this, "Không có dữ liệu Pay", Toast.LENGTH_SHORT).show();
                                Log.e("--------------->",t+"");
                                progressDialog.dismiss();
                                dialog.cancel();
                            }
                        });
                    }
                }).start();

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Pay.this);
                adapterAddress = new AdapterAddress(listAddress,Pay.this);
                rcvSelectAddress.setLayoutManager(linearLayoutManager);
                rcvSelectAddress.setAdapter(adapterAddress);
                dialog.show();
            }
        });
    }
    float totalPayment;
    private void updatecost(){
        float totalCostItem = 0;
        float Fee = 0;
        for (Cart t : listProductPay){
            totalCostItem += t.getPriceproduct()*t.getQuanlitycart();
        }

        totalPayment = totalCostItem + Fee;
        DecimalFormat decimalFormat = new DecimalFormat("###,### đ");
        txtTotalcostofgoodsDisplay.setText(decimalFormat.format(totalCostItem));
        txtTransportfeeDisplay.setText(decimalFormat.format(Fee));
        txtTotalsettlementDisplay.setText(decimalFormat.format(totalPayment));

        tongtien = String.valueOf((int) totalPayment);
    }
    int payment = 0;
    private void pay(String listPay){

        // 0 oline

        if (rbtnOnline.isChecked()){
            payment = 1;
        }else {
            payment = 0;
        }
        SharedPreferences saveAcc = getSharedPreferences("SaveAcc",MODE_PRIVATE);
        String email = saveAcc.getString("SaveEmail","");
        String password = saveAcc.getString("SavePass","");
        new Thread(new Runnable() {
            @Override
            public void run() {
                handlerAddress.post(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog = new ProgressDialog(Pay.this);
                        progressDialog.setMessage("Loanding");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                    }
                });
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://khoihoang0511.000webhostapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                API api = retrofit.create(API.class);
                Call<String> callDress = api.Pay(listPay,idaddress,payment,email,password);
                callDress.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful() && response.body() != null){
                            progressDialog.dismiss();
                            startActivity(new Intent(Pay.this, PaymentSuccess.class));
                            Toast.makeText(Pay.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            progressDialog.dismiss();
                            Log.e("=------->",response.body()+"");
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(Pay.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Pay.this, PaymentSuccess.class));
                        progressDialog.dismiss();
                    }
                });
            }
        }).start();
    }
    private void payByZalo(){
        CreateOrder orderApi = new CreateOrder();

        handlerAddress.post(new Runnable() {
            @Override
            public void run() {
                progressDialog = new ProgressDialog(Pay.this);
                progressDialog.setMessage("Loading....");
                progressDialog.setCancelable(false);
                progressDialog.show();
            }
        });

        try {
            JSONObject data = orderApi.createOrder(tongtien);
            Log.e("-----a------>",tongtien+"");
            String code = data.getString("return_code");
            Log.d("----------->","return_code: " + code);
            if (code.equals("1")) {
                String token = data.getString("zp_trans_token");
                ZaloPaySDK.getInstance().payOrder(Pay.this, token, "demozpdk://app", new PayOrderListener() {
                    @Override
                    public void onPaymentSucceeded(String s, String s1, String s2) {
                        Toast.makeText(Pay.this, "Thành công", Toast.LENGTH_SHORT).show();
                        pay(new Gson().toJson(listProductPay));
                    }

                    @Override
                    public void onPaymentCanceled(String s, String s1) {
                        Toast.makeText(Pay.this, "Đã hủy", Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                    }

                    @Override
                    public void onPaymentError(ZaloPayError zaloPayError, String s, String s1) {

                    }

                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void onNewIntent(Intent intent){
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }
}