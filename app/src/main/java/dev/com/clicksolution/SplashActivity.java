package dev.com.clicksolution;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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

        new CountDownTimer(3000,1){
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                //when thread is completed this method will be called
                // This method will be executed once the timer is over
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                // imageView.startAnimation(animation_out);
                startActivity(intent);
                finish();

            }
        }.start();


    }
}
