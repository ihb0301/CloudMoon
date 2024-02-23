package com.goormthon_univ.cloudmoon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.goormthon_univ.cloudmoon.Server.ServerManager;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    //Preferences 관리를 위한 객체
    PreferencesManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login_button=findViewById(R.id.login_login_button);
        TextView login_register=findViewById(R.id.login_resigter);
        Intent intent=new Intent(this,HomeActivity.class);
        Intent intent_register=new Intent(this,RegisterActivity.class);

        //SharedPreferences 설정
        manager=new PreferencesManager(getSharedPreferences("preferences", Activity.MODE_PRIVATE));
        SharedPreferences pref=getSharedPreferences("preferences", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();

        //ServerManager
        ServerManager server_manager=new ServerManager(getApplicationContext());

        TextInputEditText activity_login_email=findViewById(R.id.activity_login_email);
        TextInputEditText activity_login_password=findViewById(R.id.activity_login_password);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject login_json = new JSONObject();
                String msg;
                JSONObject return_json;
                try {
                    login_json.put("email", activity_login_email.getText());
                    login_json.put("password", activity_login_password.getText());

                    msg=server_manager.http_request_post_json("http://13.125.254.39:8080/accounts/login",login_json);
                    return_json=new JSONObject(msg);
                    if(return_json.isNull("message")){
                        //로그인 성공 시 저장
                        editor.putBoolean("login",true);
                        editor.commit();
                        manager.pref_write_string("token",return_json.get("token").toString());
                        manager.pref_write_string("accountsId",return_json.get("accountsId").toString());

                        //토근 정보로부터 불러오기
                        String account_info=server_manager.http_request_get_json("http://13.125.254.39:8080/accounts/"+return_json.get("accountsId").toString());
                        JSONObject return_account_json=new JSONObject(account_info);
                        manager.pref_write_string("email",return_account_json.get("email").toString());
                        manager.pref_write_string("pw",return_account_json.get("password").toString());
                        manager.pref_write_string("nickname",return_account_json.get("nickname").toString());
                        manager.pref_write_string("lang_a",return_account_json.get("myLanguage").toString());
                        manager.pref_write_string("lang_b",return_account_json.get("learningLanguage").toString());
                        manager.pref_write_string("lang_b_level",return_account_json.get("level").toString());

                        startActivityForResult(intent,101);
                        finish();
                    }else{
                        Log.d("LoginActivity",return_json.get("message").toString());
                        if(return_json.get("message").toString().equals("ACCOUNTS NOT EXIST")){
                            Toast.makeText(getApplicationContext(),"존재하지 않는 계정입니다.",Toast.LENGTH_SHORT).show();
                        }else if(return_json.get("message").toString().equals("ACCOUNTS PASSWORD INCORRECT")){
                            Toast.makeText(getApplicationContext(),"비밀번호가 틀렸습니다.",Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
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