package com.rceroorkee.indoriya.gagan.rce;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.pushbots.push.Pushbots;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    private DrawerLayout drawerLayout;
    ImageView notice,photos,videos,login,aboutus,contactus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //registration for notification
        Pushbots.sharedInstance().init(this);
        Pushbots.sharedInstance().setCustomHandler(customHandler.class);
        //Toolbar Attachment
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //initialization of images as buttons
        notice=(ImageView)findViewById(R.id.btn_notice);
        photos=(ImageView)findViewById(R.id.btn_photos);
        videos=(ImageView)findViewById(R.id.btn_videos);
        login=(ImageView)findViewById(R.id.btn_login);
        aboutus=(ImageView)findViewById(R.id.btn_aboutus);
        contactus=(ImageView)findViewById(R.id.btn_contactus);

        //on click of buttons these methods will call we pass this as reference
        notice.setOnClickListener(this);
        photos.setOnClickListener(this);
        videos.setOnClickListener(this);
        login.setOnClickListener(this);
        aboutus.setOnClickListener(this);
        contactus.setOnClickListener(this);
//        tabLayout=(TabLayout)findViewById(R.id.tabLayout);

//        viewPager=(ViewPager)findViewById(R.id.viewPager);
//        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
////        viewPagerAdapter.addFragments(new notice(), "Notices");
//        viewPager.setAdapter(viewPagerAdapter);
//        tabLayout.setupWithViewPager(viewPager);

        //for navigation drawer
        initNavigationDrawer();



    }

    public void initNavigationDrawer() {

        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id) {
                    case R.id.login:
                        Intent intent= new Intent(MainActivity.this,Login.class);
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.share:
                        Toast.makeText(getApplicationContext(), "Share this app", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        shareThisApp();
                        break;

                    case R.id.Exit:
                       confirmExit();
                    default:

                }
                return true;
            }
        });
        
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.share:
                Toast.makeText(this,"Share is clicked",Toast.LENGTH_LONG).show();
                shareThisApp();
                break;
            case R.id.developer:
                Toast.makeText(this,"developer is choosed",Toast.LENGTH_LONG).show();
                Intent intent =new Intent(this,Developer.class);
                startActivity(intent);
                break;
            case R.id.exit:
                confirmExit();



        }

        return super.onOptionsItemSelected(item);
    }
    private void confirmExit(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to Exit?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        finish();

                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
public void shareThisApp(){
//    Intent shareIntent=new Intent();
//    shareIntent.setAction(Intent.ACTION_SEND);
//    shareIntent.putExtra("android.intent.extra.SUBJECT","My first Share");
//    shareIntent.setType("text/plain");
//    startActivity(shareIntent);
//
    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
    sharingIntent.setType("text/plain");
    String shareBody = "Share this app";
    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share");
    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
    startActivity(Intent.createChooser(sharingIntent, "Share via"));
}




    public void onClick(View v) {
        Intent intent;
        switch(v.getId()){
            case R.id.btn_notice:
               intent=new Intent(MainActivity.this,NoticeList.class);
               startActivity(intent);
               break;
            case R.id.btn_photos:
                 intent=new Intent(MainActivity.this,Photos.class);
                startActivity(intent);
                break;
            case R.id.btn_videos:
                 intent=new Intent(MainActivity.this,Videos.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                 intent=new Intent(MainActivity.this,Login.class);
                startActivity(intent);
                break;
            case R.id.btn_aboutus:
                 intent=new Intent(MainActivity.this,AboutUs.class);
                startActivity(intent);
                break;
            case R.id.btn_contactus:
                 intent=new Intent(MainActivity.this,ContactUS.class);
                startActivity(intent);
                break;
           default:
        }
    }
}

