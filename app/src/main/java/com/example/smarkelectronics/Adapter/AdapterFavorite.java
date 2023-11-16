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
import com.example.smarkelectronics.Model.Favorite;
import com.example.smarkelectronics.R;

import java.util.ArrayList;

public class AdapterFavorite extends RecyclerView.Adapter<AdapterFavorite.ViewHolder> {
    Context context;
    ArrayList<Favorite> listfavorite;
    private Onclickfavorite onclickfavorite;



    public AdapterFavorite(Context context, ArrayList<Favorite> listfavorite) {
        this.context = context;
        this.listfavorite = listfavorite;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_donhangnew,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFavorite.ViewHolder holder, int position) {
        int vitri = position;
        Favorite favorite = listfavorite.get(vitri);
        Glide.with(context).load(favorite.getImgavatar()).into(holder.imgfavorite);
        holder.tvnamefavorite.setText(favorite.getNameproduct());
        holder.tvpricefavorite.setText(""+favorite.getPriceproduct());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickfavorite.clickfavorite(vitri);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listfavorite.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvnamefavorite, tvpricefavorite, tvdeletefavorite, tvaddcartfavorite;
        ImageView imgfavorite;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvaddcartfavorite = itemView.findViewById(R.id.tvaddcartfavorite);
            tvdeletefavorite = itemView.findViewById(R.id.tvdeletefavorite);
            tvpricefavorite = itemView.findViewById(R.id.tvpricefavorite);
            tvnamefavorite = itemView.findViewById(R.id.tvnamefavorite);
            imgfavorite = itemView.findViewById(R.id.imgfavorite);
        }
    }
}
