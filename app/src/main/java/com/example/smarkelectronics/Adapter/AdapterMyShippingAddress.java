package com.example.smarkelectronics.Adapter;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smarkelectronics.Activity.Addshippingaddress;
import com.example.smarkelectronics.Activity.CartActivity;
import com.example.smarkelectronics.Activity.MyshipPingAddress;
import com.example.smarkelectronics.Model.AddressModel;
import com.example.smarkelectronics.R;
import com.example.smarkelectronics.api.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapterMyShippingAddress extends RecyclerView.Adapter<AdapterMyShippingAddress.Viewholder>{

    private List<AddressModel> list;
    private Context context;
    private AdapterAddress.ItemclickListenerAddress itemclickListenerAddress;
    private ProgressDialog progressDialog;
    private Handler handlerAddress = new Handler();

    public interface ItemclickListenerAddress{
        void OnItemclickAddress(String name, String adress, String sdt);
    }

    public void OnItemclickListenerAddress(AdapterAddress.ItemclickListenerAddress itemclickListenerAddress){
        this.itemclickListenerAddress = itemclickListenerAddress;
    }

    public AdapterMyShippingAddress(List<AddressModel> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public interface DeleteItemAddress{
        void DeleteAddress (int position);
    }
    private DeleteItemAddress deleteItemAddress;

    public void OnDeleteItemAddress(DeleteItemAddress deleteItemAddress){
        this.deleteItemAddress = deleteItemAddress;
    }

    @NonNull
    @Override
    public AdapterMyShippingAddress.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_myshippingaddress,parent,false);
        return new AdapterMyShippingAddress.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMyShippingAddress.Viewholder holder, int position) {

        Log.e("----->",list.size()+"");
        int vitri = position;
        holder.txtNameSelectAddress.setText(list.get(vitri).getNameaddress());
        holder.txtDetailedAddressSelectAddress.setText(list.get(vitri).getChitiet());
        holder.txtWardSelectAddress.setText(list.get(vitri).getPhuong());
        holder.txtDistrictSelectAddress.setText(list.get(vitri).getQuan());
        holder.txtCitySelectAddress.setText(list.get(vitri).getThanhpho());
        holder.txtPhoneSelectAddress.setText(list.get(vitri).getPhoneaddress().trim()+"");

        holder.btnEditAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Addshippingaddress.class);
                intent.putExtra("Updateaddress",list.get(holder.getAdapterPosition()));

                context.startActivity(intent);
            }
        });

        holder.btnDeleMyshippingaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMyshippingaddress(list.get(holder.getAdapterPosition()).getIdaddress());
                deleteItemAddress.DeleteAddress(holder.getAdapterPosition());
            }
        });


    }

    public void deleteMyshippingaddress(Integer id){
        new Thread(new Runnable() {
            @Override
            public void run() {
                handlerAddress.post(new Runnable() {
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
                API api_address = retrofit_catalog.create(API.class);
                Call<String> call = api_address.delete_ItemInAddress(id);
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
        return list.size();
    }



    public class Viewholder extends RecyclerView.ViewHolder {
        TextView txtNameSelectAddress,txtDetailedAddressSelectAddress,txtWardSelectAddress,txtDistrictSelectAddress,txtCitySelectAddress,txtPhoneSelectAddress;
        Button btnDeleMyshippingaddress,btnEditAddress;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            btnEditAddress = itemView.findViewById(R.id.btnEditAddress);
            btnDeleMyshippingaddress = itemView.findViewById(R.id.btnDeleMyshippingaddress);
            txtNameSelectAddress = itemView.findViewById(R.id.txtNameSelectAddress);
            txtDetailedAddressSelectAddress = itemView.findViewById(R.id.txtDetailedAddressSelectAddress);
            txtWardSelectAddress = itemView.findViewById(R.id.txtWardSelectAddress);
            txtDistrictSelectAddress = itemView.findViewById(R.id.txtDistrictSelectAddress);
            txtCitySelectAddress = itemView.findViewById(R.id.txtCitySelectAddress);
            txtPhoneSelectAddress = itemView.findViewById(R.id.txtPhoneSelectAddress);
        }
    }
}
