package com.rceroorkee.indoriya.gagan.rce;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class NoticeDetail extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    private DrawerLayout drawerLayout;

    TextView id,msg,desg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Intent intent = getIntent();

        String id = intent.getStringExtra(Config.NOTICE_ID).toString();

        String name = intent.getStringExtra(Config.NOTICE_SUB).toString();

        String desg = intent.getStringExtra(Config.NOTICE_MSG).toString();

        TextView tvid=(TextView)findViewById(R.id.tvSubject);
        TextView tvname=(TextView)findViewById(R.id.tvName);
        TextView tvdesg=(TextView)findViewById(R.id.tvDesg);

        tvid.setText(id);
        tvname.setText("Subject : "+name);
        tvdesg.setText(desg);

    }




}
