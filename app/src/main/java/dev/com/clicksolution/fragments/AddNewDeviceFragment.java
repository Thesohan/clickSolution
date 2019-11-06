package dev.com.clicksolution.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import dev.com.clicksolution.R;
import dev.com.clicksolution.interfaces.Callback;

public class AddNewDeviceFragment extends Fragment {

    private Callback callbackFromFragment;

    public AddNewDeviceFragment(Callback callbackFromFragment) {
        this.callbackFromFragment = callbackFromFragment;
    }


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
                callbackFromFragment.callback();
            }
        });
        return view;
    }

}
