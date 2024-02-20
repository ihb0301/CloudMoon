package com.goormthon_univ.cloudmoon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity4 extends AppCompatActivity {
    //Preferences 관리를 위한 객체
    PreferencesManager manager;

    //드롭다운 메뉴 표시 여부
    Boolean isDropdownShow=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register4);

        manager=new PreferencesManager(getSharedPreferences("preferences", Activity.MODE_PRIVATE));

        Button activity_register4_next=findViewById(R.id.activity_register4_next);

        TextInputLayout activity_register4_lang_layout=findViewById(R.id.activity_register4_lang_layout);
        AutoCompleteTextView activity_register4_lang=findViewById(R.id.activity_register4_lang);

        //리사이클러뷰 어뎁터 연결
        RecyclerView lang_dropdown_recyclerview=findViewById(R.id.lang_dropdown_recyclerview);

        LangDropdownAdapter langadapter=new LangDropdownAdapter(getApplicationContext(),activity_register4_next,activity_register4_lang,0);
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

        activity_register4_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isDropdownShow){
                    lang_dropdown_recyclerview.setVisibility(View.GONE);
                    isDropdownShow=!isDropdownShow;
                    activity_register4_lang_layout.setEndIconDrawable(R.drawable.arrow_down);
                }else{
                    lang_dropdown_recyclerview.setVisibility(View.VISIBLE);
                    isDropdownShow=!isDropdownShow;
                    activity_register4_lang_layout.setEndIconDrawable(R.drawable.arrow_up);
                }
            }
        });

        Intent intent=new Intent(this,RegisterActivity5.class);
        activity_register4_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intent,101);
            }
        });
    }

    public void registeractivity3(View view){
        Intent intent=new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }
}