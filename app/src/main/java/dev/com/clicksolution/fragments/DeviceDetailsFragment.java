package dev.com.clicksolution.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import dev.com.clicksolution.R;

public class DeviceDetailsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.device_details_fragment,container,false);
        ImageView addImage = view.findViewById(R.id.addImage);
        ImageView backGroundImage = view.findViewById(R.id.backgroundImage);
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageFromCamera();
            }
        });
        return view;
    }


    //Todo: Capture the image from camera and set the image into backGroundImage
    private void getImageFromCamera() {

    }

}
