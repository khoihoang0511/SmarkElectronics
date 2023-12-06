package com.example.smarkelectronics.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.smarkelectronics.R;
import com.example.smarkelectronics.api.API;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EvaluateActivity extends AppCompatActivity {

    ImageView imgstarEvaluate1,imgstarEvaluate2,imgstarEvaluate3,imgstarEvaluate4,imgstarEvaluate5;
    int numberofstars;
    private ProgressDialog progressDialog;
    private Handler handlerAddress = new Handler();
    EditText edtContentEvaluate;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        TextView tvbackMyEvaluate = findViewById(R.id.tvbackMyEvaluate);
        ImageView imgMyEvaluate = findViewById(R.id.imgMyEvaluate);
        TextView txtNameEvaluate = findViewById(R.id.txtNameEvaluate);
        imgstarEvaluate1 = findViewById(R.id.imgstarEvaluate1);
        imgstarEvaluate2 = findViewById(R.id.imgstarEvaluate2);
        imgstarEvaluate3 = findViewById(R.id.imgstarEvaluate3);
        imgstarEvaluate4 = findViewById(R.id.imgstarEvaluate4);
        imgstarEvaluate5 = findViewById(R.id.imgstarEvaluate5);
        edtContentEvaluate = findViewById(R.id.edtContentEvaluate);
        TextView txtAddMyEvalute = findViewById(R.id.txtAddMyEvalute);

        numberofstars = 0;

        Intent getintent = getIntent();
        String image = getintent.getStringExtra("Image");
        String name = getintent.getStringExtra("Name");
        id = getintent.getIntExtra("id",0);
        try {
            Glide.with(EvaluateActivity.this).load(image).into(imgMyEvaluate);
            txtNameEvaluate.setText(name+"");
        }catch (Exception e){

        }
        tvbackMyEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EvaluateActivity.this,OderActivity.class));
            }
        });
        txtAddMyEvalute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReview();
            }
        });
        imgstarEvaluate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColorGrey();
                imgstarEvaluate1.setImageResource(R.drawable.baseline_star_yellow_24);
                numberofstars = 1;
            }
        });
        imgstarEvaluate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColorGrey();
                imgstarEvaluate2.setImageResource(R.drawable.baseline_star_yellow_24);
                imgstarEvaluate1.setImageResource(R.drawable.baseline_star_yellow_24);
                numberofstars = 2;
            }
        });
        imgstarEvaluate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColorGrey();
                imgstarEvaluate3.setImageResource(R.drawable.baseline_star_yellow_24);
                imgstarEvaluate2.setImageResource(R.drawable.baseline_star_yellow_24);
                imgstarEvaluate1.setImageResource(R.drawable.baseline_star_yellow_24);
                numberofstars = 3;
            }
        });
        imgstarEvaluate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColorGrey();
                imgstarEvaluate4.setImageResource(R.drawable.baseline_star_yellow_24);
                imgstarEvaluate3.setImageResource(R.drawable.baseline_star_yellow_24);
                imgstarEvaluate2.setImageResource(R.drawable.baseline_star_yellow_24);
                imgstarEvaluate1.setImageResource(R.drawable.baseline_star_yellow_24);
                numberofstars = 4;
            }
        });
        imgstarEvaluate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColorGrey();
                imgstarEvaluate5.setImageResource(R.drawable.baseline_star_yellow_24);
                imgstarEvaluate4.setImageResource(R.drawable.baseline_star_yellow_24);
                imgstarEvaluate3.setImageResource(R.drawable.baseline_star_yellow_24);
                imgstarEvaluate2.setImageResource(R.drawable.baseline_star_yellow_24);
                imgstarEvaluate1.setImageResource(R.drawable.baseline_star_yellow_24);
                numberofstars = 5;
            }
        });
    }
    private void setColorGrey(){
        imgstarEvaluate1.setImageResource(R.drawable.baseline_star_grey_24);
        imgstarEvaluate2.setImageResource(R.drawable.baseline_star_grey_24);
        imgstarEvaluate3.setImageResource(R.drawable.baseline_star_grey_24);
        imgstarEvaluate4.setImageResource(R.drawable.baseline_star_grey_24);
        imgstarEvaluate5.setImageResource(R.drawable.baseline_star_grey_24);
    }
    private void addReview(){
        SharedPreferences saveAcc = getSharedPreferences("SaveAcc",MODE_PRIVATE);
        String email = saveAcc.getString("SaveEmail","");
        String password = saveAcc.getString("SavePass","");
        String content = edtContentEvaluate.getText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                handlerAddress.post(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog = new ProgressDialog(EvaluateActivity.this);
                        progressDialog.setMessage("Loanding");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                    }
                });
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://khoihoang0511.000webhostapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                API api = retrofit.create(API.class);
                Call<String> callDress = api.addReview(numberofstars,content,id,email,password);
                callDress.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful() && response.body() != null){
                            progressDialog.dismiss();
                            startActivity(new Intent(EvaluateActivity.this,MyReviewActivity.class));
                            Toast.makeText(EvaluateActivity.this, "Đánh giá thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            progressDialog.dismiss();
                            Log.e("=------->",response.body()+"");
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(EvaluateActivity.this, "Kết nối không ổn định", Toast.LENGTH_SHORT).show();
                        Log.e("=------->",t+"");
                    }
                });
            }
        }).start();
    }
}