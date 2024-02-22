package com.goormthon_univ.cloudmoon.NotificationAdapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.goormthon_univ.cloudmoon.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder>{
    ArrayList<NotificationClass> items=new ArrayList<>();
    //context 사용을 위한 객체
    static Context context;

    public NotificationAdapter(Context context){
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType){
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View itemView=inflater.inflate(R.layout.recyclerview_notification,viewGroup,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,int position){
        NotificationClass item=items.get(position);
        viewHolder.setItem(item);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyDataSetChanged();
            }
        });
    }

    public int getItemCount(){
        return items.size();
    }

    public void addItem(NotificationClass item){
        items.add(item);
    }

    public void setItems(ArrayList<NotificationClass> items){
        this.items=items;
    }

    public NotificationClass getItem(int position){
        return items.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView notification_content;
        TextView notification_time;
        LinearLayout recyclerview_notification_layout;

        public ViewHolder(View itemView){
            super(itemView);

            notification_content=itemView.findViewById(R.id.public_content);
            notification_time=itemView.findViewById(R.id.public_time);

            recyclerview_notification_layout=itemView.findViewById(R.id.recyclerview_notification_layout2);
        }

        public void setItem(NotificationClass item){
            notification_content.setText(item.content);
            notification_time.setText(item.time);

            //일기 교환 신청 알림일 경우
            if(notification_content.getText().toString().contains("님에게 일기 교환 신청이 도착했어요!")){
                recyclerview_notification_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("감지","욥");
                        dialog_accept_function();
                    }
                });
            }
        }
    }

    public static void dialog_accept_function(){
        //accept 다이얼로그 생성
        Dialog dialog_accept=new Dialog(context);
        dialog_accept.setContentView(R.layout.dialog_accept);
        dialog_accept.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_accept.show();

        dialog_accept.findViewById(R.id.dialog_accpet_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_accept.dismiss();
            }
        });
        dialog_accept.findViewById(R.id.dialog_accept_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //거절하기를 눌렀을 때의 동작
                dialog_accept.dismiss();
            }
        });
        dialog_accept.findViewById(R.id.dialog_accept_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //수락하기를 눌렀을 때의 동작
                dialog_accept.dismiss();
            }
        });
        dialog_accept.findViewById(R.id.dialog_accept_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //일기 구경하러 가기 눌렀을 때의 동작
                dialog_accept.dismiss();
            }
        });
    }
}

