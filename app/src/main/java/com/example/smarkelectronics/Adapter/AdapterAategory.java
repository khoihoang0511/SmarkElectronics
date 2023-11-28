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
import com.example.smarkelectronics.Model.CategoryModel;
import com.example.smarkelectronics.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterAategory extends RecyclerView.Adapter<AdapterAategory.ViewHolder> {

    private Context context;
    private ArrayList<CategoryModel> listCategory;

    public AdapterAategory(Context context, ArrayList<CategoryModel> listCategory) {
        this.context = context;
        this.listCategory = listCategory;
    }

    public interface ItemclickListener{
        void OnItemclick(int position);
    }
    private ItemclickListener itemclickListener;
    public void OnItemclickListener(ItemclickListener itemclickListener){
        this.itemclickListener = itemclickListener;
    }

    @NonNull
    @Override
    public AdapterAategory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sanpham,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAategory.ViewHolder holder, int position) {
        CategoryModel categoryModel = listCategory.get(holder.getAdapterPosition());
        try{
            DecimalFormat decimalFormat = new DecimalFormat("###,### đ");
            Glide.with(context)
                    .load(categoryModel.getImgavatar())
                    .into(holder.img_sp);
            holder.tvtensp.setText(categoryModel.getNameproduct()+"");
            holder.tvgiasp.setText(decimalFormat.format(categoryModel.getPriceproduct()));
//            holder.tvgiohang.setText(categoryModel.getNameproduct()+"");
        }catch (Exception e){

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemclickListener.OnItemclick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCategory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_sp;
        TextView tvgiohang,tvtensp,tvgiasp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_sp = itemView.findViewById(R.id.img_sp);
            tvgiohang = itemView.findViewById(R.id.tvgiohang);
            tvtensp = itemView.findViewById(R.id.tvtensp);
            tvgiasp = itemView.findViewById(R.id.tvgiasp);
        }
    }
}
