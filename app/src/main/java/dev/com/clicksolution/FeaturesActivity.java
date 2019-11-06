package dev.com.clicksolution;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import dev.com.clicksolution.fragments.AddNewDeviceFragment;
import dev.com.clicksolution.fragments.ConnectionFragment;
import dev.com.clicksolution.fragments.DeviceDetailsFragment;
import dev.com.clicksolution.interfaces.Callback;

public class FeaturesActivity extends AppCompatActivity {
    private  FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_device2);

        fragmentManager= getSupportFragmentManager();
        Intent intent = getIntent();
        int fragmentNumber= intent.getIntExtra(HomeScreen.CONNECTION_FRAGMENT_KEY,0);
        // if you come on this activity from homeScreen by clicking the recycler view items
        // than value of fragmentNumber must be equal to [HomeFragment.CONNECTION_FRAGMENT]
        if(fragmentNumber==0) {
            AddNewDeviceFragment addNewDeviceFragment = new AddNewDeviceFragment(new Callback() {
                @Override
                public void callback() {

                    Log.d("call",""+2);
                 hostFragments(new DeviceDetailsFragment(),2);

                }
            });
            hostFragments(addNewDeviceFragment, 0);

            Log.d("Open",""+0);
        }
        else if(fragmentNumber==HomeScreen.CONNECTION_FRAGMENT){
            ConnectionFragment connectionFragment=new ConnectionFragment();
            hostFragments(connectionFragment,HomeScreen.CONNECTION_FRAGMENT);
        }
    }

    private void hostFragments(Fragment fragment, int id) {

        fragmentTransaction= fragmentManager.beginTransaction();
        Log.d("insideOpen",""+id);
        fragmentTransaction.replace(R.id.frameLayout,fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("addN"+id).commit();

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
