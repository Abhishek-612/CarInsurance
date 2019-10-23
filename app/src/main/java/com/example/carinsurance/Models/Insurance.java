package com.example.carinsurance.Models;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.example.carinsurance.InsuranceFragment;
import com.example.carinsurance.PredictionFragment;
import com.example.carinsurance.R;
import com.example.carinsurance.VolleyHelper;

import org.json.JSONObject;

import java.util.HashMap;

public class Insurance {
    public double premium,balance;
    String boughtAt;

    public Insurance() {

    }

    public Insurance(String vehicleNum, double premium, String boughtAt) {
        this.premium = premium;
        this.boughtAt = boughtAt;
    }


    public double getPremium() {
        return premium;
    }

    public String getBoughtAt() {
        return boughtAt;
    }


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

    public void findInsurance(Context c, String carNum, final VolleyHelper.VolleyCallBack listener){
        VolleyHelper helper = new VolleyHelper(c);
        final ProgressDialog dialog = ProgressDialog.show(c,"Loading data","Please wait while we fetch your car insurance details",true);
        dialog.setCancelable(false);
        HashMap<String,Object> data = new HashMap<>();
        data.put("vehicle",carNum);
        helper.callApi("androidApi/getInsurance", data, new VolleyHelper.VolleyCallBack() {
            @Override
            public void data(JSONObject data, String error) {
                dialog.dismiss();
                listener.data(data,error);
            }
        });
    }
    public static Insurance getJSON(JSONObject data){
        try {
            Insurance i = new Insurance();
            i.boughtAt = data.getString("boughtAt");
            i.premium = data.getDouble("premium");
            i.balance = data.getDouble("balance");
            return i;
        }catch (Exception e){}

        return null;
    }

}
