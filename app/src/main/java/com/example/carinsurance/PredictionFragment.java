package com.example.carinsurance;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.gesture.Prediction;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.carinsurance.Models.Predictions;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageActivity;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONObject;

import java.util.HashMap;

import org.json.JSONObject;

import java.util.HashMap;


public class PredictionFragment extends Fragment {

    View rootView;
    Button capture,upload;
    ImageView image;
    Uri resultUri=null;
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
        upload=(Button)rootView.findViewById(R.id.upload);
        image=(ImageView)rootView.findViewById(R.id.image);

        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity().setAspectRatio(1,1).setGuidelines(CropImageView.Guidelines.ON).start(getContext(),PredictionFragment.this);


            }
        });


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(resultUri!=null){
                    Predictions pred = new Predictions();
                    pred.uploadImage(resultUri);
                    final ProgressDialog progressdialog = ProgressDialog.show(
                            getContext(), "Please wait",
                            "Loading please wait..", true);
                    progressdialog.setCancelable(false);
                    pred.predict(getContext(), new HashMap<String, String>(), new VolleyHelper.VolleyCallBack() {
                        @Override
                        public void data(JSONObject data, String error) {
                            progressdialog.dismiss();
                            if(data!=null){
                                Log.d("response",data.toString());
                                handleResponse(data);
                            }
                            else
                                Log.d("response error",error);
                        }
                    });
                }


                else
                    Snackbar.make(view, "Error uploading image. Please try again", Snackbar.LENGTH_SHORT)
                            .setAction("Try again", null).show();
            }
        });
    }

    private void handleResponse(JSONObject data) {
        Bundle b = new Bundle();
        b.putString("data",data.toString());
        DialogPredictionBottomFragment dialogPredictionBottomFragment =
                DialogPredictionBottomFragment.newInstance();
        dialogPredictionBottomFragment.setArguments(b);
        dialogPredictionBottomFragment.show(((AppCompatActivity)getContext()).getSupportFragmentManager(),
                "add_second_dialog_fragment");
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == Activity.RESULT_OK) {
                resultUri = result.getUri();
                image.setImageURI(resultUri);
                upload.setVisibility(View.VISIBLE);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

    }
}