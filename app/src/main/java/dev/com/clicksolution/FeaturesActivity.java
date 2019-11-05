package dev.com.clicksolution;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import java.util.Objects;

import dev.com.clicksolution.fragments.AddNewDeviceFragment;
import dev.com.clicksolution.fragments.ConnectionFragment;

public class FeaturesActivity extends AppCompatActivity {
    private  FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_device2);
        fragmentManager= getSupportFragmentManager();
        fragmentTransaction= fragmentManager.beginTransaction();

        Intent intent = getIntent();
        int fragmentNumber= intent.getIntExtra(HomeScreen.CONNECTION_FRAGMENT_KEY,0);
        // if you come on this activity from homeScreen by clicking the recycler view items
        // than value of fragmentNumber must be equal to [HomeFragment.CONNECTION_FRAGMENT]
        if(fragmentNumber==0) {
            AddNewDeviceFragment addNewDeviceFragment = new AddNewDeviceFragment();
            openFragment(addNewDeviceFragment, 0);
        }
        else if(fragmentNumber==HomeScreen.CONNECTION_FRAGMENT){
            ConnectionFragment connectionFragment=new ConnectionFragment();
            openFragment(connectionFragment,HomeScreen.CONNECTION_FRAGMENT);
        }
    }

    private void openFragment(Fragment fragment, int id) {

        fragmentTransaction.replace(R.id.frameLayout,fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("addNewDeviceFragment"+id).commit();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(getSupportFragmentManager().getBackStackEntryCount()==0){
            finish();
        }
    }
}
