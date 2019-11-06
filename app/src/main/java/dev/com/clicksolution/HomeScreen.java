package dev.com.clicksolution;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

import dev.com.clicksolution.adapter.WifiListDetailsAdapter;
import dev.com.clicksolution.dataset.RecyclerViewDataset;
import dev.com.clicksolution.interfaces.OnClickListenerEvent;

public class HomeScreen extends AppCompatActivity {
    public static final int CONNECTION_FRAGMENT=1000;
    public static final String CONNECTION_FRAGMENT_KEY="connection_fragment";
    RecyclerView recyclerView;
    LinearLayout cardView;
    FloatingActionButton floatingActionButton;
    ImageButton AddNewBtn, ScannerBtn;
    ArrayList<RecyclerViewDataset> myList = new ArrayList<>();
    WifiListDetailsAdapter wifiListDetailsAdapter;
    Animation scale_upAnim, scale_downAnim;
    boolean isEven = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        recyclerView = findViewById(R.id.rv1);
        floatingActionButton = findViewById(R.id.fab);
        cardView = findViewById(R.id.actionView);
        AddNewBtn = findViewById(R.id.addNewBtn);
        ScannerBtn = findViewById(R.id.scannerBtn);
        cardView.setVisibility(View.INVISIBLE);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);

        myList.add(new RecyclerViewDataset(R.drawable.sample1, "mi","Redmi 4"));
        myList.add(new RecyclerViewDataset(R.drawable.sample2, "mi", "Redmi A4"));
        myList.add(new RecyclerViewDataset(R.drawable.sample3, "samsung", "Galaxy M30"));
        myList.add(new RecyclerViewDataset(R.drawable.sample4, "mi", "Redmi 4"));
        myList.add(new RecyclerViewDataset(R.drawable.sample5, "apple", "Iphone 6s"));
        myList.add(new RecyclerViewDataset(R.drawable.sample6, "mi", "Sigma X3"));
        myList.add(new RecyclerViewDataset(R.drawable.sample2, "mi", "Redmi A4"));
        myList.add(new RecyclerViewDataset(R.drawable.sample3, "samsung", "Galaxy M30"));
        myList.add(new RecyclerViewDataset(R.drawable.sample4, "mi", "Redmi 4"));

        wifiListDetailsAdapter = new WifiListDetailsAdapter(myList, this, new OnClickListenerEvent() {
            @Override
            public void onClickListener(int position) {
                goToConnectionFragment(position);
            }
        });

        registerForContextMenu(recyclerView);
        recyclerView.setAdapter(wifiListDetailsAdapter);
        recyclerView.scrollToPosition(wifiListDetailsAdapter.getItemCount() - 1);

        scale_upAnim = AnimationUtils.loadAnimation(HomeScreen.this, R.anim.scale_up_fab);
        scale_downAnim = AnimationUtils.loadAnimation(HomeScreen.this, R.anim.scale_down_fab);


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFloatingActionButtonIcon();
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // final View myView = findViewById(R.id.actionView);
                changeFloatingActionButtonIcon();
            }
        });


        AddNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AddNewBtn.getVisibility() == View.VISIBLE) {
                    // starting new add_new_device_fragment only if view is visible
                    cardView.setVisibility(View.INVISIBLE);
                    isEven=!isEven;
                    startActivity(new Intent(HomeScreen.this, FeaturesActivity.class));
                }
            }
        });

    }

    private void goToConnectionFragment(int position) {
    //TODO: implement other feature here
        Intent intent = new Intent(HomeScreen.this, FeaturesActivity.class);
        intent.putExtra(CONNECTION_FRAGMENT_KEY,CONNECTION_FRAGMENT);
        startActivity(intent);
    }

    private void changeFloatingActionButtonIcon() {
        if (isEven) {
            recyclerView.setAlpha((float) 0.2);
            cardView.startAnimation(scale_upAnim);
            cardView.setVisibility(View.VISIBLE);
            GlideApp.with(getApplicationContext())
                    .load(R.drawable.ic_cross_mark)
                    .into(floatingActionButton);
            isEven=!isEven;
        } else {

            recyclerView.setAlpha((float) 1.0);
            cardView.startAnimation(scale_downAnim);
            cardView.setVisibility(View.INVISIBLE);
            GlideApp.with(getApplicationContext())
                    .load(R.drawable.ic_add_mark)
                    .into(floatingActionButton);
            isEven=!isEven;
        }
    }

    @Override
    public void onBackPressed() {

        if (isEven) {
            cardView.startAnimation(scale_downAnim);
            cardView.setVisibility(View.INVISIBLE);
            GlideApp.with(getApplicationContext())
                    .load(R.drawable.ic_add_mark)
                    .into(floatingActionButton);
            isEven=!isEven;

        } else
            super.onBackPressed();
    }

    @Override
    protected void onResume() {
        recyclerView.setAlpha((float) 1.0);
        GlideApp.with(getApplicationContext()).load(R.drawable.ic_add_mark).into(floatingActionButton);
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.getting_started_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if (id == R.id.getting_started) {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position;
        try {
            position = ((WifiListDetailsAdapter) Objects.requireNonNull(recyclerView.getAdapter())).getPosition();
        } catch (Exception e) {
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) {
            case 0:
                //Todo Edit the item
                break;
            case 1:
                //Todo Move the item
                break;
            case 2:
                //Todo Delete the item
                break;

        }

        Toast.makeText(this, ""+item.getTitle()+position, Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }
}
