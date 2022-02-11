package com.example.djapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.djapp.R;

public class HomeActivity extends AppCompatActivity {

    private  static int SPLASH_TIME_OUT = 5000; //4 sec

    ImageView logo,splashImg;
    TextView title;
    LottieAnimationView lottieAnimationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); //splash

        setContentView(R.layout.activity_home);

        logo=findViewById(R.id.logoSplash);
        splashImg=findViewById(R.id.coverSplash);
        lottieAnimationView=findViewById(R.id.lottieSplash);
        title=findViewById(R.id.titleSplash);


        //splashImg.animate().translationY(-4800).setDuration(1000).setStartDelay(4000);
        logo.animate().translationY(4400).setDuration(1000).setStartDelay(4000);
        title.animate().translationY(4400).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(4400).setDuration(1000).setStartDelay(4000);



        //from here down its for splash screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);

    }
}