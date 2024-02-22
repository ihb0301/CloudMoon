package com.goormthon_univ.cloudmoon.PublicAdapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.goormthon_univ.cloudmoon.CommentAdapter.Comment;
import com.goormthon_univ.cloudmoon.R;

import java.util.ArrayList;

public class PublicAdapter extends RecyclerView.Adapter<PublicAdapter.ViewHolder>{
    ArrayList<Public> items=new ArrayList<>();
    //context 사용을 위한 객체
    static Context context;

    public PublicAdapter(Context context){
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType){
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View itemView=inflater.inflate(R.layout.recyclerview_public,viewGroup,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,int position){
        Public item=items.get(position);
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

    public void addItem(Public item){
        items.add(item);
    }

    public void setItems(ArrayList<Public> items){
        this.items=items;
    }

    public Public getItem(int position){
        return items.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView public_image;
        TextView public_nickname;
        TextView public_content;
        TextView public_time;
        ImageView public_send;
        ConstraintLayout recyclerview_public_layout;

        public ViewHolder(View itemView){
            super(itemView);

            public_image=itemView.findViewById(R.id.public_image);
            public_send=itemView.findViewById(R.id.public_send);
            public_nickname=itemView.findViewById(R.id.public_nickname);
            public_content=itemView.findViewById(R.id.public_content);
            public_time=itemView.findViewById(R.id.public_time);

            recyclerview_public_layout=itemView.findViewById(R.id.recyclerview_public_layout);

            Dialog dialog_friend=new Dialog(context);
            dialog_friend.setContentView(R.layout.dialog_friend);
            dialog_friend.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            public_send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog_friend.show();

                    Button dialog_friend_request=dialog_friend.findViewById(R.id.dialog_friend_request);
                    Button dialog_friend_cancel=dialog_friend.findViewById(R.id.dialog_friend_cancel);
                    ImageView dialog_friend_close=dialog_friend.findViewById(R.id.dialog_friend_close);

                    dialog_friend_close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog_friend.dismiss();
                        }
                    });
                }
            });
        }

        public void setItem(Public item){
            public_nickname.setText(item.nickname);
            public_content.setText(item.content);
            public_time.setText(item.time);
        }
    }
}

