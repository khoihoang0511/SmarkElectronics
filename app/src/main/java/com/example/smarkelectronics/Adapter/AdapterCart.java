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

    int tongthanhtoan = 0;




    public AdapterCart(Context context, ArrayList<Cart> list) {
        this.context = context;
        this.list = list;
    }

    //interface Gửi dữ liệu tổng tiền khi checkbox
    public interface OnClickCartCheckbox {
        public void clickcheckBox(int tongtien);
    }
    public OnClickCartCheckbox onClickCartCheckbox;
    public void setOnClickProduct(OnClickCartCheckbox onClickCartCheckbox) {
        this.onClickCartCheckbox = onClickCartCheckbox;
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
        holder.tvnamecart.setText(list.get(position).getNameproduct());
        DecimalFormat decimalFormat = new DecimalFormat("###,### đ");
        holder.tvpricecart.setText(decimalFormat.format(list.get(position).getPriceproduct()));
        holder.tvquantityCart.setText(""+list.get(position).getQuanlitycart());


        holder.tvmincountcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quanlity = Integer.parseInt(holder.tvquantityCart.getText().toString());
                if (quanlity >=2){
                    quanlity--;
                    holder.tvquantityCart.setText(String.valueOf(quanlity));
                }
            }
        });
        holder.tvmaxcountcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quanlity = Integer.parseInt(holder.tvquantityCart.getText().toString());
                if (quanlity >= 1){
                    quanlity++;
                    holder.tvquantityCart.setText(String.valueOf(quanlity));
                }
            }
        });
        holder.cboxcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.cboxcart.isChecked()){
                    int gia = list.get(position).getPriceproduct();
                    int quanlity = Integer.parseInt(holder.tvquantityCart.getText().toString());
                    tongthanhtoan +=(gia*quanlity);
                    onClickCartCheckbox.clickcheckBox(tongthanhtoan);
                }else {
                    int gia = list.get(position).getPriceproduct();
                    int quanlity = Integer.parseInt(holder.tvquantityCart.getText().toString());
                    tongthanhtoan -= (gia*quanlity);
                    onClickCartCheckbox.clickcheckBox(tongthanhtoan);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder {
        CheckBox cboxcart;
        ImageView imgavatar;
        TextView tvnamecart , tvpricecart, tvquantityCart, tvmaxcountcart, tvmincountcart;
        public ViewHodel(@NonNull View itemView) {
            super(itemView);
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
