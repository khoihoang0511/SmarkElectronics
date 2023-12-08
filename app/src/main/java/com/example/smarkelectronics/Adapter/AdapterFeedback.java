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
import com.example.smarkelectronics.Model.FeedbackModel;
import com.example.smarkelectronics.R;

import java.util.ArrayList;

public class AdapterFeedback extends RecyclerView.Adapter<AdapterFeedback.viewHolder> {
    Context context;
    ArrayList<FeedbackModel> listFeedback;

    public AdapterFeedback(Context context, ArrayList<FeedbackModel> listFeedback) {
        this.context = context;
        this.listFeedback = listFeedback;
    }

    @NonNull
    @Override
    public AdapterFeedback.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_feedback,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFeedback.viewHolder holder, int position) {
        FeedbackModel feedbackModel = listFeedback.get(holder.getAdapterPosition());
        try {
            Glide.with(context).load(feedbackModel.getImgMyFeedback()).into(holder.imgMyFeedback);
            holder.txtNameMyFeedback.setText(feedbackModel.getTxtNameMyFeedback()+"");
            holder.txtDayMonthYearFeedback.setText(feedbackModel.getTxtDayMonthYearFeedback()+"");
            holder.txtContentFeedback.setText(feedbackModel.getTxtContentFeedback()+"");
            if (feedbackModel.getStarFeedback() ==0 ){
                holder.imgstarFeedback1.setImageResource(R.drawable.baseline_star_grey_24);
                holder.imgstarFeedback2.setImageResource(R.drawable.baseline_star_grey_24);
                holder.imgstarFeedback3.setImageResource(R.drawable.baseline_star_grey_24);
                holder.imgstarFeedback4.setImageResource(R.drawable.baseline_star_grey_24);
                holder.imgstarFeedback5.setImageResource(R.drawable.baseline_star_grey_24);
            }else if (feedbackModel.getStarFeedback() == 1){
                holder.imgstarFeedback1.setImageResource(R.drawable.baseline_star_yellow_24);
                holder.imgstarFeedback2.setImageResource(R.drawable.baseline_star_grey_24);
                holder.imgstarFeedback3.setImageResource(R.drawable.baseline_star_grey_24);
                holder.imgstarFeedback4.setImageResource(R.drawable.baseline_star_grey_24);
                holder.imgstarFeedback5.setImageResource(R.drawable.baseline_star_grey_24);
            }else if (feedbackModel.getStarFeedback() == 2) {
                holder.imgstarFeedback1.setImageResource(R.drawable.baseline_star_yellow_24);
                holder.imgstarFeedback2.setImageResource(R.drawable.baseline_star_yellow_24);
                holder.imgstarFeedback3.setImageResource(R.drawable.baseline_star_grey_24);
                holder.imgstarFeedback4.setImageResource(R.drawable.baseline_star_grey_24);
                holder.imgstarFeedback5.setImageResource(R.drawable.baseline_star_grey_24);
            }else if (feedbackModel.getStarFeedback() == 3) {
                holder.imgstarFeedback1.setImageResource(R.drawable.baseline_star_yellow_24);
                holder.imgstarFeedback2.setImageResource(R.drawable.baseline_star_yellow_24);
                holder.imgstarFeedback3.setImageResource(R.drawable.baseline_star_yellow_24);
                holder.imgstarFeedback4.setImageResource(R.drawable.baseline_star_grey_24);
                holder.imgstarFeedback5.setImageResource(R.drawable.baseline_star_grey_24);
            }else if (feedbackModel.getStarFeedback() == 4) {
                holder.imgstarFeedback1.setImageResource(R.drawable.baseline_star_yellow_24);
                holder.imgstarFeedback2.setImageResource(R.drawable.baseline_star_yellow_24);
                holder.imgstarFeedback3.setImageResource(R.drawable.baseline_star_yellow_24);
                holder.imgstarFeedback4.setImageResource(R.drawable.baseline_star_yellow_24);
                holder.imgstarFeedback5.setImageResource(R.drawable.baseline_star_grey_24);
            }else if (feedbackModel.getStarFeedback() == 5) {
                holder.imgstarFeedback1.setImageResource(R.drawable.baseline_star_yellow_24);
                holder.imgstarFeedback2.setImageResource(R.drawable.baseline_star_yellow_24);
                holder.imgstarFeedback3.setImageResource(R.drawable.baseline_star_yellow_24);
                holder.imgstarFeedback4.setImageResource(R.drawable.baseline_star_yellow_24);
                holder.imgstarFeedback5.setImageResource(R.drawable.baseline_star_yellow_24);
            }
        }catch (Exception e){

        }
    }

    @Override
    public int getItemCount() {
        return listFeedback.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView imgMyFeedback,imgstarFeedback1,imgstarFeedback2,imgstarFeedback3,imgstarFeedback4,imgstarFeedback5;
        TextView txtNameMyFeedback,txtDayMonthYearFeedback,txtContentFeedback;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imgMyFeedback = itemView.findViewById(R.id.imgMyFeedback);
            imgstarFeedback1 = itemView.findViewById(R.id.imgstarFeedback1);
            imgstarFeedback2 = itemView.findViewById(R.id.imgstarFeedback2);
            imgstarFeedback3 = itemView.findViewById(R.id.imgstarFeedback3);
            imgstarFeedback4 = itemView.findViewById(R.id.imgstarFeedback4);
            imgstarFeedback5 = itemView.findViewById(R.id.imgstarFeedback5);
            txtNameMyFeedback = itemView.findViewById(R.id.txtNameMyFeedback);
            txtDayMonthYearFeedback = itemView.findViewById(R.id.txtDayMonthYearFeedback);
            txtContentFeedback = itemView.findViewById(R.id.txtContentFeedback);
        }
    }
}
