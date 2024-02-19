package com.goormthon_univ.cloudmoon;

import android.graphics.Color;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LangAdapter extends RecyclerView.Adapter<LangAdapter.ViewHolder>{
    ArrayList<Lang> items=new ArrayList<Lang>();
    private int selectedItemPosition=0;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType){
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View itemView=inflater.inflate(R.layout.recyclerview_lang,viewGroup,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,int position){
        Lang item=items.get(position);
        viewHolder.setItem(item);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItemPosition=position;
                notifyDataSetChanged();
            }
        });
        if(selectedItemPosition==position){
            viewHolder.recyclerview_radius2.setVisibility(View.VISIBLE);
        }else{
            viewHolder.recyclerview_radius2.setVisibility(View.GONE);
        }
    }

    public int getItemCount(){
        return items.size();
    }

    public void addItem(Lang item){
        items.add(item);
    }

    public void setItems(ArrayList<Lang> items){
        this.items=items;
    }

    public Lang getItem(int position){
        return items.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView level_image;
        TextView level_text;
        TextView level_text_des;
        ConstraintLayout recyclerview_lang_layout;
        ImageView recyclerview_radius2;

        public ViewHolder(View itemView){
            super(itemView);

            level_image=itemView.findViewById(R.id.level_image);
            level_text=itemView.findViewById(R.id.level_text);
            level_text_des=itemView.findViewById(R.id.level_text_des);

            recyclerview_lang_layout=itemView.findViewById(R.id.recyclerview_lang_layout);
            recyclerview_radius2=itemView.findViewById(R.id.recyclerview_radius2);
        }

        public void setItem(Lang item){
            level_text.setText(item.lang);
            level_text_des.setText(item.lang_des);

            if(item.level==1){
                level_image.setImageResource(R.drawable.lang_level_1);
            }else if(item.level==2){
                level_image.setImageResource(R.drawable.lang_level_2);
            }else if(item.level==3){
                level_image.setImageResource(R.drawable.lang_level_3);
            }else if(item.level==4){
                level_image.setImageResource(R.drawable.lang_level_4);
            }
        }
    }
}

