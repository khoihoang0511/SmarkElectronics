package com.example.smarkelectronics.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smarkelectronics.Model.Cart;
import com.example.smarkelectronics.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterProductPay extends RecyclerView.Adapter<AdapterProductPay.ViewHolder> {

    private Context context;
    private ArrayList<Cart> listProductPay;

    public AdapterProductPay(Context context, ArrayList<Cart> listProductPay) {
        this.context = context;
        this.listProductPay = listProductPay;
    }


    @NonNull
    @Override
    public AdapterProductPay.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rcvpayproduct,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProductPay.ViewHolder holder, int position) {
        DecimalFormat decimalFormat = new DecimalFormat("###,### đ");
        holder.txtPiceProductPay.setText(decimalFormat.format(listProductPay.get(holder.getAdapterPosition()).getPriceproduct()));
        holder.txtProductNamePay.setText(listProductPay.get(holder.getAdapterPosition()).getNameproduct()+"");
        holder.txtQuantityProductPay.setText(listProductPay.get(holder.getAdapterPosition()).getQuanlitycart()+"");
        try{
            Glide.with(context)
                    .load(listProductPay.get(holder.getAdapterPosition()).getImgavatar())
                    .into(holder.imgProductPay);
        }catch (Exception e){

        }
    }

    @Override
    public int getItemCount() {
        return listProductPay.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtProductNamePay,txtPiceProductPay,txtQuantityProductPay;
        public ImageView imgProductPay;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProductNamePay = itemView.findViewById(R.id.txtProductNamePay);
            txtPiceProductPay = itemView.findViewById(R.id.txtPiceProductPay);
            txtQuantityProductPay = itemView.findViewById(R.id.txtQuantityProductPay);
            imgProductPay = itemView.findViewById(R.id.imgProductPay);
        }
    }
}
