package dev.com.clicksolution.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import dev.com.clicksolution.R;

public class ConnectionFragment extends Fragment implements View.OnClickListener{

    private boolean isIndoor=true;
    private ImageView connectionImageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.connection_fragment,container,false);
        Button indoorModeButton,outdoorModeButton;
        TextView connectionTextView;
        indoorModeButton=view.findViewById(R.id.indoorButton);
        outdoorModeButton = view.findViewById(R.id.outdoorButton);
        connectionTextView = view.findViewById(R.id.connectingTextView);
        connectionImageView= view.findViewById(R.id.connectionImageView);
        indoorModeButton.setOnClickListener(this);
        outdoorModeButton.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.indoorButton:
                isIndoor=true;
                break;
            case R.id.outdoorButton:
                isIndoor=false;
                break;
        }

       changeConnectionIcon();
    }

    private void changeConnectionIcon() {

        if(isIndoor){
            connectionImageView.setImageResource(R.drawable.indoor);
        }
        else{
            connectionImageView.setImageResource(R.drawable.outdoor);
        }
    }
}
