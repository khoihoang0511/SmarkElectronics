package com.example.smarkelectronics.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smarkelectronics.Model.SelectAddressModel;
import com.example.smarkelectronics.R;

import java.util.List;

public class AdapterSelectAddress extends RecyclerView.Adapter<AdapterSelectAddress.SelectAddressViewHolder>{
    private List<SelectAddressModel> SelectAddressList;

    public AdapterSelectAddress(List<SelectAddressModel> selectAddressList) {
        SelectAddressList = selectAddressList;
    }

    @NonNull
    @Override
    public SelectAddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_selectaddress,parent,false);
        return new SelectAddressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectAddressViewHolder holder, int position) {
        SelectAddressModel selectAddress = SelectAddressList.get(position);
        if (selectAddress == null){
            return;
        }
        holder.txtNameSelectAddress.setText(selectAddress.getName());
        holder.txtDetailedAddressSelectAddress.setText(selectAddress.getAddress());
        holder.txtPhoneSelectAddress.setText(selectAddress.getPhonenumber());
        holder.txtCitySelectAddress.setText(selectAddress.getCity());
        holder.txtDistrictSelectAddress.setText(selectAddress.getDistrict());
        holder.txtWardSelectAddress.setText(selectAddress.getWard());
    }

    @Override
    public int getItemCount() {
        if (SelectAddressList !=null){
            return SelectAddressList.size();
        }
        return 0;
    }

    class SelectAddressViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNameSelectAddress,txtDetailedAddressSelectAddress,txtWardSelectAddress,txtDistrictSelectAddress,txtCitySelectAddress,txtPhoneSelectAddress;
        public SelectAddressViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameSelectAddress = itemView.findViewById(R.id.txtNameSelectAddress);
            txtDetailedAddressSelectAddress = itemView.findViewById(R.id.txtDetailedAddressSelectAddress);
            txtWardSelectAddress = itemView.findViewById(R.id.txtWardSelectAddress);
            txtDistrictSelectAddress = itemView.findViewById(R.id.txtDistrictSelectAddress);
            txtCitySelectAddress = itemView.findViewById(R.id.txtCitySelectAddress);
            txtPhoneSelectAddress = itemView.findViewById(R.id.txtPhoneSelectAddress);
        }
    }
}
