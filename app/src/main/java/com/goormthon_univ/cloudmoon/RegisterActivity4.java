package com.goormthon_univ.cloudmoon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register4);

        Button activity_register4_next=findViewById(R.id.activity_register4_next);

        TextInputLayout activity_register4_lang_layout=findViewById(R.id.activity_register4_lang_layout);
        AutoCompleteTextView activity_register4_lang=findViewById(R.id.activity_register4_lang);

        String[] items={"한국어(한국어)","영어(English)","중국어()","일본어()"};
        ArrayAdapter<String> adapter=new ArrayAdapter<>(RegisterActivity4.this,R.layout.item_list,items);
        activity_register4_lang.setAdapter(adapter);

        activity_register4_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"test",Toast.LENGTH_LONG);
                TextView text=findViewById(R.id.textView25);

                activity_register4_next.setEnabled(true);
                activity_register4_next.setBackgroundColor(getResources().getColor(R.color.p_500));
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