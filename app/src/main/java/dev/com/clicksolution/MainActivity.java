package dev.com.clicksolution;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MyViewPagerAdapter viewPagerAdapter;
    private LinearLayout dotsLinearLayout;
    private TextView[] dotsTextView;
    private int[] layouts;
    private Button skipButton, nextButton;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Checking for first time launch - before calling setContentView()
//        prefManager = new PrefManager(this);
//        if (!prefManager.isFirstTimeLaunch()) {
//            launchHomeScreen();
//            finish();
//        }


        /// hiding actionbar
        Objects.requireNonNull(getSupportActionBar()).hide();
        // Making notification bar transparent
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_main);

        viewPager= findViewById(R.id.view_pager);
        dotsLinearLayout = findViewById(R.id.layoutDots);
        skipButton = findViewById(R.id.btn_skip);
        nextButton = findViewById(R.id.btn_next);


        // layouts of all welcome sliders
        layouts= new int[]{
                R.layout.slide_one,
                R.layout.slide_two,
                R.layout.slide_three};

        // adding bottom dotsTextView
        addBottomDots(0);



        viewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current= getItem(+1);
                if(current < layouts.length){
                    viewPager.setCurrentItem(current);
                }else {
                    launchHomeScreen(); }
            }
        });
    }// onCreate()


    private void launchHomeScreen() {
        //prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(MainActivity.this, HomeScreen.class));
        overridePendingTransition(R.anim.splash_fade_animation, R.anim.fade_out);
        finish();
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void addBottomDots(int currentPage) {
        dotsTextView = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLinearLayout.removeAllViews();
        for (int i = 0; i < dotsTextView.length; i++) {
            dotsTextView[i] = new TextView(this);
            dotsTextView[i].setText(getString(R.string.bullet_filled)+"  ");
            dotsTextView[i].setTextSize(15);
            dotsTextView[i].setTextColor(colorsInactive[currentPage]);
            dotsLinearLayout.addView(dotsTextView[i]);
        }

        if (dotsTextView.length > 0)
            dotsTextView[currentPage].setTextColor(colorsActive[currentPage]);
    }

    // viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener= new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                nextButton.setText(getString(R.string.start));
                skipButton.setVisibility(View.INVISIBLE);
            } else {
                // still pages are left
                nextButton.setText(getString(R.string.next));
                skipButton.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

//---------------------------------------------------------------------------------------------------------------

    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            layoutInflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view= layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);
            return view;}

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View view= (View) object;
            container.removeView(view);
        }
    }

}