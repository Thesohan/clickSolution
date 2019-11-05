//package dev.com.clicksolution;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.animation.Animator;
//import android.animation.AnimatorListenerAdapter;
//import android.os.Bundle;
//import android.view.View;
//import android.view.ViewAnimationUtils;
//
//public class AddNewDeviceActivity extends AppCompatActivity {
//
//    View nextView, previousView;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_new_device);
//
//        nextView = findViewById(R.id.otherDetailLayout);
//         previousView= findViewById(R.id.phoneDetailLayout);
//         previousView.setVisibility(View.VISIBLE);
//    }
//
//    public void proceedBtnClicked(View view) {
//
//        startRevealAnimation(nextView);
//        previousView.setVisibility(View.INVISIBLE);
//    }
//    public void backBtnClicked(View view) {
//
//        startRevealAnimation(previousView);
//        nextView.setVisibility(View.INVISIBLE);
//     }
//    public void startRevealAnimation(final View myView){
//        int finalHeight = myView.getMeasuredHeight();
//        int finalWidth =  myView.getMeasuredWidth();
//
//        int cy = finalHeight / 2;
//        int cx = finalWidth / 2;
//
//
//        int finalRadius = Math.max(finalWidth, finalHeight);
//        Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0f, finalRadius);
//        anim.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                super.onAnimationStart(animation);
//                myView.setVisibility(View.VISIBLE);
//
//            }
//        });
//        anim.setDuration(800);
//        // anim.setStartDelay(500);
//        anim.start();
//
//    }
//
//
//    public void imageViewClicked(View view) {
//        //get image from device
//
//
//    }
//
//    public void saveBtnClicked(View view) {
//    }
//}
