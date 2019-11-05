package dev.com.clicksolution.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

import dev.com.clicksolution.R;

public class AddNewDeviceFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.add_new_device_fragment,container,false);
        Button proceedButton = view.findViewById(R.id.proceedButton);
        Button closeButton = view.findViewById(R.id.closeButton);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).onBackPressed();
            }
        });


        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager =getFragmentManager();
                assert fragmentManager != null;
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                DeviceDetailsFragment deviceDetailsFragment=new DeviceDetailsFragment();
                fragmentTransaction.replace(R.id.frameLayout,deviceDetailsFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("deviceDetailsFragment").commit();
            }
        });
        return view;
    }

}
