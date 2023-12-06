package com.example.smarkelectronics.Adapter;

import static android.content.Context.MODE_PRIVATE;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smarkelectronics.Activity.ProductActivity;
import com.example.smarkelectronics.Model.Favorite;
import com.example.smarkelectronics.R;
import com.example.smarkelectronics.api.API;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapterFavorite extends RecyclerView.Adapter<AdapterFavorite.ViewHolder> {
    Context context;
    ArrayList<Favorite> listfavorite;
    private Onclickfavorite onclickfavorite;

    private ProgressDialog progressDialog;
    private Handler handlercart = new Handler();



    public AdapterFavorite(Context context, ArrayList<Favorite> listfavorite) {
        this.context = context;
        this.listfavorite = listfavorite;
    }

    public interface DeleteItemFavorite{
        void DeleteItemFavorite (int position);
    }
    private AdapterFavorite.DeleteItemFavorite deleteItemFavorite;

    public void OnDeleteItemFavorite(AdapterFavorite.DeleteItemFavorite deleteItemFavorite){
        this.deleteItemFavorite = deleteItemFavorite;
    }

    public interface Onclickfavorite{
        public void clickfavorite(int position);
    }


    public void SetOnclickfavorite(Onclickfavorite onclickfavorite) {
        this.onclickfavorite = onclickfavorite;
    }

    @NonNull
    @Override
    public AdapterFavorite.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favorite,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFavorite.ViewHolder holder, int position) {
        int vitri = position;
        Favorite favorite = listfavorite.get(vitri);
        Glide.with(context).load(favorite.getImgavatar()).into(holder.imgfavorite);
        holder.tvnamefavorite.setText(favorite.getNameproduct());
        DecimalFormat decimalFormat = new DecimalFormat("###,### đ");
        holder.tvpricefavorite.setText(decimalFormat.format(favorite.getPriceproduct()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickfavorite.clickfavorite(vitri);
            }
        });

        holder.txtdeletefavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItemFavorite(listfavorite.get(holder.getAdapterPosition()).getIdfavorite());
                deleteItemFavorite.DeleteItemFavorite(holder.getAdapterPosition());
            }
        });
        holder.txtaddcartfavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFavoriteCart(listfavorite.get(holder.getAdapterPosition()).getIdproduct());

            }
        });

    }
    private void deleteItemFavorite(Integer id){
        new Thread(new Runnable() {
            @Override
            public void run() {

                handlercart.post(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog = new ProgressDialog(context);
                        progressDialog.setMessage("Loading");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                    }
                });

                Retrofit retrofit_catalog = new Retrofit.Builder()
                        .baseUrl("https://khoihoang0511.000webhostapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                API api_cart = retrofit_catalog.create(API.class);
                Call<String> call = api_cart.delete_ItemInFavorite(id);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                        progressDialog.cancel();

                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        notifyDataSetChanged();
                        progressDialog.cancel();
                    }
                });
            }
        }).start();
    }

    @Override
    public int getItemCount() {
        return listfavorite.size();
    }

    private void addFavoriteCart(int id){
        SharedPreferences saveAcc = context.getSharedPreferences("SaveAcc", MODE_PRIVATE);
        String email = saveAcc.getString("SaveEmail","");
        String password = saveAcc.getString("SavePass","");
        new Thread(new Runnable() {
            @Override
            public void run() {
                handlercart.post(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog = new ProgressDialog(context);
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
                Call<String> callproduct = api.addcart(1,id,email,password);
                callproduct.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful() && response.body() != null){
                            progressDialog.dismiss();
                            Toast.makeText(context, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(context, "Kết nối không ổn định", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
            }
        }).start();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvnamefavorite, tvpricefavorite, txtaddcartfavorite, txtdeletefavorite;
        ImageView imgfavorite;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtaddcartfavorite = itemView.findViewById(R.id.txtaddcartfavorite);
            txtdeletefavorite = itemView.findViewById(R.id.txtdeletefavorite);
            tvpricefavorite = itemView.findViewById(R.id.tvpricefavorite);
            tvnamefavorite = itemView.findViewById(R.id.tvnamefavorite);
            imgfavorite = itemView.findViewById(R.id.imgfavorite);
        }
    }
}
