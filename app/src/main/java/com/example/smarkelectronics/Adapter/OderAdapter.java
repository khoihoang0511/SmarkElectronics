package com.example.smarkelectronics.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import com.example.smarkelectronics.Activity.OderActivity;
import com.example.smarkelectronics.Model.OderModel;
import com.example.smarkelectronics.R;
import com.example.smarkelectronics.api.API;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OderAdapter extends RecyclerView.Adapter<OderAdapter.ViewHolder> {
    private Context context;
    private ArrayList<OderModel> listOder;
    private ProgressDialog progressDialog;
    private Handler handlerAddress = new Handler();

    public OderAdapter(Context context, ArrayList<OderModel> listOder) {
        this.context = context;
        this.listOder = listOder;
    }

    @NonNull
    @Override
    public OderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_oder_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OderAdapter.ViewHolder holder, int position) {
        OderModel oderModel = listOder.get(holder.getAdapterPosition());
        try{
            Glide.with(context)
                    .load(oderModel.getImgProductOder())
                    .into(holder.imgProductOder);
            holder.txtNameSPOder.setText(oderModel.getNameProduct()+"");
            holder.txtPriceProductOder.setText(oderModel.getPriceProductOder()+"");
            holder.txtQuantityOder.setText(oderModel.getQuantityorder()+"");
            holder.txtTotalPaymentOder.setText(oderModel.getCosts()+"");
            String nameStatus = oderModel.getStatus() == 0 ?"Đang xác nhận": oderModel.getStatus() == 1?"Đang giao": oderModel.getStatus() == 2?"Giao thành công":"Đã hủy";
            holder.txtStatusOderDisplay.setText(nameStatus+"");

            if (oderModel.getStatus() != 0 ){
                holder.btnStatusOder.setVisibility(View.GONE);
            }else {
                holder.btnStatusOder.setVisibility(View.VISIBLE);
            }
            holder.btnStatusOder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancelOder(listOder.get(holder.getAdapterPosition()).getIdmyoder());
                }
            });
        }catch (Exception e){

        }

    }

    private void cancelOder(int id){
        new Thread(new Runnable() {
            @Override
            public void run() {
                handlerAddress.post(new Runnable() {
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
                Call<String> callDress = api.delete_ItemOder(id);
                callDress.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful() && response.body() !=null){
                            context.startActivity(new Intent(context,OderActivity.class));
                            progressDialog.dismiss();
                        }else {
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(context, "Lỗi mạng", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
            }
        }).start();
    }

    @Override
    public int getItemCount() {
        return listOder.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProductOder;
        TextView txtNameSPOder,txtPriceProductOder,txtQuantityOder,txtTotalPaymentOder,txtStatusOderDisplay;
        Button btnStatusOder;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProductOder = itemView.findViewById(R.id.imgProductOder);
            txtNameSPOder = itemView.findViewById(R.id.txtNameSPOder);
            txtPriceProductOder = itemView.findViewById(R.id.txtPriceProductOder);
            txtQuantityOder = itemView.findViewById(R.id.txtQuantityOder);
            txtTotalPaymentOder = itemView.findViewById(R.id.txtTotalPaymentOder);
            btnStatusOder = itemView.findViewById(R.id.btnStatusOder);
            txtStatusOderDisplay = itemView.findViewById(R.id.txtStatusOderDisplay);
        }
    }
}
