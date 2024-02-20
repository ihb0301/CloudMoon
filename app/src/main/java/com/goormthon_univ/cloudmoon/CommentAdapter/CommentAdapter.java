package com.goormthon_univ.cloudmoon.CommentAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.goormthon_univ.cloudmoon.Lang;
import com.goormthon_univ.cloudmoon.PreferencesManager;
import com.goormthon_univ.cloudmoon.R;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{
    ArrayList<Comment> items=new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType){
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View itemView=inflater.inflate(R.layout.recyclerview_comment,viewGroup,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,int position){
        Comment item=items.get(position);
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

    public void addItem(Comment item){
        items.add(item);
    }

    public void setItems(ArrayList<Comment> items){
        this.items=items;
    }

    public Comment getItem(int position){
        return items.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView comment_image;
        TextView comment_nickname;
        TextView comment_content;
        ConstraintLayout recyclerview_lang_layout;
        ImageView recyclerview_radius2;


        public ViewHolder(View itemView){
            super(itemView);

            comment_image=itemView.findViewById(R.id.comment_image);
            comment_nickname=itemView.findViewById(R.id.comment_nickname);
            comment_content=itemView.findViewById(R.id.comment_content);

            recyclerview_lang_layout=itemView.findViewById(R.id.recyclerview_lang_layout);
            recyclerview_radius2=itemView.findViewById(R.id.recyclerview_radius2);
        }

        public void setItem(Comment item){
            comment_nickname.setText(item.nickname);
            comment_content.setText(item.content);
        }
    }
}

