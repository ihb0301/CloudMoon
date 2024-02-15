package com.goormthon_univ.cloudmoon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login_button=findViewById(R.id.login_login_button);
        TextView login_register=findViewById(R.id.login_resigter);
        Intent intent=new Intent(this,HomeActivity.class);
        Intent intent_register=new Intent(this,RegisterActivity.class);

        //SharedPreferences 설정
        SharedPreferences pref=getSharedPreferences("preferences", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //로그인 성공 시 저장
                editor.putBoolean("login",true);
                editor.commit();

                startActivityForResult(intent,101);
                finish();
            }
        });

        login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intent_register,101);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
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