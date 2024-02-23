package com.goormthon_univ.cloudmoon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.goormthon_univ.cloudmoon.Server.ServerManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Home2Activity extends AppCompatActivity {
    //Preferences 관리를 위한 객체
    PreferencesManager manager;

    //서버 관리를 위한 객체
    ServerManager server_manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        server_manager=new ServerManager(getApplicationContext());

        manager=new PreferencesManager(getSharedPreferences("preferences", Activity.MODE_PRIVATE));

        Date now=new Date();
        SimpleDateFormat simpledataformat=new SimpleDateFormat("MM월 dd일");

        TextView today_date=findViewById(R.id.home2_today);
        today_date.setText(simpledataformat.format(now));

    }

    public void mainactivity(View view){
        Intent intent=new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }

    public void write(View view){
        JSONObject login_json = new JSONObject();

        TextInputEditText text_input=findViewById(R.id.home2_text);

        try {
            login_json.put("accountsId",manager.pref_read_string("accountsId"));
            login_json.put("content",text_input.getText());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        String msg=server_manager.http_request_post_json("http://13.125.254.39:8080/diary/create",login_json);


        Intent intent=new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }
}