package com.rceroorkee.indoriya.gagan.rce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class Photos extends AppCompatActivity {

    private final String android_version_names[] = {
            "chairman",
            "secretary",
            "dg",
            "dean",
            "library",
            "hostel",
            "anu",
            "library2"

    };

    private final String android_image_urls[] = {
            "http://rceroorkee.net16.net/rcenoticeapp/images2/chairman.JPG",
            "http://rceroorkee.net16.net/rcenoticeapp/images2/sectury.jpg",
            "http://rceroorkee.net16.net/rcenoticeapp/images2/dg.JPG",
            "http://rceroorkee.net16.net/rcenoticeapp/images2/dean.JPG",
            "http://rceroorkee.net16.net/rcenoticeapp/images2/library.JPG",
            "http://rceroorkee.net16.net/rcenoticeapp/images2/hostel.jpg",
            "http://rceroorkee.net16.net/rcenoticeapp/images2/anu.JPG",
            "http://rceroorkee.net16.net/rcenoticeapp/images2/library2.JPG",


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        initViews();
    }

    private void initViews(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList androidVersions = prepareData();
        DataAdapter adapter = new DataAdapter(getApplicationContext(),androidVersions);
        recyclerView.setAdapter(adapter);

    }
    private ArrayList prepareData(){

        ArrayList android_version = new ArrayList<>();
        for(int i=0;i<android_version_names.length;i++){
            AndroidVersion androidVersion = new AndroidVersion();
            androidVersion.setAndroid_version_name(android_version_names[i]);
            androidVersion.setAndroid_image_url(android_image_urls[i]);
            android_version.add(androidVersion);
        }
        return android_version;
    }
}