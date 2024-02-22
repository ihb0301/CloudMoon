package com.goormthon_univ.cloudmoon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.goormthon_univ.cloudmoon.NotificationAdapter.NotificationAdapter;
import com.goormthon_univ.cloudmoon.NotificationAdapter.NotificationClass;
import com.goormthon_univ.cloudmoon.PublicAdapter.Public;
import com.goormthon_univ.cloudmoon.PublicAdapter.PublicAdapter;

public class AlarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        //알림 어뎁터 연결
        RecyclerView notification_recycler=findViewById(R.id.notification_recycler);

        NotificationAdapter notificationadapter=new NotificationAdapter(AlarmActivity.this);

        LinearLayoutManager layoutManager_notification=new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false);
        notification_recycler.setLayoutManager(layoutManager_notification);
        notification_recycler.setAdapter(notificationadapter);

        NotificationClass n_1=new NotificationClass("Moon 님이 새 일기를 작성했어요!","1시간 전");
        NotificationClass n_2=new NotificationClass("Moon 님이 댓글을 달았어요!","2024. 2. 23");
        NotificationClass n_3=new NotificationClass("Moon 님에게 일기 교환 신청이 도착했어요!","2024. 2. 6");

        notificationadapter.addItem(n_1);
        notificationadapter.addItem(n_2);
        notificationadapter.addItem(n_3);
        notificationadapter.notifyDataSetChanged();
    }

    public void mainactivity(View view){
        Intent intent=new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }
}