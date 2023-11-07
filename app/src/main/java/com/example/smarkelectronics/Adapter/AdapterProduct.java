package com.example.smarkelectronics.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smarkelectronics.R;
import com.example.smarkelectronics.product;

import java.util.ArrayList;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.Viewholder> {

    Context context ;
    ArrayList<product> list;

    public AdapterProduct(Context context, ArrayList<product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterProduct.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sanpham,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProduct.Viewholder holder, int position) {
        holder.tvtensp.setText(list.get(position).getNameproduct());
        holder.tvgiasp.setText(""+list.get(position).getPriceproduct());
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
