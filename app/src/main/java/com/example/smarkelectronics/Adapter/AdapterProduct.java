package com.example.smarkelectronics.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smarkelectronics.R;
import com.example.smarkelectronics.Model.product;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.Viewholder> {

    private List<product> list;
    private ItemclickListener itemclickListener;

    public interface ItemclickListener{
        void OnItemclick(int position);
    }

    public AdapterProduct(List<product> list, ItemclickListener itemclickListener) {
        this.list = list;
        this.itemclickListener = itemclickListener;
    }

    @NonNull
    @Override
    public AdapterProduct.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProduct.Viewholder holder, int position) {
        holder.tvtensp.setText(list.get(position).getNameproduct());
        DecimalFormat decimalFormat = new DecimalFormat("###,### đ");
        holder.tvgiasp.setText(decimalFormat.format(list.get(position).getPriceproduct()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemclickListener != null){
                    itemclickListener.OnItemclick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView img_sp;
        TextView tvgiohang, tvtensp, tvgiasp;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            img_sp = itemView.findViewById(R.id.img_sp);
            tvgiasp = itemView.findViewById(R.id.tvgiasp);
            tvgiohang = itemView.findViewById(R.id.tvgiohang);
            tvtensp = itemView.findViewById(R.id.tvtensp);
        }
    }
}
