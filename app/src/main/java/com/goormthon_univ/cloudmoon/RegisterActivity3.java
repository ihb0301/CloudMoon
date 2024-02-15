package com.goormthon_univ.cloudmoon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.google.android.material.datepicker.MaterialStyledDatePickerDialog;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);

        Button activity_register_next=findViewById(R.id.activity_register3_next);

        final Calendar calendar= Calendar.getInstance();

        TextInputEditText activity_register3_date=findViewById(R.id.activity_register3_date);

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                String myFormat="yyyy년/MM월/dd일";
                SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.KOREA);
                activity_register3_date.setText(dateFormat.format(calendar.getTime()));
                activity_register_next.setEnabled(true);
                activity_register_next.setBackgroundColor(getResources().getColor(R.color.p_500));
            }
        };

        activity_register3_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RegisterActivity3.this,date,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Intent intent=new Intent(this,RegisterActivity4.class);
        activity_register_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intent,101);
            }
        });
    }

    public void registeractivity2(View view){
        Intent intent=new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }
}