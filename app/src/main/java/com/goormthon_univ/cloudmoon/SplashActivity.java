package com.goormthon_univ.cloudmoon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //스플래시 화면 보여주기
        Handler h=new Handler();
        Intent intent=new Intent(this,SplashActivity2.class);
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivityForResult(intent,101);
                finish();
            }
        },1000);
    }
}