package dev.com.clicksolution.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.suke.widget.SwitchButton;

import java.util.Objects;

import dev.com.clicksolution.R;

public class ConnectionFragment extends Fragment implements View.OnClickListener{


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.connection_fragment,container,false);
        Button indoorModeButton,outdoorModeButton;
        TextView connectionTextView;
        com.suke.widget.SwitchButton switchButton;
        switchButton  = view.findViewById(R.id.switchButton);

        Objects.requireNonNull(getActivity()).setTitle("OUTDOOR");

        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(isChecked){
                    Objects.requireNonNull(getActivity()).setTitle("INDOOR");

                }
                else{

                    Objects.requireNonNull(getActivity()).setTitle("OUTDOOR");
                }

            }
        });


        return view;
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.indoorButton:
//                isIndoor=true;
//                break;
//            case R.id.outdoorButton:
//                isIndoor=false;
//                break;
//        }
//
//       changeConnectionIcon();
    }

    private void changeConnectionIcon(boolean isIndoor) {

    }
}
