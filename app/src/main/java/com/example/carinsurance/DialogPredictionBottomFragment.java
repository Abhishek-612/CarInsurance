package com.example.carinsurance;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

        Claims claims= null;
        try {
            claims = Claims.getJSON(data.getString("predictions"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        date.setText(claims.getRaisedAt());
        cost.setText("₹ "+claims.getPrice());
        location.setText(claims.getLocation());
        if(claims.getLocation().equals("whole"))
            severity.setVisibility(View.GONE);
        else
            severity.setText(claims.getSeverity());

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), CarsFragment.vehicleNum, Toast.LENGTH_SHORT).show();
            }
        });
    }



}