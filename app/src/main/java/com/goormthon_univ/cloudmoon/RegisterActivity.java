package com.goormthon_univ.cloudmoon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegisterActivity extends AppCompatActivity {

    //CloudMoon 서버 주소
    final String url_link="http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt=20210330";

    //Handler
    Handler handler=new Handler();

    TextInputEditText input_email;
    TextInputEditText input_pw;
    TextInputEditText input_pw2;

    Button activity_register_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        input_email=findViewById(R.id.activity_register_email);
        input_pw=findViewById(R.id.activity_register_pw);
        input_pw2=findViewById(R.id.activity_register_pw2);

        activity_register_next=findViewById(R.id.activity_register_next);

        Intent intent=new Intent(this,RegisterActivity2.class);
        activity_register_next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //버튼 클릭 시 HTTP 가동
                /*
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        request(url_link);
                    }
                }).start();*/
                startActivityForResult(intent,101);
            }
        });

        input_pw2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){}
                    //Toast.makeText(getApplicationContext(), "onFocusChange", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void loginactivity(View view){
        Intent intent=new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }

    public void check_req(View view){
        if(input_email.getText().length()>1 && input_pw.getText().length()>1 && input_pw2.getText().length()>1)
            activity_register_next.setEnabled(true);
            activity_register_next.setBackgroundColor(getResources().getColor(R.color.p_500));
    }

    public void request(String url_link){
        StringBuilder output=new StringBuilder();
        try{
            URL url=new URL(url_link);

            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            if(connection!=null){
                connection.setConnectTimeout(10000);
                connection.setRequestMethod("GET");
                connection.setDoInput(true);

                int responseCode=connection.getResponseCode();
                BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line=null;
                while(true){
                    line=reader.readLine();
                    if(line==null){
                        break;
                    }

                    output.append(line+"\n");
                }
                reader.close();
                connection.disconnect();
            }
        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"오류 발생 : "+e.toString(),Toast.LENGTH_LONG);
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                //출력 결과
            }
        });
    }
}