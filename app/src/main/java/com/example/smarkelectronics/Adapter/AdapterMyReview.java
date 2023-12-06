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
import com.example.smarkelectronics.Model.MyReviewModel;
import com.example.smarkelectronics.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterMyReview extends RecyclerView.Adapter<AdapterMyReview.ViewHolder>{
    Context context;
    ArrayList<MyReviewModel> listMyreview;

    public AdapterMyReview(Context context, ArrayList<MyReviewModel> listMyreview) {
        this.context = context;
        this.listMyreview = listMyreview;
    }

    @NonNull
    @Override
    public AdapterMyReview.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_myreview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMyReview.ViewHolder holder, int position) {
        MyReviewModel myReviewModel = listMyreview.get(holder.getAdapterPosition());
        try {
            DecimalFormat decimalFormat = new DecimalFormat("###,### đ");
            Glide.with(context).load(myReviewModel.getImgMyreview()).into(holder.imgMyreview);
            holder.txtNameMyReview.setText(myReviewModel.getTxtNameMyReview());
            holder.txtProductPriceMyReview.setText(decimalFormat.format(myReviewModel.getTxtProductPriceMyReview()));
            holder.txtDayMonthYear.setText(myReviewModel.getTxtDayMonthYear()+"");
            holder.txtContent.setText(myReviewModel.getTxtContent()+"");
            if (myReviewModel.getStar() ==0 ){
                holder.imgstar1.setImageResource(R.drawable.baseline_star_grey_24);
                holder.imgstar2.setImageResource(R.drawable.baseline_star_grey_24);
                holder.imgstar3.setImageResource(R.drawable.baseline_star_grey_24);
                holder.imgstar4.setImageResource(R.drawable.baseline_star_grey_24);
                holder.imgstar5.setImageResource(R.drawable.baseline_star_grey_24);
            }else if (myReviewModel.getStar() == 1){
                holder.imgstar1.setImageResource(R.drawable.baseline_star_yellow_24);
                holder.imgstar2.setImageResource(R.drawable.baseline_star_grey_24);
                holder.imgstar3.setImageResource(R.drawable.baseline_star_grey_24);
                holder.imgstar4.setImageResource(R.drawable.baseline_star_grey_24);
                holder.imgstar5.setImageResource(R.drawable.baseline_star_grey_24);
            }else if (myReviewModel.getStar() == 2) {
                holder.imgstar1.setImageResource(R.drawable.baseline_star_yellow_24);
                holder.imgstar2.setImageResource(R.drawable.baseline_star_yellow_24);
                holder.imgstar3.setImageResource(R.drawable.baseline_star_grey_24);
                holder.imgstar4.setImageResource(R.drawable.baseline_star_grey_24);
                holder.imgstar5.setImageResource(R.drawable.baseline_star_grey_24);
            }else if (myReviewModel.getStar() == 3) {
                holder.imgstar1.setImageResource(R.drawable.baseline_star_yellow_24);
                holder.imgstar2.setImageResource(R.drawable.baseline_star_yellow_24);
                holder.imgstar3.setImageResource(R.drawable.baseline_star_yellow_24);
                holder.imgstar4.setImageResource(R.drawable.baseline_star_grey_24);
                holder.imgstar5.setImageResource(R.drawable.baseline_star_grey_24);
            }else if (myReviewModel.getStar() == 4) {
                holder.imgstar1.setImageResource(R.drawable.baseline_star_yellow_24);
                holder.imgstar2.setImageResource(R.drawable.baseline_star_yellow_24);
                holder.imgstar3.setImageResource(R.drawable.baseline_star_yellow_24);
                holder.imgstar4.setImageResource(R.drawable.baseline_star_yellow_24);
                holder.imgstar5.setImageResource(R.drawable.baseline_star_grey_24);
            }else if (myReviewModel.getStar() == 5) {
                holder.imgstar1.setImageResource(R.drawable.baseline_star_yellow_24);
                holder.imgstar2.setImageResource(R.drawable.baseline_star_yellow_24);
                holder.imgstar3.setImageResource(R.drawable.baseline_star_yellow_24);
                holder.imgstar4.setImageResource(R.drawable.baseline_star_yellow_24);
                holder.imgstar5.setImageResource(R.drawable.baseline_star_yellow_24);
            }
        }catch (Exception e){

        }
    }

    @Override
    public int getItemCount() {
        return listMyreview.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgMyreview,imgstar1,imgstar2,imgstar3,imgstar4,imgstar5;
        TextView txtNameMyReview,txtProductPriceMyReview,txtDayMonthYear,txtContent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMyreview = itemView.findViewById(R.id.imgMyreview);
            imgstar1 = itemView.findViewById(R.id.imgstar1);
            imgstar2 = itemView.findViewById(R.id.imgstar2);
            imgstar3 = itemView.findViewById(R.id.imgstar3);
            imgstar4 = itemView.findViewById(R.id.imgstar4);
            imgstar5 = itemView.findViewById(R.id.imgstar5);
            txtNameMyReview = itemView.findViewById(R.id.txtNameMyReview);
            txtProductPriceMyReview = itemView.findViewById(R.id.txtProductPriceMyReview);
            txtDayMonthYear = itemView.findViewById(R.id.txtDayMonthYear);
            txtContent = itemView.findViewById(R.id.txtContent);
        }
    }
}
