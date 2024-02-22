package com.goormthon_univ.cloudmoon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.goormthon_univ.cloudmoon.PublicAdapter.Public;
import com.goormthon_univ.cloudmoon.PublicAdapter.PublicAdapter;
import com.goormthon_univ.cloudmoon.Server.ServerManager;

import java.util.HashMap;
import java.util.Map;

public class PublicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public);

        TextView public_text=findViewById(R.id.public_text);
        Button public_button=findViewById(R.id.public_button);

        PreferencesManager manager=new PreferencesManager(getSharedPreferences("preferences", Activity.MODE_PRIVATE));

        ServerManager server_manager=new ServerManager(getApplicationContext());

        Map<String,String> parms=new HashMap<String,String>();
                parms.put("email",manager.pref_read_string("email"));
                parms.put("nickname",manager.pref_read_string("nickname"));
                parms.put("password",manager.pref_read_string("pw"));
                parms.put("myLanguage",manager.pref_read_string("lang_a"));
                parms.put("learningLanguage",manager.pref_read_string("lang_b"));
                parms.put("level",manager.pref_read_string("lang_b_level"));
                parms.put("flagOpen","true");
                parms.put("flagAutoLogin","true");

        server_manager.string_request_post("/accounts/register",parms);

        public_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //회원가입
                //server_manager.string_request_post("https://virtserver.swaggerhub.com/eoslovy/cloudmoon/1.0.0/accounts/register",parms);
                //로그인
                Map<String,String> parms_login=new HashMap<String,String>();
                parms_login.put("email","email");
                parms_login.put("password","pass");
                server_manager.string_request_post("/accounts/login",parms_login);
            }
        });


        //글 어뎁터 연결
        RecyclerView public_recycler=findViewById(R.id.notification_recycler);

        PublicAdapter publicadapter=new PublicAdapter(PublicActivity.this);

        LinearLayoutManager layoutManager_public=new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false);
        public_recycler.setLayoutManager(layoutManager_public);
        public_recycler.setAdapter(publicadapter);

        Public p_1=new Public("galaxy","오늘은 기분이 좋지 않은 날이었다. 아침 일찍 일어났지만 피곤함이 가시지 않아 힘들었다. 하루 내내 뭔가 잘못된 기분이 들었고, 마음이 무거워졌다.","2월 2일");
        Public p_2=new Public("moon","오늘은 햇살 가득한 좋은 날이었다. 아침에 일어나 창 밖을 보니 아름다운 풍경이 펼쳐져 기분이 좋았다. 친구들과 함께 맛있는 점심을 먹으며 웃고 이야기를 나누었다. 하루 종일 웃으며 즐거운 시간을 보내고, 이제 피곤한 몸을 침대에 누워 휴식을 취하려고 한다. 내일도 좋은 하루가 되기를 기대한다.","1월 31일");

        publicadapter.addItem(p_1);
        publicadapter.addItem(p_2);
        publicadapter.notifyDataSetChanged();
    }

    public void mainactivity(View view){
        Intent intent=new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }
}