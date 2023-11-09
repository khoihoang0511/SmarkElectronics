package com.example.smarkelectronics.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smarkelectronics.Model.Cart;
import com.example.smarkelectronics.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.ViewHodel> {

    Context context;
    ArrayList<Cart> list;


    public AdapterCart(Context context, ArrayList<Cart> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterCart.ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart,parent,false);
        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCart.ViewHodel holder, int position) {Cart cart = list.get(position);
        holder.tvnamecart.setText(list.get(position).getNameproduct());
        DecimalFormat decimalFormat = new DecimalFormat("###,### đ");
        holder.tvpricecart.setText(decimalFormat.format(list.get(position).getPriceproduct()));
        holder.tvquantityProduct.setText(""+list.get(position).getQuanlitycart());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder {
        CheckBox cboxcart;
        ImageView imgavatar;
        TextView tvnamecart , tvpricecart, tvquantityProduct;
        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            cboxcart = itemView.findViewById(R.id.cboxcart);
            imgavatar = itemView.findViewById(R.id.imgavatar);
            tvnamecart = itemView.findViewById(R.id.tvnamecart);
            tvpricecart = itemView.findViewById(R.id.tvpricecart);
            tvquantityProduct = itemView.findViewById(R.id.tvquantitycart);
        }
    }
}
