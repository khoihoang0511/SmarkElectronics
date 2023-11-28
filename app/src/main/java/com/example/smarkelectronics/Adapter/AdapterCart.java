package com.example.smarkelectronics.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smarkelectronics.Activity.CartActivity;
import com.example.smarkelectronics.Model.Cart;
import com.example.smarkelectronics.Model.CartCheckBoxModel;
import com.example.smarkelectronics.R;
import com.example.smarkelectronics.api.API;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.ViewHodel> {

    Context context;
    ArrayList<CartCheckBoxModel> list;

    int tongthanhtoan = 0;

    private ProgressDialog progressDialog;
    private Handler handlercart = new Handler();


    public AdapterCart(Context context, ArrayList<CartCheckBoxModel> list) {
        this.context = context;
        this.list = list;
    }

    //interface Gửi dữ liệu tổng tiền khi checkbox
    public interface OnClickCartCheckbox {
        public void clickcheckBox(boolean check,int vitri);
    }
    public OnClickCartCheckbox onClickCartCheckbox;
    public void setOnClickProduct(OnClickCartCheckbox onClickCartCheckbox) {
        this.onClickCartCheckbox = onClickCartCheckbox;
    }

    //Truyền dữ liều từ adapter sang activity
    public interface UpdatecartQuantity{
        void CartQuantity (int position, int Quantity);
    }
    private UpdatecartQuantity updatecartQuantity;

    public void SetUpdatecartQuantity(UpdatecartQuantity updatecartQuantity){
        this.updatecartQuantity = updatecartQuantity;
    }
    public interface DeleteItemCart{
        void deleteItemCart (int position);
    }
    private DeleteItemCart deleteItemCart;

    public void OnDeleteItemCart(DeleteItemCart deleteItemCart){
        this.deleteItemCart = deleteItemCart;
    }
    //---------

    @NonNull
    @Override
    public AdapterCart.ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart,parent,false);
        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCart.ViewHodel holder, int position) {
        int vitricart = position;
        holder.tvnamecart.setText(list.get(vitricart).getNameproduct());
        DecimalFormat decimalFormat = new DecimalFormat("###,### đ");
        Glide.with(context)
                .load(list.get(vitricart).getImgavatar()).into(holder.imgavatar);
        holder.tvpricecart.setText(decimalFormat.format(list.get(vitricart).getPriceproduct()));
        holder.tvquantityCart.setText(""+list.get(vitricart).getQuanlitycart());


        holder.tvmincountcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quanlity = Integer.parseInt(holder.tvquantityCart.getText().toString());
                if (quanlity >=2){
                    quanlity--;
                    holder.tvquantityCart.setText(String.valueOf(quanlity));
                    updatecartQuantity.CartQuantity(holder.getAdapterPosition(), quanlity);
                }
            }
        });

        holder.btnDeleteCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItemCart(list.get(holder.getAdapterPosition()).getIdcart());
                deleteItemCart.deleteItemCart(holder.getAdapterPosition());
            }
        });
        holder.tvmaxcountcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quanlity = Integer.parseInt(holder.tvquantityCart.getText().toString());
                if (quanlity >= 1){
                    quanlity++;
                    holder.tvquantityCart.setText(String.valueOf(quanlity));
                    updatecartQuantity.CartQuantity(holder.getAdapterPosition(), quanlity);
                }
            }
        });

        holder.cboxcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(holder.getAdapterPosition()).isCheck())
                list.get(holder.getAdapterPosition()).setCheck(false);
                else
                    list.get(holder.getAdapterPosition()).setCheck(true);
                onClickCartCheckbox.clickcheckBox(list.get(holder.getAdapterPosition()).isCheck(), holder.getAdapterPosition());
            }
        });

    }

    private void deleteItemCart(Integer id){
        new Thread(new Runnable() {
            @Override
            public void run() {

                handlercart.post(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog = new ProgressDialog(context);
                        progressDialog.setMessage("Loading");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                    }
                });

                Retrofit retrofit_catalog = new Retrofit.Builder()
                        .baseUrl("https://khoihoang0511.000webhostapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                API api_cart = retrofit_catalog.create(API.class);
                Call<String> call = api_cart.delete_ItemInCare(id);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                        progressDialog.cancel();

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                       notifyDataSetChanged();
                        progressDialog.cancel();
                    }
                });
            }
        }).start();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder {
        CheckBox cboxcart;
        ImageView imgavatar;
        TextView tvnamecart , tvpricecart, tvquantityCart, tvmaxcountcart, tvmincountcart;
        Button btnDeleteCart;
        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            btnDeleteCart = itemView.findViewById(R.id.btnDeleteCart);
            cboxcart = itemView.findViewById(R.id.cboxcart);
            imgavatar = itemView.findViewById(R.id.imgavatar);
            tvnamecart = itemView.findViewById(R.id.tvnamecart);
            tvpricecart = itemView.findViewById(R.id.tvpricecart);
            tvquantityCart = itemView.findViewById(R.id.tvquantitycart);
            tvmaxcountcart = itemView.findViewById(R.id.tvmaxcountcart);
            tvmincountcart = itemView.findViewById(R.id.tvmincountcart);
        }

    }
}
