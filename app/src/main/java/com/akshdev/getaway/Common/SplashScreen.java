package com.akshdev.getaway.Common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.akshdev.getaway.R;
import com.akshdev.getaway.User.UserDashboard;

public class SplashScreen extends AppCompatActivity {

    private static final int SPLASH_TIMER = 2000;
    AppCompatTextView title,madeBy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        //Full Screen
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Hooks
        title = findViewById(R.id.splash_title);
        madeBy = findViewById(R.id.splash_made_by);

        //Animation
        Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_splash_title);
        title.startAnimation(scaleAnimation);


        //setting splash screen. --> Parallel Thread --> Handler

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences onBoardingScreen = getSharedPreferences("onBoardingScreen", Context.MODE_PRIVATE);
                boolean isFirstTime = onBoardingScreen.getBoolean("firstTime", true);

                if(isFirstTime){
                    startActivity(new Intent(getApplicationContext(),UserDashboard.class));
                }
                else {
                    startActivity(new Intent(getApplicationContext(), UserDashboard.class));
                }
                finish();
            }
        }, SPLASH_TIMER);

    }
}