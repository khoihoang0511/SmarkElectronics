package com.example.smarkelectronics.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarkelectronics.Adapter.SliderProductAdapter;
import com.example.smarkelectronics.Model.product;
import com.example.smarkelectronics.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

    SliderProductAdapter sliderProductAdapter;
    List<Integer> list;
    int i = 1;
    int position;

    //list nhập dữ liệu từ fragmenthome
    ArrayList<product> productlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ViewPager2 viewPagerProduct = findViewById(R.id.product_view_paper);
        TextView tvmincount = findViewById(R.id.tvmincount);
        TextView tvquantityProduct = findViewById(R.id.tvquantityProduct);
        TextView tvmaxcount = findViewById(R.id.tvmaxcount);
        TextView tvnameProduct = findViewById(R.id.tvnameProduct);
        TextView tvPriceProduct = findViewById(R.id.tvPriceProduct);
        TextView tvnote = findViewById(R.id.tvnote);
        TextView tvback = findViewById(R.id.tvback);

        //hiển thị danh sách ảnh
        list = new ArrayList<>();
        list.add(R.drawable.mac);
        list.add(R.drawable.honor);
        list.add(R.drawable.laptop_sp);

        sliderProductAdapter = new SliderProductAdapter(list);
        viewPagerProduct.setAdapter(sliderProductAdapter);

        //Hiển thị indicator slider
        LinearLayout linearLayoutProduct = findViewById(R.id.indicator_layoutproduct);
        int indicatorcount = list.size();
        for (int i = 0 ; i < indicatorcount ; i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.unselected_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(6,0,6,0);//khoảng cách giữa các indicator slider
            imageView.setLayoutParams(params);
            linearLayoutProduct.addView(imageView);
        }

        //set indicator đầu tiên là indicator đc chọn
        ImageView dot = (ImageView) linearLayoutProduct.getChildAt(0);
        dot.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.selected_dot));


        //đặt sự kiện trượt ảnh để khi
        viewPagerProduct.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                for (int i = 0 ; i < indicatorcount ; i++){
                    ImageView view = (ImageView) linearLayoutProduct.getChildAt(i);
                    view.setImageDrawable(ContextCompat.getDrawable(
                            ProductActivity.this,
                            i == position ? R.drawable.selected_dot : R.drawable.unselected_dot));
                }
            }
        });


        tvmincount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i != 1){
                    i--;
                }
                tvquantityProduct.setText(""+i);
            }
        });
        tvmaxcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                tvquantityProduct.setText(""+i);
            }
        });

        //Nhận dữ liệu từ fragmenthome
        Intent intent = getIntent();
        if (intent != null){
            position = intent.getIntExtra("position",0);
            productlist = (ArrayList<product>) intent.getSerializableExtra("list");
        }

        tvnameProduct.setText(productlist.get(position).getNameproduct());
        tvnote.setText(productlist.get(position).getNoteproduct());
        DecimalFormat decimalFormat = new DecimalFormat("###,### đ");
        tvPriceProduct.setText(decimalFormat.format(productlist.get(position).getPriceproduct()));

        tvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });









    }
}