package com.goormthon_univ.cloudmoon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.goormthon_univ.cloudmoon.CommentAdapter.Comment;
import com.goormthon_univ.cloudmoon.CommentAdapter.CommentAdapter;

public class HomeActivity3 extends AppCompatActivity {
    LinearLayout home3_keyboard_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home3);

        home3_keyboard_layout=findViewById(R.id.home3_keyboard_layout);


        TextView home3_title=findViewById(R.id.home3_title);

        Intent intent=getIntent();
        home3_title.setText(intent.getStringExtra("date"));

        //상대방 댓글 어뎁터 연결
        RecyclerView home3_partner_comment_recycler=findViewById(R.id.home3_partner_comment_recycler);

        CommentAdapter commentadapter_partner=new CommentAdapter();

        LinearLayoutManager layoutManager_partner=new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false);
        home3_partner_comment_recycler.setLayoutManager(layoutManager_partner);
        home3_partner_comment_recycler.setAdapter(commentadapter_partner);

        Comment c_p_1=new Comment("goorm","‘않 좋았어’ 아니고 ‘안 좋았어’라고 써야 돼!");

        commentadapter_partner.addItem(c_p_1);
        commentadapter_partner.notifyDataSetChanged();

        //나의 댓글 어뎁터 연결
        RecyclerView home3_me_comment_recycler=findViewById(R.id.home3_me_comment_recycler);

        CommentAdapter commentadapter_me=new CommentAdapter();

        LinearLayoutManager layoutManager_me=new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false);
        home3_me_comment_recycler.setLayoutManager(layoutManager_me);
        home3_me_comment_recycler.setAdapter(commentadapter_me);

        Comment c_m_1=new Comment("moon","辛苦了，希望下次能成功。");
        Comment c_m_2=new Comment("goorm","@moon 谢谢！");

        commentadapter_me.addItem(c_m_1);
        commentadapter_me.addItem(c_m_2);
        commentadapter_me.notifyDataSetChanged();
    }

    public void mainactivity(View view){
        Intent intent=new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }

    public void comment_onclick_partner(View view){
        home3_keyboard_layout.setVisibility(View.VISIBLE);
    }

    public void comment_onclick_me(View view){
        home3_keyboard_layout.setVisibility(View.VISIBLE);
    }
}