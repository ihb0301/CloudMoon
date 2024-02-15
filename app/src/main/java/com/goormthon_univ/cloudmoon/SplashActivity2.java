package com.goormthon_univ.cloudmoon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SplashActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);

        Button next_button=findViewById(R.id.splash_next_button);
        Intent intent=new Intent(this,LoginActivity.class);

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intent,101);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences pref=getSharedPreferences("preferences", Activity.MODE_PRIVATE);

        if(pref!=null&&pref.contains("login")){
            if(pref.getBoolean("login",true)==true){
                Intent intent=new Intent(this,HomeActivity.class);
                startActivityForResult(intent,101);
                finish();
            }
        }
    }
}