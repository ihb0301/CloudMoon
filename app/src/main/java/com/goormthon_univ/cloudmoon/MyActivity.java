package com.goormthon_univ.cloudmoon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyActivity extends AppCompatActivity {
    //Preferences 관리를 위한 객체
    PreferencesManager manager;

    String[] items={"한국어","영어","중국어","일본어"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        manager=new PreferencesManager(getSharedPreferences("preferences", Activity.MODE_PRIVATE));

        TextView my_nickname_text=findViewById(R.id.my_nickname_text);
        TextView my_lang_a_text=findViewById(R.id.my_lang_a_text);
        TextView my_lang_b_text=findViewById(R.id.my_lang_b_text);

        my_nickname_text.setText(manager.pref_read_string("nickname")+" 님");
        my_lang_a_text.setText(manager.pref_read_string("lang_a"));
        my_lang_a_text.setText(manager.pref_read_string("lang_b"));

        ImageView my_lang_b_level=findViewById(R.id.my_lang_b_level);

        if(manager.pref_read_string("lang_b_level").equals("1")){
            my_lang_b_level.setImageResource(R.drawable.lang_level_1);
        }else if(manager.pref_read_string("lang_b_level").equals("2")){
            my_lang_b_level.setImageResource(R.drawable.lang_level_2);
        }else if(manager.pref_read_string("lang_b_level").equals("3")){
            my_lang_b_level.setImageResource(R.drawable.lang_level_3);
        }else if(manager.pref_read_string("lang_b_level").equals("4")){
            my_lang_b_level.setImageResource(R.drawable.lang_level_4);
        }
    }

    public void mainactivity(View view){
        Intent intent=new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }
}