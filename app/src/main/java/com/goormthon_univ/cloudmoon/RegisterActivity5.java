package com.goormthon_univ.cloudmoon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity5 extends AppCompatActivity {
    //Preferences 관리를 위한 객체
    PreferencesManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register5);

        manager=new PreferencesManager(getSharedPreferences("preferences", Activity.MODE_PRIVATE));

        RecyclerView lang_level_recyclerview=findViewById(R.id.lang_level_recyclerview);



        LangAdapter langadapter=new LangAdapter();
        langadapter.manager=this.manager;

        LinearLayoutManager layoutManager=new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false);
        lang_level_recyclerview.setLayoutManager(layoutManager);
        lang_level_recyclerview.setAdapter(langadapter);

        Lang l_1=new Lang();
        l_1.lang="입문자";
        l_1.lang_des="가벼운 인사를 나눌 수 있어요.";
        l_1.level=1;

        Lang l_2=new Lang();
        l_2.lang="초급자";
        l_2.lang_des="간단한 일상 대화를 나눌 수 있어요.";
        l_2.level=2;

        Lang l_3=new Lang();
        l_3.lang="중급자";
        l_3.lang_des="다양한 주제로 대화할 수 있어요.";
        l_3.level=3;

        Lang l_4=new Lang();
        l_4.lang="실력자";
        l_4.lang_des="모국어와 비슷한 수준이에요.";
        l_4.level=4;

        langadapter.addItem(l_1);
        langadapter.addItem(l_2);
        langadapter.addItem(l_3);
        langadapter.addItem(l_4);
        langadapter.notifyDataSetChanged();

        Button activity_register5_next=findViewById(R.id.activity_register5_next);

        TextInputLayout activity_register5_lang_layout=findViewById(R.id.activity_register5_lang_layout);
        AutoCompleteTextView activity_register5_lang=findViewById(R.id.activity_register5_lang);

        String[] items={"한국어(한국어)","영어(English)","중국어()","일본어()"};
        ArrayAdapter<String> adapter=new ArrayAdapter<>(RegisterActivity5.this,R.layout.item_list,items);
        activity_register5_lang.setAdapter(adapter);

        activity_register5_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity_register5_next.setEnabled(true);
                activity_register5_next.setBackgroundColor(getResources().getColor(R.color.p_500));
            }
        });

        Intent intent=new Intent(this, HomeActivity.class);
        //회원가입 완료 후 모든 회원가입 액티비티들 종료
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity_register5_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.pref_write_boolean("login",true);
                startActivityForResult(intent,101);
                finish();
            }
        });
    }
}