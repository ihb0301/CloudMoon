package com.goormthon_univ.cloudmoon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.goormthon_univ.cloudmoon.Server.ServerManager;

import org.json.JSONException;
import org.json.JSONObject;

public class MyActivity2 extends AppCompatActivity {
    //Preferences 관리를 위한 객체
    PreferencesManager manager;

    //서버 관리를 위한 객체
    ServerManager server_manager;

    //드롭다운 메뉴 표시 여부
    Boolean isDropdownShow_langa=false;
    Boolean isDropdownShow_langb=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my2);

        server_manager=new ServerManager(getApplicationContext());

        manager=new PreferencesManager(getSharedPreferences("preferences", Activity.MODE_PRIVATE));

        Button activity_my2_button=findViewById(R.id.activity_my2_button);
        activity_my2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myactivity(v);
            }
        });

        EditText activity_my2_nickname=findViewById(R.id.activity_my2_nickname);
        activity_my2_nickname.setText(manager.pref_read_string("nickname"));

        TextInputLayout activity_register4_lang_layout=findViewById(R.id.activity_my2_lang_a_layout);
        AutoCompleteTextView activity_my2_lang_a=findViewById(R.id.activity_my2_lang_a);
        activity_my2_lang_a.setText(manager.pref_read_string("lang_a"));

        //모국어 리사이클러뷰 어뎁터 연결
        RecyclerView lang_dropdown_recyclerview=findViewById(R.id.activity_my2_lang_a_dropdown_recyclerview);

        LangDropdownAdapter langadapter=new LangDropdownAdapter(getApplicationContext(),activity_my2_button,activity_my2_lang_a,0);
        langadapter.manager=this.manager;

        LinearLayoutManager layoutManager=new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false);
        lang_dropdown_recyclerview.setLayoutManager(layoutManager);
        lang_dropdown_recyclerview.setAdapter(langadapter);

        Lang l_1=new Lang();
        l_1.lang="한국어";
        l_1.lang_des="한국어";

        Lang l_2=new Lang();
        l_2.lang="영어";
        l_2.lang_des="English";

        Lang l_3=new Lang();
        l_3.lang="중국어";
        l_3.lang_des="中文";

        Lang l_4=new Lang();
        l_4.lang="일본어";
        l_4.lang_des="日本語";

        langadapter.addItem(l_1);
        langadapter.addItem(l_2);
        langadapter.addItem(l_3);
        langadapter.addItem(l_4);
        langadapter.notifyDataSetChanged();

        activity_my2_lang_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isDropdownShow_langa){
                    lang_dropdown_recyclerview.setVisibility(View.GONE);
                    isDropdownShow_langa=!isDropdownShow_langa;
                    activity_register4_lang_layout.setEndIconDrawable(R.drawable.arrow_down);
                }else{
                    lang_dropdown_recyclerview.setVisibility(View.VISIBLE);
                    isDropdownShow_langa=!isDropdownShow_langa;
                    activity_register4_lang_layout.setEndIconDrawable(R.drawable.arrow_up);
                }
            }
        });

        TextInputLayout activity_register5_lang_layout=findViewById(R.id.activity_my2_lang_b_layout);
        AutoCompleteTextView activity_my2_lang_b=findViewById(R.id.activity_my2_lang_b);
        activity_my2_lang_b.setText(manager.pref_read_string("lang_b"));

        //학습 언어 리사이클러뷰 언어 선택 어뎁터 연결
        RecyclerView lang_b_dropdown_recyclerview=findViewById(R.id.activity_my2_lang_b_dropdown_recyclerview);

        LangDropdownAdapter lang_b_adapter=new LangDropdownAdapter(getApplicationContext(),activity_my2_button,activity_my2_lang_b,1);
        lang_b_adapter.manager=this.manager;

        LinearLayoutManager layoutManager2=new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false);
        lang_b_dropdown_recyclerview.setLayoutManager(layoutManager2);
        lang_b_dropdown_recyclerview.setAdapter(lang_b_adapter);

        Lang lang_1=new Lang();
        lang_1.lang="한국어";
        lang_1.lang_des="한국어";

        Lang lang_2=new Lang();
        lang_2.lang="영어";
        lang_2.lang_des="English";

        Lang lang_3=new Lang();
        lang_3.lang="중국어";
        lang_3.lang_des="中文";

        Lang lang_4=new Lang();
        lang_4.lang="일본어";
        lang_4.lang_des="日本語";

        lang_b_adapter.addItem(lang_1);
        lang_b_adapter.addItem(lang_2);
        lang_b_adapter.addItem(lang_3);
        lang_b_adapter.addItem(lang_4);
        lang_b_adapter.notifyDataSetChanged();

        //레벨 어뎁터 연결
        RecyclerView lang_level_recyclerview=findViewById(R.id.lang_level_recyclerview);

        LangAdapter langadapter3=new LangAdapter(Integer.valueOf(manager.pref_read_string("lang_b_level"))-1);
        langadapter3.manager=this.manager;

        LinearLayoutManager layoutManager3=new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false);
        lang_level_recyclerview.setLayoutManager(layoutManager3);
        lang_level_recyclerview.setAdapter(langadapter3);

        Lang lang_pf_1=new Lang();
        lang_pf_1.lang="입문자";
        lang_pf_1.lang_des="가벼운 인사를 나눌 수 있어요.";
        lang_pf_1.level=1;

        Lang lang_pf_2=new Lang();
        lang_pf_2.lang="초급자";
        lang_pf_2.lang_des="간단한 일상 대화를 나눌 수 있어요.";
        lang_pf_2.level=2;

        Lang lang_pf_3=new Lang();
        lang_pf_3.lang="중급자";
        lang_pf_3.lang_des="다양한 주제로 대화할 수 있어요.";
        lang_pf_3.level=3;

        Lang lang_pf_4=new Lang();
        lang_pf_4.lang="실력자";
        lang_pf_4.lang_des="모국어와 비슷한 수준이에요.";
        lang_pf_4.level=4;

        langadapter3.addItem(lang_pf_1);
        langadapter3.addItem(lang_pf_2);
        langadapter3.addItem(lang_pf_3);
        langadapter3.addItem(lang_pf_4);
        langadapter3.notifyDataSetChanged();

        activity_my2_lang_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isDropdownShow_langb){
                    lang_b_dropdown_recyclerview.setVisibility(View.GONE);
                    isDropdownShow_langb=!isDropdownShow_langb;
                    activity_register5_lang_layout.setEndIconDrawable(R.drawable.arrow_down);
                }else{
                    lang_b_dropdown_recyclerview.setVisibility(View.VISIBLE);
                    isDropdownShow_langb=!isDropdownShow_langb;
                    activity_register5_lang_layout.setEndIconDrawable(R.drawable.arrow_up);
                }
            }
        });
    }

    public void myactivity(View view){
        //변경한 정보 서버로 올리기
        JSONObject login_json = new JSONObject();
        try {
            login_json.put("email",manager.pref_read_string("email"));
            login_json.put("nickname",manager.pref_read_string("nickname"));
            login_json.put("password",manager.pref_read_string("pw"));
            login_json.put("myLanguage",manager.pref_read_string("lang_a"));
            login_json.put("learningLanguage",manager.pref_read_string("lang_b"));
            login_json.put("level",manager.pref_read_string("lang_b_level"));
            login_json.put("flagOpen","true");
            login_json.put("flagAutoLogin","true");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        String msg=server_manager.http_request_put_json("http://13.125.254.39:8080/accounts/"+manager.pref_read_string("accountsId"),login_json);

        Intent intent=new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }
}