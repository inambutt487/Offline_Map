/*
package com.appscottage.offline.maphd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SplashActivity extends Activity {
ImageView j,a;
    RelativeLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float calculatedWidth = metrics.widthPixels * 0.52f;
        float calculatedWidth1 = metrics.widthPixels * 0.35f;
        float calculatedHeight = metrics.widthPixels * 0.4f;
        float calculatedHeight1 = metrics.widthPixels * 0.32f;
        setContentView(R.layout.activity_splash);


        linearLayout=(RelativeLayout) findViewById(R.id.textlayout);
        j=(ImageView)findViewById(R.id.j);
        a=(ImageView)findViewById(R.id.a);
        TranslateAnimation animate = new TranslateAnimation(-200, calculatedWidth, 0, calculatedHeight);
        animate.setDuration(2000);
        animate.setFillAfter(true);
        j.startAnimation(animate);
        TranslateAnimation animate1 = new TranslateAnimation(200, (-1)*calculatedWidth1, 0, calculatedHeight1);
        animate1.setDuration(2000);
        animate1.setFillAfter(true);

        animate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        a.startAnimation(animate1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fadeInAnimation = AnimationUtils.loadAnimation(
                        SplashActivity.this, R.anim.fade_in_view);
                linearLayout.startAnimation(fadeInAnimation);
                fadeInAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        linearLayout.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // TODO Auto-generated method stub

                        Intent i=new Intent(SplashActivity.this,MainMenu.class);
                        startActivity(i);

                        finish();
                    }
                });
            }

        }, 2000);
    }


}
*/
