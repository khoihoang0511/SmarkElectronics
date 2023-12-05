package com.example.smarkelectronics.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.smarkelectronics.Adapter.AdapterFavorite;
import com.example.smarkelectronics.Model.CategoryModel;
import com.example.smarkelectronics.Model.Favorite;
import com.example.smarkelectronics.Model.product;
import com.example.smarkelectronics.R;
import com.example.smarkelectronics.api.API;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class fragment_favorite extends Fragment {

    private ProgressDialog progressDialog;
    private Handler handlerfavorite = new Handler();
    AdapterFavorite adapterFavorite;
    ArrayList<Favorite> favoritelist;
    RecyclerView rcvfavorite;
    Button btnAddalltocart;
    ImageView opensearchfavorite;
    SearchView searchfavorite;
    ArrayList<Favorite> all;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        rcvfavorite = view.findViewById(R.id.rcvfavorite);
        favoritelist = new ArrayList<>();
        btnAddalltocart = view.findViewById(R.id.btnAddalltocart);
        opensearchfavorite = view.findViewById(R.id.opensearchfavorite);
        searchfavorite = view.findViewById(R.id.searchfavorite);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                handlerfavorite.post(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog = new ProgressDialog(getContext());
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
                Call<ArrayList<Favorite>> callfavorite = api.getlistfavorite();
                callfavorite.enqueue(new Callback<ArrayList<Favorite>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Favorite>> call, Response<ArrayList<Favorite>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            ArrayList<Favorite> list = response.body();
                            favoritelist.clear();
                            favoritelist.addAll(list);
                            all.clear();
                            all.addAll(list);
                            adapterFavorite.notifyDataSetChanged();
                            progressDialog.dismiss();
                        }else {
                            Toast.makeText(getContext(), "LỖI", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Favorite>> call, Throwable t) {
                        Toast.makeText(getContext(), "There are no favorite products", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
            }
        }).start();
        all = new ArrayList<>();
        adapterFavorite = new AdapterFavorite(getContext(),favoritelist);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvfavorite.setLayoutManager(layoutManager);
        rcvfavorite.setAdapter(adapterFavorite);
        adapterFavorite.SetOnclickfavorite(new AdapterFavorite.Onclickfavorite() {
            @Override
            public void clickfavorite(int position) {

            }
        });
        adapterFavorite.OnDeleteItemFavorite(new AdapterFavorite.DeleteItemFavorite() {
            @Override
            public void DeleteItemFavorite(int position) {
                favoritelist.remove(position);
                adapterFavorite.notifyDataSetChanged();
            }
        });
        btnAddalltocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Addalltocart(new Gson().toJson(favoritelist));
                Log.e("-------->",new Gson().toJson(favoritelist)+"");
            }
        });
        opensearchfavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchfavorite.getVisibility() ==View.VISIBLE){
                    searchfavorite.setVisibility(View.GONE);
                }else
                    searchfavorite.setVisibility(View.VISIBLE);
            }
        });
        searchfavorite.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()){
                    favoritelist.clear();
                    favoritelist.addAll(all);
                    adapterFavorite.notifyDataSetChanged();
                }else {
                    favoritelist.clear();
                    for (Favorite th: all){
                        if (th.getNameproduct().contains(newText)){
                            favoritelist.add(th);
                        }
                    }
                    adapterFavorite.notifyDataSetChanged();
                }
                return true;
            }
        });
    }
    private void Addalltocart(String addall){
        new Thread(new Runnable() {
            @Override
            public void run() {
                handlerfavorite.post(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog = new ProgressDialog(getContext());
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
                Call<String> callproduct = api.addalltocart(addall);
                callproduct.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(getContext(), "All products have been added to the cart", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(getContext(), "Lỗi mạng", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
            }
        }).start();
    }
}