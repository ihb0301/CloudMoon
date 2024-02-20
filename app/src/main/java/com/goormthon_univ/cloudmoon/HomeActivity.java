package com.goormthon_univ.cloudmoon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.goormthon_univ.cloudmoon.calendar_decorator.AllDecorator;
import com.goormthon_univ.cloudmoon.calendar_decorator.SelectDecorator;
import com.goormthon_univ.cloudmoon.calendar_decorator.TodayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {
    private ArrayList<CalendarDay> calendarDayList = new ArrayList<>();
    private MaterialCalendarView home_calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //상단바 색상 변경
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.p_500));
        }

        calendarDayList.add(CalendarDay.from(2024,02,11));
        calendarDayList.add(CalendarDay.from(2024,02,20));
        AllDecorator decorator = new AllDecorator(calendarDayList,this);
        TodayDecorator today_decorator = new TodayDecorator(this);
        SelectDecorator select_decorator = new SelectDecorator(this);

        home_calendarView= findViewById(R.id.home_calendarView);

        //캘린더뷰 해당 날짜 이미지 추가
        home_calendarView.addDecorator(today_decorator);
        home_calendarView.addDecorator(select_decorator);
        home_calendarView.addDecorator(decorator);
        home_calendarView.state().edit()
                .setFirstDayOfWeek(DayOfWeek.of(Calendar.SUNDAY))
                .commit();

        //MM월 YYYY 방식-> YYYY년 MM월 으로 표현 수정
        home_calendarView.setTitleFormatter(new TitleFormatter() {
            @Override
            public CharSequence format(CalendarDay day) {
                //LocalDate date=day.getDate();
                String[] date_arr=day.toString().split("-");
                String date_str=date_arr[0].replace("CalendarDay{","")+"년 "+date_arr[1]+"월";
                return date_str;
            }
        });

        Intent intent_home3activity=new Intent(getApplicationContext(), HomeActivity3.class);
        home_calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                String[] date_arr=date.toString().split("-");
                intent_home3activity.putExtra("date",date_arr[1]+"월 "+date_arr[2].replace("}","")+"일");
                startActivityForResult(intent_home3activity,101);
            }
        });
    }

    public void myactivity(View view){
        Intent intent_myactivity=new Intent(getApplicationContext(), MyActivity.class);
        startActivityForResult(intent_myactivity,101);
    }

    public void home2activity(View view){
        Intent intent_home2activity=new Intent(getApplicationContext(), Home2Activity.class);
        startActivityForResult(intent_home2activity,101);
    }

    public void alarmactivity(View view){
        Intent intent_alarmactivity=new Intent(getApplicationContext(), AlarmActivity.class);
        startActivityForResult(intent_alarmactivity,101);
    }
}