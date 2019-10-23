package com.example.carinsurance;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;


public class PredictionFragment extends Fragment {

    View rootView;
    Button capture;
    ImageView image;
    private static final int REQUEST_IMAGE_CAPTURE=101;

    public PredictionFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_prediction, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewSetter();
    }

    void viewSetter(){
        capture=(Button)rootView.findViewById(R.id.capture);
        image=(ImageView)rootView.findViewById(R.id.image);

        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
//                CropImage.activity()
//                        .setGuidelines(CropImageView.Guidelines.ON)
//                        .start(getContext(),PredictionFragmentthis);
//
//// start cropping activity for pre-acquired image saved on the device
//                CropImage.activity(imageUri)
//                        .start(this);
//
//// for fragment (DO NOT use `getActivity()`)
//                CropImage.activity()
//                        .start(getContext(), this);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_IMAGE_CAPTURE && resultCode==-1){
            Bitmap bitmap=(Bitmap)data.getExtras().get("data");
            image.setImageBitmap(bitmap);
        }

    }
}