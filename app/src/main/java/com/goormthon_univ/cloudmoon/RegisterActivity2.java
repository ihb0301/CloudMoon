package com.goormthon_univ.cloudmoon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        TextView text_enable_nickname=findViewById(R.id.text_enable_nickname);
        text_enable_nickname.setVisibility(View.GONE);

        Button activity_register2_check_button=findViewById(R.id.activity_register2_check_button);
        Button activity_register_next=findViewById(R.id.activity_register2_next);

        TextInputEditText activity_register2_nickname=findViewById(R.id.activity_register2_nickname);
        activity_register2_check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activity_register2_nickname.getText().length()>2){
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