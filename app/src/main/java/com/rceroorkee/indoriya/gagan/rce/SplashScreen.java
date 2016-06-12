package com.rceroorkee.indoriya.gagan.rce;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by marc on 4/5/2016.
 */
public class SplashScreen extends AppCompatActivity {
    public void onAttachedToWindow(){
        super.onAttachedToWindow();
        Window window=getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }
    Thread splashThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        StartAnimation();
    }
    private void StartAnimation(){
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.alpha);
        anim.reset();
        LinearLayout l=(LinearLayout)findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);
        anim=AnimationUtils.loadAnimation(this,R.anim.translate);
        anim.reset();
        ImageView iv=(ImageView) findViewById(R.id.splash);
        iv.clearAnimation();
        iv.startAnimation(anim);
        TextView tv=(TextView) findViewById(R.id.splashtagline);
        tv.clearAnimation();
        tv.startAnimation(anim);
        TextView tvDev=(TextView) findViewById(R.id.splashDeveloper);
        tvDev.clearAnimation();
        tvDev.startAnimation(anim);

        splashThread= new  Thread(){
            public void  run(){
                try{
                    int waited=0;
                    //splash screen pause time
                    while(waited<3500){
                        sleep(100);
                        waited+=100;
                    }
                    Intent intent= new Intent(SplashScreen.this,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashScreen.this.finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    SplashScreen.this.finish();
                }
            }
        };
        splashThread.start();


    }
}
