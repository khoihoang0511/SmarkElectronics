package com.example.smarkelectronics.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.smarkelectronics.Adapter.AdapterAategory;
import com.example.smarkelectronics.Model.CategoryModel;
import com.example.smarkelectronics.Model.product;
import com.example.smarkelectronics.R;
import com.example.smarkelectronics.api.API;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryActivity extends AppCompatActivity {

    RecyclerView rcvCategory;
    LinearLayout LayouTarrange;
    ImageView imgArrange;
    ToggleButton btnTArrangePrice,btnTArrangeLetter;
    private ProgressDialog progressDialog;
    private Handler handlerAddress = new Handler();

    ArrayList<CategoryModel> all;

    ArrayList<CategoryModel> listCategory;
    AdapterAategory adapterAategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        rcvCategory = findViewById(R.id.rcvCategory);
        LayouTarrange = findViewById(R.id.LayouTarrange);
        btnTArrangePrice = findViewById(R.id.btnTArrangePrice);
        btnTArrangeLetter = findViewById(R.id.btnTArrangeLetter);
        imgArrange = findViewById(R.id.imgArrange);
        TextView imgBackCategory = findViewById(R.id.imgBackCategory);
        SearchView searchsanphamCategory = findViewById(R.id.searchsanphamCategory);

        listCategory = new ArrayList<>();
        all = new ArrayList<>();

        int numberOfColumns = 2; // Điều này có thể được thay đổi thành số cột bạn muốn
        GridLayoutManager layoutManager = new GridLayoutManager(CategoryActivity.this, numberOfColumns);
        rcvCategory.setLayoutManager(layoutManager);
        adapterAategory = new AdapterAategory(CategoryActivity.this,listCategory);
        rcvCategory.setAdapter(adapterAategory);

        searchsanphamCategory.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()){
                    listCategory.clear();
                    listCategory.addAll(all);
                    adapterAategory.notifyDataSetChanged();
                }else {
                    listCategory.clear();
                    for (CategoryModel th: all){
                        if (th.getNameproduct().contains(newText)){
                            listCategory.add(th);
                        }
                    }
                    adapterAategory.notifyDataSetChanged();
                }
                return true;
            }
        });

        imgArrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LayouTarrange.getVisibility() == View.VISIBLE)
                    LayouTarrange.setVisibility(View.GONE);
                else
                    LayouTarrange.setVisibility(View.VISIBLE);
            }
        });
        btnTArrangeLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnTArrangeLetter.isChecked()){
                    sortAZ();
                }else {
                    sortZA();
                }
            }
        });
        btnTArrangePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnTArrangePrice.isChecked()){
                    priceincreases();
                }else {
                    reducedprice();
                }
            }
        });

        adapterAategory.OnItemclickListener(new AdapterAategory.ItemclickListener() {
            @Override
            public void OnItemclick(int position) {
                ArrayList<product> listProduct = new ArrayList<>();
                for (CategoryModel a: listCategory){
                    listProduct.add(new product(a.getIdproduct(),a.getImgavatar(),a.getImgavatar2(),a.getImgavatar3(),a.getNameproduct(),a.getPriceproduct(),a.getNoteproduct()));
                }
                Intent intent = new Intent(CategoryActivity.this,ProductActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("listproduct",listProduct);
                startActivity(intent);
            }
        });

//        imgcarttCategory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(CategoryActivity.this,CartActivity.class));
//            }
//        });

        imgBackCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryActivity.this,MainActivity.class));
            }
        });

        Intent getIntent = getIntent();
        if (getIntent!=null){
            getlistCategory(getIntent.getStringExtra("tendanhmuc"));
//            txtNameCategory.setText(getIntent.getStringExtra("tendanhmuc"));
            Toast.makeText(this, getIntent.getStringExtra("tendanhmuc"), Toast.LENGTH_SHORT).show();
        }

    }
    private void getlistCategory(String nameCategory){
        new Thread(new Runnable() {
            @Override
            public void run() {
                handlerAddress.post(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog = new ProgressDialog(CategoryActivity.this);
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
                Call<ArrayList<CategoryModel>> callDress = api.getlistCategory(nameCategory);
                callDress.enqueue(new Callback<ArrayList<CategoryModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<CategoryModel>> call, Response<ArrayList<CategoryModel>> response) {
                        if (response.isSuccessful() && response.body()!=null){
                            ArrayList<CategoryModel> list = response.body();
                            listCategory.clear();
                            listCategory.addAll(list);
                            all.clear();
                            all.addAll(list);
                            adapterAategory.notifyDataSetChanged();
                            progressDialog.dismiss();
                        }else {
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<CategoryModel>> call, Throwable t) {
                        progressDialog.dismiss();
                    }
                });
            }
        }).start();
    }
    private void sortAZ(){
        Collections.sort(listCategory, new Comparator<CategoryModel>() {
            @Override
            public int compare(CategoryModel o1, CategoryModel o2) {
                return o1.getNameproduct().compareTo(o2.getNameproduct());
            }
        });
        adapterAategory.notifyDataSetChanged();
    }
    private void sortZA(){
        Collections.sort(listCategory, new Comparator<CategoryModel>() {
            @Override
            public int compare(CategoryModel o1, CategoryModel o2) {
                return o2.getNameproduct().compareTo(o1.getNameproduct());
            }
        });
        adapterAategory.notifyDataSetChanged();
    }
    private void priceincreases(){
        Collections.sort(listCategory, new Comparator<CategoryModel>() {
            @Override
            public int compare(CategoryModel o1, CategoryModel o2) {
                return (String.valueOf(o1.getPriceproduct())).compareTo((String.valueOf(o2.getPriceproduct())));
            }
        });
        adapterAategory.notifyDataSetChanged();
    }
    private void reducedprice(){
        Collections.sort(listCategory, new Comparator<CategoryModel>() {
            @Override
            public int compare(CategoryModel o1, CategoryModel o2) {
                return (String.valueOf(o2.getPriceproduct())).compareTo((String.valueOf(o1.getPriceproduct())));
            }
        });
        adapterAategory.notifyDataSetChanged();
    }
}