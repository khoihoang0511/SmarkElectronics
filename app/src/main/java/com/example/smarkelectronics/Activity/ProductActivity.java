package com.example.smarkelectronics.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarkelectronics.Adapter.AdapterFeedback;
import com.example.smarkelectronics.Adapter.SliderProductAdapter;
import com.example.smarkelectronics.Model.FeedbackModel;
import com.example.smarkelectronics.Model.product;
import com.example.smarkelectronics.R;
import com.example.smarkelectronics.api.API;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductActivity extends AppCompatActivity {

    SliderProductAdapter sliderProductAdapter;
    List<String> list;
    int i = 1;
    int position;

    int quanlityproduct = 1;
    private ProgressDialog progressDialog;
    private Handler handlerAddress = new Handler();

    //list nhập dữ liệu từ fragmenthome
    ArrayList<product> productlist;
    ArrayList<FeedbackModel> listfeedbackModels;
    AdapterFeedback adapterFeedback;
    RecyclerView rcvFeetback;


    //api đẩy dữ liệu lên php
    private Handler handlerproduct = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ViewPager2 viewPagerProduct = findViewById(R.id.product_view_paper);
        TextView tvmincount = findViewById(R.id.tvmincount);
        TextView tvquantityProduct = findViewById(R.id.tvquantityProductt);
        TextView tvmaxcount = findViewById(R.id.tvmaxcount);
        TextView tvnameProduct = findViewById(R.id.tvnameProduct);
        TextView tvPriceProduct = findViewById(R.id.tvPriceProduct);
        TextView tvnote = findViewById(R.id.tvnote);
        TextView tvback = findViewById(R.id.tvback);
        TextView tvaddcart = findViewById(R.id.btnaddCart);
        Button btnfavorite = findViewById(R.id.btnfavorite);
        ImageView imgcartproduct = findViewById(R.id.imgcartproduct);
        rcvFeetback = findViewById(R.id.rcvFeetback);

//Nhận dữ liệu từ fragmenthome
        Intent intent = getIntent();
        if (intent != null){
            position = intent.getIntExtra("position",0);//nếu không có dữ liệu thì mặc định là 0
            productlist = (ArrayList<product>) intent.getSerializableExtra("listproduct");
        }

        tvnameProduct.setText(productlist.get(position).getNameproduct());
        tvnote.setText(productlist.get(position).getNoteproduct());
        DecimalFormat decimalFormat = new DecimalFormat("###,### đ");
        tvPriceProduct.setText(decimalFormat.format(productlist.get(position).getPriceproduct()));


        //hiển thị danh sách ảnh
        list = new ArrayList<>();
        list.add(productlist.get(position).getImgavatar());
        list.add(productlist.get(position).getImgavatar2());
        list.add(productlist.get(position).getImgavatar3());

        sliderProductAdapter = new SliderProductAdapter(list,this);
        viewPagerProduct.setAdapter(sliderProductAdapter);

        listfeedbackModels = new ArrayList<>();
        adapterFeedback = new AdapterFeedback(ProductActivity.this,listfeedbackModels);
        rcvFeetback.setAdapter(adapterFeedback);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ProductActivity.this);
        rcvFeetback.setLayoutManager(linearLayoutManager);

        feedbacklist();

        //Hiển thị indicator slider
        LinearLayout linearLayoutProduct = findViewById(R.id.indicator_layoutproduct);
        int indicatorcount = list.size();
        for (int i = 0 ; i < indicatorcount ; i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.unselected_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(6,0,6,0);//khoảng cách giữa các indicator slider
            imageView.setLayoutParams(params);
            linearLayoutProduct.addView(imageView);
        }

        //set indicator đầu tiên là indicator đc chọn
        ImageView dot = (ImageView) linearLayoutProduct.getChildAt(0);
        dot.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.selected_dot));


        //đặt sự kiện trượt ảnh để khi
        viewPagerProduct.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                for (int i = 0 ; i < indicatorcount ; i++){
                    ImageView view = (ImageView) linearLayoutProduct.getChildAt(i);
                    view.setImageDrawable(ContextCompat.getDrawable(
                            ProductActivity.this,
                            i == position ? R.drawable.selected_dot : R.drawable.unselected_dot));
                }
            }
        });


        imgcartproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentproduct = new Intent(ProductActivity.this,CartActivity.class);
                startActivity(intentproduct);
            }
        });



        btnfavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    SharedPreferences saveAcc = getSharedPreferences("SaveAcc",MODE_PRIVATE);
                    String email = saveAcc.getString("SaveEmail","");
                    String password = saveAcc.getString("SavePass","");
                    @Override
                    public void run() {
                        handlerproduct.post(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog = new ProgressDialog(ProductActivity.this);
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
                        Call<String> callfavorite = api.addfavorite(productlist.get(position).getIdproduct(),email,password);
                        callfavorite.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if (response.isSuccessful() && response.body() != null){
                                    progressDialog.dismiss();
                                    Toast.makeText(ProductActivity.this, "Đã thêm vào danh mục yêu thích", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(ProductActivity.this, "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Toast.makeText(ProductActivity.this, "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        });
                    }
                }).start();
            }
        });


        tvmincount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i != 1){
                    i--;
                }
                tvquantityProduct.setText(""+i);
            }
        });
        tvmaxcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                tvquantityProduct.setText(""+i);
            }
        });



        tvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        tvaddcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences saveAcc = getSharedPreferences("SaveAcc",MODE_PRIVATE);
                String email = saveAcc.getString("SaveEmail","");
                String password = saveAcc.getString("SavePass","");
             new Thread(new Runnable() {
                 @Override
                 public void run() {
                     handlerproduct.post(new Runnable() {
                         @Override
                         public void run() {
                             progressDialog = new ProgressDialog(ProductActivity.this);
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
                     quanlityproduct = Integer.parseInt(tvquantityProduct.getText().toString());
                     Call<String> callproduct = api.addcart(quanlityproduct,productlist.get(position).getIdproduct(),email,password);
                     callproduct.enqueue(new Callback<String>() {
                         @Override
                         public void onResponse(Call<String> call, Response<String> response) {
                             if (response.isSuccessful() && response.body() != null){
                                 progressDialog.dismiss();
                                 Toast.makeText(ProductActivity.this, "Thêm giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                             }else {
                                 Toast.makeText(ProductActivity.this, "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
                                 progressDialog.dismiss();
                             }
                         }

                         @Override
                         public void onFailure(Call<String> call, Throwable t) {
                             Toast.makeText(ProductActivity.this, "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
                             progressDialog.dismiss();
                         }
                     });
                 }
             }).start();
            }
        });
    }
    private void feedbacklist(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                handlerAddress.post(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog = new ProgressDialog(ProductActivity.this);
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
                Call<ArrayList<FeedbackModel>> call = api.getListFeedback(productlist.get(position).getIdproduct());
                call.enqueue(new Callback<ArrayList<FeedbackModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<FeedbackModel>> call, Response<ArrayList<FeedbackModel>> response) {
                        if (response.isSuccessful() && response.body() !=null){
                            ArrayList<FeedbackModel> feedbackModels = response.body();
                            listfeedbackModels.clear();
                            listfeedbackModels.addAll(feedbackModels);
                            adapterFeedback.notifyDataSetChanged();
                            progressDialog.dismiss();
                        }else {
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<FeedbackModel>> call, Throwable t) {
                        progressDialog.dismiss();
                    }
                });
            }
        }).start();
    }
}