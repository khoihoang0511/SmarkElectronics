package com.example.smarkelectronics.Fragment;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.smarkelectronics.Adapter.ImageSliderAdapter;
import com.example.smarkelectronics.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    ImageSliderAdapter imageSliderAdapter;
    ViewPager2 viewPager2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private int currentPage = 0;
    private final Handler handler = new Handler();
    private final int delay = 2600; // 5 giây (thời gian trễ giữa các trang)


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager2 = view.findViewById(R.id.view_paper);

        //---hiển thị danh sách ảnh
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.img_slider3);
        list.add(R.drawable.img_slider2);
        list.add(R.drawable.img_slider);
        list.add(R.drawable.img_slider4);
        list.add(R.drawable.img_slider5);

        imageSliderAdapter = new ImageSliderAdapter(list);
        viewPager2.setAdapter(imageSliderAdapter);
        //---hiển thị danh sách ảnh



        //----slider tự động
         Runnable runnable = new Runnable() {
            public void run() {
                if (currentPage == imageSliderAdapter.getItemCount() - 1) {
                    currentPage = 0;
                } else {
                    currentPage++;
                }
                viewPager2.setCurrentItem(currentPage);
                handler.postDelayed(this, delay);
            }
        };

        handler.postDelayed(runnable, delay);
        viewPager2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                handler.removeCallbacks(runnable);
                return false;
            }
        });
        //----slider tự động


        //---- sự kiện indicator khi kéo trượt ảnh
        LinearLayout indicator_layout = view.findViewById(R.id.indicator_layout);
        int indicatorCount =  list.size();


        for (int i = 0 ; i < indicatorCount ; i++){
            ImageView imageView = new ImageView(getContext());
            imageView.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.unselected_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(8,0,8,0);//khoảng cách giữa các indicator slider
            imageView.setLayoutParams(params);
            indicator_layout.addView(imageView);
        }

        //set cố định indicator đầu tiên là indicator dc chọn
        ImageView dot = (ImageView) indicator_layout.getChildAt(0);
        dot.setImageDrawable(ContextCompat.getDrawable(
                getContext(),R.drawable.selected_dot
        ));

        // Đặt sự kiện trượt để cập nhật indicator
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                for (int i = 0 ; i < indicatorCount ; i++){
                    ImageView dot = (ImageView) indicator_layout.getChildAt(i);
                    dot.setImageDrawable(ContextCompat.getDrawable(
                            getContext(),
                            i == position ? R.drawable.selected_dot : R.drawable.unselected_dot
                    ));
                }
            }
        });
        //---- sự kiện indicator khi kéo trượt ảnh


        return view;
    }
}