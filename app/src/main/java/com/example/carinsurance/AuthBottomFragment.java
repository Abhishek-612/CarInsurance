package com.example.carinsurance;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AuthBottomFragment extends BottomSheetDialogFragment {


    EditText code;
    Bundle bd;
    Button verify;

    public static AuthBottomFragment newInstance() {
        return new AuthBottomFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.auth_bottom_layout, container, false);

        // get the views and attach the listener

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        code=(EditText)view.findViewById(R.id.authID);
        bd=getArguments();
        verify=(Button)view.findViewById(R.id.verify);


        final long verifyCode=bd.getLong("code");




        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(code.getText().toString().equals(verifyCode))
                // TOGDO:Send car details to database
                ((AppCompatActivity)getContext()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new CarsFragment())
                        .commit();

                dismiss();
            }
        });
    }



}