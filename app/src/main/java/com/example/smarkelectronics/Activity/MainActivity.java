package com.example.smarkelectronics.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.smarkelectronics.Fragment.HomeFragment;
import com.example.smarkelectronics.Fragment.fragment_favorite;
import com.example.smarkelectronics.Fragment.fragment_notification_new;
import com.example.smarkelectronics.Fragment.fragment_profile_new;
import com.example.smarkelectronics.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottmnavigationview);

        changfragment(new HomeFragment());
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.ichome){
                    changfragment(new HomeFragment());
                }else if(item.getItemId() == R.id.icfavorite){
                    changfragment(new fragment_favorite());
                }else if (item.getItemId() == R.id.icnotification){
                    changfragment(new fragment_notification_new());
                }else if (item.getItemId() == R.id.icprofile) {
                    changfragment(new fragment_profile_new());
                }
                return true;
            }
        });




    }
    public void changfragment(Fragment fragment){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentmain,fragment);
        fragmentTransaction.commit();
    }
}