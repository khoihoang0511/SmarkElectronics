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
import com.example.smarkelectronics.Model.NotificationModel;
import com.example.smarkelectronics.R;

import java.util.ArrayList;

public class AdapterNotification extends RecyclerView.Adapter<AdapterNotification.viewHolder> {
    Context context;
    ArrayList<NotificationModel> listNotification;

    public AdapterNotification(Context context, ArrayList<NotificationModel> listNotification) {
        this.context = context;
        this.listNotification = listNotification;
    }

    @NonNull
    @Override
    public AdapterNotification.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_thongbaonew,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNotification.viewHolder holder, int position) {
        NotificationModel notificationModel = listNotification.get(holder.getAdapterPosition());
        try{
            Glide.with(context).load(notificationModel.getImgNotification()).into(holder.imgNotification);
            holder.txtNameNotification.setText(notificationModel.getTxtNameNotification()+"");
            holder.txtContentNotification.setText(notificationModel.getTxtContentNotification()+"");
            holder.txtDateNotification.setText(notificationModel.getTxtDateNotification()+"");
        }catch (Exception e){

        }

    }

    @Override
    public int getItemCount() {
        return listNotification.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView imgNotification;
        TextView txtNameNotification,txtContentNotification,txtDateNotification;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imgNotification = itemView.findViewById(R.id.imgNotification);
            txtNameNotification = itemView.findViewById(R.id.txtNameNotification);
            txtContentNotification = itemView.findViewById(R.id.txtContentNotification);
            txtDateNotification = itemView.findViewById(R.id.txtDateNotification);
        }
    }
}
