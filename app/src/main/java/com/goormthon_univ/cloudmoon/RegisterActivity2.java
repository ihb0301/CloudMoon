package com.goormthon_univ.cloudmoon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.goormthon_univ.cloudmoon.Server.ServerManager;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity2 extends AppCompatActivity {
    //Preferences 관리를 위한 객체
    PreferencesManager manager;

    //서버 관리를 위한 객체
    ServerManager server_manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        server_manager=new ServerManager(getApplicationContext());

        manager=new PreferencesManager(getSharedPreferences("preferences", Activity.MODE_PRIVATE));

        TextView text_enable_nickname=findViewById(R.id.text_enable_nickname);
        text_enable_nickname.setVisibility(View.GONE);

        Button activity_register2_check_button=findViewById(R.id.activity_register2_check_button);
        Button activity_register_next=findViewById(R.id.activity_register2_next);

        TextInputEditText activity_register2_nickname=findViewById(R.id.activity_login_email);
        activity_register2_check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg=server_manager.http_request_get_json("http://13.125.254.39:8080/accounts/check-nickname/"+activity_register2_nickname.getText());
                if(msg.equals("중복된 닉네임입니다.")){
                    Toast.makeText(getApplicationContext(),"중복된 닉네임입니다.",Toast.LENGTH_SHORT).show();
                    text_enable_nickname.setVisibility(View.INVISIBLE);
                    activity_register_next.setEnabled(false);
                    activity_register_next.setBackgroundColor(getResources().getColor(R.color.n_300));
                }else{
                    text_enable_nickname.setVisibility(View.VISIBLE);
                    activity_register_next.setEnabled(true);
                    activity_register_next.setBackgroundColor(getResources().getColor(R.color.p_500));
                }
            }
        });

        Intent intent=new Intent(this,RegisterActivity3.class);
        activity_register_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.pref_write_string("nickname",activity_register2_nickname.getText().toString());
                startActivityForResult(intent,101);
            }
        });
    }

    public void registeractivity(View view){
        Intent intent=new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }
}