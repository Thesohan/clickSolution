package dev.com.clicksolution;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Objects;

public class SplashActivity extends AppCompatActivity {

   private ImageView imageView;
   private Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //hiding actionbar
        Objects.requireNonNull(getSupportActionBar()).hide();
        imageView = findViewById(R.id.logo);
        animation = AnimationUtils.loadAnimation(this, R.anim.splash_fade_animation);
        // final Animation animation_out= AnimationUtils.loadAnimation(this,R.anim.fade_out);
        postDelayedHandler();
        imageView.startAnimation(animation);
    }

    private void postDelayedHandler() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                // imageView.startAnimation(animation_out);
                startActivity(intent);
                overridePendingTransition(R.anim.splash_fade_animation, R.anim.fade_out);
                finish();
            }
        }, 3000);
    }
}
