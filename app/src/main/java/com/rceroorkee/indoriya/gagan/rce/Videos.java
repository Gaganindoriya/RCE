package com.rceroorkee.indoriya.gagan.rce;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Videos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);


    }
    public void openyoutube(View v){
        String s="TJAUzh_hdh0&list=FLgmC3j7SSQGzS8gAvakmP9A";
        try{
            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+s));
            startActivity(intent);
        }catch(ActivityNotFoundException ex){
            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+s));
            startActivity(intent);
        }
    }
}

