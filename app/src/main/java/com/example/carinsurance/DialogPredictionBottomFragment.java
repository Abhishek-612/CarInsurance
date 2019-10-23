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

import com.example.carinsurance.Models.Claims;

import org.w3c.dom.Text;

public class DialogPredictionBottomFragment extends BottomSheetDialogFragment {


    TextView cost,date;
    Button request;
    View rootView;

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

        // get the views and attach the listener

        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cost=(TextView)rootView.findViewById(R.id.cost);
        date=(TextView)rootView.findViewById(R.id.dateBottom);
        request=(Button)rootView.findViewById(R.id.raise);

        Claims claims=new Claims("9/8/99",5000);

        date.setText(claims.getRaisedAt());
        cost.setText("₹ "+claims.getPrice());

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }



}