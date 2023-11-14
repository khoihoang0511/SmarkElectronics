package com.example.smarkelectronics.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smarkelectronics.Model.AddressModel;
import com.example.smarkelectronics.Model.product;
import com.example.smarkelectronics.R;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterAddress extends RecyclerView.Adapter<AdapterAddress.Viewholder> {
    private List<AddressModel> list;
    private Context context;
    private ItemclickListenerAddress itemclickListenerAddress;

    public interface ItemclickListenerAddress{
        void OnItemclickAddress(int position);
    }

    public AdapterAddress(List<AddressModel> list, Context context, ItemclickListenerAddress itemclickListenerAddress) {
        this.list = list;
        this.context = context;
        this.itemclickListenerAddress = itemclickListenerAddress;
    }

    @NonNull
    @Override
    public AdapterAddress.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_selectaddress,parent,false);
        return new AdapterAddress.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAddress.Viewholder holder, int position) {
        Log.e("----->",list.size()+"");
        int vitri = position;
        holder.txtNameSelectAddress.setText(list.get(vitri).getNameaddress());
        holder.txtDetailedAddressSelectAddress.setText(list.get(vitri).getChitiet());
        holder.txtWardSelectAddress.setText(list.get(vitri).getPhuong());
        holder.txtDistrictSelectAddress.setText(list.get(vitri).getQuan());
        holder.txtCitySelectAddress.setText(list.get(vitri).getThanhpho());
        holder.txtPhoneSelectAddress.setText(list.get(vitri).getPhoneaddress());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemclickListenerAddress != null){
                    itemclickListenerAddress.OnItemclickAddress(vitri);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView txtNameSelectAddress,txtDetailedAddressSelectAddress,txtWardSelectAddress,txtDistrictSelectAddress,txtCitySelectAddress,txtPhoneSelectAddress;
        RadioButton SelectAddress;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            SelectAddress = itemView.findViewById(R.id.SelectAddress);
            txtNameSelectAddress = itemView.findViewById(R.id.txtNameSelectAddress);
            txtDetailedAddressSelectAddress = itemView.findViewById(R.id.txtDetailedAddressSelectAddress);
            txtWardSelectAddress = itemView.findViewById(R.id.txtWardSelectAddress);
            txtDistrictSelectAddress = itemView.findViewById(R.id.txtDistrictSelectAddress);
            txtCitySelectAddress = itemView.findViewById(R.id.txtCitySelectAddress);
            txtPhoneSelectAddress = itemView.findViewById(R.id.txtPhoneSelectAddress);
        }
    }
}
