package com.goormthon_univ.cloudmoon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.goormthon_univ.cloudmoon.PublicAdapter.Public;
import com.goormthon_univ.cloudmoon.PublicAdapter.PublicAdapter;
import com.goormthon_univ.cloudmoon.Server.ServerManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class PublicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public);

        PreferencesManager manager=new PreferencesManager(getSharedPreferences("preferences", Activity.MODE_PRIVATE));

        ServerManager server_manager=new ServerManager(getApplicationContext());

        //글 어뎁터 연결
        RecyclerView public_recycler=findViewById(R.id.notification_recycler);

        PublicAdapter publicadapter=new PublicAdapter(PublicActivity.this);

        LinearLayoutManager layoutManager_public=new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false);
        public_recycler.setLayoutManager(layoutManager_public);
        public_recycler.setAdapter(publicadapter);

        //글 불러오기
        TextInputEditText text_input=findViewById(R.id.home2_text);

        String msg=server_manager.http_request_get_json("http://13.125.254.39:8080/language/"+manager.pref_read_string("lang_a"));
        try {
            JSONArray public_json_arr = new JSONArray(msg);
            for(int i=0;i<public_json_arr.length();i++){
                JSONObject public_json=public_json_arr.getJSONObject(i);

                //작성자 accountsId 불러오기
                String writer_id=public_json.get("accountsId").toString();

                JSONObject nickname=new JSONObject(server_manager.http_request_get_json("http://13.125.254.39:8080/accounts/"+writer_id));

                Public p=new Public(nickname.get("nickname").toString(),public_json.get("content").toString(),"2월 2일");
                publicadapter.addItem(p);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        //Public p_1=new Public("galaxy","오늘은 기분이 좋지 않은 날이었다. 아침 일찍 일어났지만 피곤함이 가시지 않아 힘들었다. 하루 내내 뭔가 잘못된 기분이 들었고, 마음이 무거워졌다.","2월 2일");
        //Public p_2=new Public("moon","오늘은 햇살 가득한 좋은 날이었다. 아침에 일어나 창 밖을 보니 아름다운 풍경이 펼쳐져 기분이 좋았다. 친구들과 함께 맛있는 점심을 먹으며 웃고 이야기를 나누었다. 하루 종일 웃으며 즐거운 시간을 보내고, 이제 피곤한 몸을 침대에 누워 휴식을 취하려고 한다. 내일도 좋은 하루가 되기를 기대한다.","1월 31일");

        //publicadapter.addItem(p_1);
        //publicadapter.addItem(p_2);
        publicadapter.notifyDataSetChanged();
    }

    public void mainactivity(View view){
        Intent intent=new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }
}