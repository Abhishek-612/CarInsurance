package com.example.carinsurance;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carinsurance.Models.Claims;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class DialogPredictionBottomFragment extends BottomSheetDialogFragment {


    TextView cost,date,severity,location;
    Button request;
    View rootView;
    JSONObject data;
    ImageView img;

    public static DialogPredictionBottomFragment newInstance() {
        return new DialogPredictionBottomFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.dialog_prediction, container,
                false);

        try {
            data = new JSONObject(getArguments().getString("data"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // get the views and attach the listener

        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cost=(TextView)rootView.findViewById(R.id.cost);
        date=(TextView)rootView.findViewById(R.id.dateBottom);
        location=(TextView)rootView.findViewById(R.id.location);
        severity=(TextView)rootView.findViewById(R.id.sever);
        request=(Button)rootView.findViewById(R.id.raise);
        img=(ImageView) rootView.findViewById(R.id.img);


        Claims claims= null;
        try {
            claims = Claims.getJSON(data.getString("predictions"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(claims == null){
            Toast.makeText(getContext(), "Error occurred", Toast.LENGTH_SHORT).show();
            return;
        }

        date.setText(claims.getTime());
        cost.setText("₹ "+claims.getPrice());
        location.setText(claims.getLocation());
        img.setImageURI(PredictionFragment.resultUri);
//        if(claims.getLocation().equals("whole"))
//            severity.setVisibility(View.GONE);
//        else
            severity.setText(claims.getSeverity());

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                new AlertDialog.Builder(getContext())
                        .setTitle("Claim requested")
                        .setMessage("Your claim is being processed. Please wait till the company responds to your request")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ((AppCompatActivity)MainActivity.c).getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.fragment_container, new CarsFragment())
                                        .commit();
                            }
                        })
                        .show();
            }
        });
    }



}