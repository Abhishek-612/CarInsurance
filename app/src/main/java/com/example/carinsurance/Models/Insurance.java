package com.example.carinsurance.Models;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.example.carinsurance.InsuranceFragment;
import com.example.carinsurance.PredictionFragment;
import com.example.carinsurance.R;

public class Insurance {

    String vehicleNum;
    double premium;
    String boughtAt;
    double claim;

    public Insurance() {
        this.vehicleNum = vehicleNum;
        this.premium = premium;
        this.boughtAt = boughtAt;
        this.claim = claim;
    }

    public Insurance(String vehicleNum, double premium, String boughtAt) {
        this.vehicleNum = vehicleNum;
        this.premium = premium;
        this.boughtAt = boughtAt;
    }

    public String getVehicleNum() {
        return vehicleNum;
    }

    public double getPremium() {
        return premium;
    }

    public String getBoughtAt() {
        return boughtAt;
    }

    public double getClaim() {
        return claim;
    }


    //TODO: Add functions
    public void pay(){

    }

    public void updatePremium(){

    }

    public void raiseClaim(AppCompatActivity context){
            context.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new PredictionFragment())
                .commit();
    }

}
