package com.goormthon_univ.cloudmoon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //상단바 색상 변경
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.p_500));
        }
    }

    public void myactivity(View view){
        Intent intent_myactivity=new Intent(getApplicationContext(), MyActivity.class);
        startActivityForResult(intent_myactivity,101);
    }

    public void home2activity(View view){
        Intent intent_home2activity=new Intent(getApplicationContext(), Home2Activity.class);
        startActivityForResult(intent_home2activity,101);
    }

    public void alarmactivity(View view){
        Intent intent_alarmactivity=new Intent(getApplicationContext(), AlarmActivity.class);
        startActivityForResult(intent_alarmactivity,101);
    }
}