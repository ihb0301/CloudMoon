package com.goormthon_univ.cloudmoon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.goormthon_univ.cloudmoon.calendar_decorator.AllDecorator;
import com.goormthon_univ.cloudmoon.calendar_decorator.SelectDecorator;
import com.goormthon_univ.cloudmoon.calendar_decorator.TodayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;

import org.threeten.bp.DayOfWeek;

import java.util.ArrayList;
import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {
    private ArrayList<CalendarDay> calendarDayList = new ArrayList<>();
    private MaterialCalendarView home_calendarView;
    PreferencesManager manager;
    Dialog dialog_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Preferences 관리
        manager=new PreferencesManager(getSharedPreferences("preferences", Activity.MODE_PRIVATE));

        //홈화면 대시 보드 글자 선택
        //unfriend 대시 보드
        TextView home_layout_unfriend_caption=findViewById(R.id.home_layout_unfriend_caption);
        home_layout_unfriend_caption.setText(manager.pref_read_string("nickname")+"님 안녕하세요 :)");

        //friend 대시 보드
        TextView home_friend_me_nickname=findViewById(R.id.home_friend_me_nickname);
        home_friend_me_nickname.setText(manager.pref_read_string("nickname")+" 님");
        TextView home_friend_partner_nickname=findViewById(R.id.home_friend_partner_nickname);
        TextView home_friend_me_lang_a=findViewById(R.id.home_friend_me_lang_a);
        ImageView home_friend_me_lang_a_image=findViewById(R.id.home_friend_me_lang_a_image);
        TextView home_friend_me_lang_b=findViewById(R.id.home_friend_me_lang_b);
        ImageView home_friend_me_lang_b_image=findViewById(R.id.home_friend_me_lang_b_image);

        //한국어 언어 표기명을 영문 표기명으로 교체하기 위한 작업
        String lang_arr[]={"한국어","영어","중국어","일본어"};
        String lang_arr_replace[]={"KR","EN","CN","JP"};
        String lang_a_rp="",lang_b_rp="";
        for(int i=0;i<4;i++){
            if(manager.pref_read_string("lang_a").equals(lang_arr[i]))
                lang_a_rp=lang_arr_replace[i];
            if(manager.pref_read_string("lang_b").equals(lang_arr[i]))
                lang_b_rp=lang_arr_replace[i];
        }
        home_friend_me_lang_a.setText(lang_a_rp);
        home_friend_me_lang_b.setText(lang_b_rp);

        switch(manager.pref_read_string("lang_b_level")){
            case "1":
                home_friend_me_lang_b_image.setImageResource(R.drawable.lang_level_1);
                break;
            case "2":
                home_friend_me_lang_b_image.setImageResource(R.drawable.lang_level_2);
                break;
            case "3":
                home_friend_me_lang_b_image.setImageResource(R.drawable.lang_level_3);
                break;
            case "4":
                home_friend_me_lang_b_image.setImageResource(R.drawable.lang_level_4);
                break;
        }

        //교환 상대 존재 여부에 따라 홈화면 대시보드 선택
        ConstraintLayout home_layout_friend=findViewById(R.id.home_layout_friend);
        ConstraintLayout home_layout_unfriend=findViewById(R.id.home_layout_unfriend);

        if(manager.pref_read_string("friend")!=null){
            if(manager.pref_read_string("friend").equals("")){
                //교환 일기 상대가 없는 경우
                home_layout_friend.setVisibility(View.GONE);
                home_layout_unfriend.setVisibility(View.VISIBLE);
            }else{
                //교환 일기 상대가 있는 경우
                home_layout_friend.setVisibility(View.VISIBLE);
                home_layout_unfriend.setVisibility(View.GONE);
            }
        }{
            //교환 일기 상대가 없는 경우
            home_layout_friend.setVisibility(View.GONE);
            home_layout_unfriend.setVisibility(View.VISIBLE);
        }

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

        Button home_find_friend=findViewById(R.id.home_find_friend);
        home_find_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publicctivity(v);
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

    public void publicctivity(View view){
        Intent intent_publicactivity=new Intent(getApplicationContext(), PublicActivity.class);
        startActivityForResult(intent_publicactivity,101);
    }

    public void dialog_home_onclick(View view){
        //home 다이얼로그 생성
        Dialog dialog_home=new Dialog(HomeActivity.this);
        dialog_home.setContentView(R.layout.dialog_home);
        dialog_home.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_home.getWindow().setGravity(Gravity.BOTTOM);
        dialog_home.show();

        dialog_home.findViewById(R.id.dialog_home_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_home.dismiss();
            }
        });
        dialog_home.findViewById(R.id.dialog_home_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_home.dismiss();
                dialog_unfriend_function();
            }
        });
    }

    public void dialog_unfriend_function(){
        //unfriend 다이얼로그 생성
        Dialog dialog_unfriend=new Dialog(HomeActivity.this);
        dialog_unfriend.setContentView(R.layout.dialog_unfriend);
        dialog_unfriend.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_unfriend.show();

        dialog_unfriend.findViewById(R.id.dialog_accpet_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_unfriend.dismiss();
            }
        });
        dialog_unfriend.findViewById(R.id.dialog_accept_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //취소하기를 눌렀을 때의 동작
                dialog_unfriend.dismiss();
            }
        });
        dialog_unfriend.findViewById(R.id.dialog_accept_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //중단하기를 눌렀을 때의 동작
            }
        });
    }
}