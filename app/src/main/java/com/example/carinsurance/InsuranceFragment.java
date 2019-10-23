package com.example.carinsurance;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carinsurance.Models.Insurance;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class InsuranceFragment extends Fragment {

    View rootView;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<PremiumDataModel> data;
    static View.OnClickListener myPremiumOnClickListener;

    TextView bought,balance,premium;
    Button claim;

    public InsuranceFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_insurance, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewSetter();
    }

    void viewSetter(){
        balance=(TextView)rootView.findViewById(R.id.balance);
        bought=(TextView)rootView.findViewById(R.id.bought_on);
        premium=(TextView)rootView.findViewById(R.id.prem);
        claim=(Button)rootView.findViewById(R.id.raise_claim);

        //TODO: Insurance update values
//        setInsurance()

        claim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO:raiseClaim() for current Insurance details
                new Insurance().raiseClaim((AppCompatActivity)getContext());
            }
        });
    }

    private void setInsurance(double premium_str,double balance_str,String bought_str){
        balance.setText("₹ "+balance_str);
        premium.setText("₹ "+premium_str);
        bought.setText(bought_str);
    }





}
