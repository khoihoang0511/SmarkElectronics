package com.example.smarkelectronics.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.smarkelectronics.Adapter.AdapterFavorite;
import com.example.smarkelectronics.Model.Favorite;
import com.example.smarkelectronics.Model.product;
import com.example.smarkelectronics.R;
import com.example.smarkelectronics.api.API;

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
                            adapterFavorite.notifyDataSetChanged();
                            progressDialog.dismiss();
                        }else {
                            Toast.makeText(getContext(), "LỖI", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Favorite>> call, Throwable t) {
                        Toast.makeText(getContext(), "Kết nối internet không ổn định", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
        adapterFavorite = new AdapterFavorite(getContext(),favoritelist);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvfavorite.setLayoutManager(layoutManager);
        rcvfavorite.setAdapter(adapterFavorite);
        adapterFavorite.SetOnclickfavorite(new AdapterFavorite.Onclickfavorite() {
            @Override
            public void clickfavorite(int position) {

            }
        });
    }
}