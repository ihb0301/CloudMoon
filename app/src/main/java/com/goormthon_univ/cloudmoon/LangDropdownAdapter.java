package com.goormthon_univ.cloudmoon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LangDropdownAdapter extends RecyclerView.Adapter<LangDropdownAdapter.ViewHolder>{
    ArrayList<Lang> items=new ArrayList<Lang>();
    private int selectedItemPosition=0;

    //Preferences 관리를 위한 객체
    PreferencesManager manager;

    //getResource 사용을 위한 context
    Context context;

    //버튼 넘겨줄 것
    Button activity_register_next;
    AutoCompleteTextView activity_register_lang;

    //모국어(0)인지 학습 언어(1)인지
    int lang_type;

    public LangDropdownAdapter(Context context, Button activity_register_next, AutoCompleteTextView activity_register_lang, int lang_type){
        this.context=context;
        this.activity_register_next=activity_register_next;
        this.activity_register_lang=activity_register_lang;
        this.lang_type=lang_type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType){
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View itemView=inflater.inflate(R.layout.lang_dropdown,viewGroup,false);

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
            viewHolder.lang_dropdown_name.setBackgroundColor(context.getResources().getColor(R.color.p_100));
            if(lang_type==0){
                manager.pref_write_string("lang_a",item.lang);
            }else{
                manager.pref_write_string("lang_b",item.lang);
            }
            activity_register_lang.setText(item.lang);
            viewHolder.lang_dropdown_check.setVisibility(View.VISIBLE);
        }else{
            viewHolder.lang_dropdown_name.setBackgroundColor(context.getResources().getColor(R.color.white));
            viewHolder.lang_dropdown_check.setVisibility(View.GONE);
        }


        activity_register_next.setEnabled(true);
        activity_register_next.setBackgroundColor(context.getResources().getColor(R.color.p_500));
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
        TextView lang_dropdown_contury;
        TextView lang_dropdown_caption;
        LinearLayout lang_dropdown_name;
        ImageView lang_dropdown_check;


        public ViewHolder(View itemView){
            super(itemView);

            lang_dropdown_contury=itemView.findViewById(R.id.lang_dropdown_contury);
            lang_dropdown_caption=itemView.findViewById(R.id.lang_dropdown_caption);

            lang_dropdown_name=itemView.findViewById(R.id.lang_dropdown_name);
            lang_dropdown_check=itemView.findViewById(R.id.lang_dropdown_check);
        }

        public void setItem(Lang item){
            lang_dropdown_contury.setText(item.lang);
            lang_dropdown_caption.setText(item.lang_des);
        }
    }
}

