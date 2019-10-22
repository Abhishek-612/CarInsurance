package com.example.carinsurance.Models;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.carinsurance.LoginActivity;
import com.example.carinsurance.VolleyHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Customer {
    public String username,password,phoneNum,name;
    public int ownedCars;

    public void verifyUser(final Context c, final VolleyHelper.VolleyCallBack listener){
        VolleyHelper helper = new VolleyHelper(c);
        HashMap<String,Object> data = new HashMap<>();
        data.put("username",username);
        data.put("password",password);
        helper.callApi("androidApi/verifyUser", data, new VolleyHelper.VolleyCallBack() {
            @Override
            public void data(JSONObject data, String error) {
                Log.d("response","data response");
                if(data == null){
                    Toast.makeText(c,"Error occurred "+error, Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.data(data,error);
            }
        });
    }

    public void addCar(){

    }

    public static Customer getObject(JSONObject jsonObject){
        try {
            if(jsonObject.getString("status").equals("success") && jsonObject.getString("result").equals("loggedIn")){
                Customer c = new Customer();
                JSONObject data = new JSONObject(jsonObject.getString("user"));
                c.ownedCars = data.getInt("ownedCars");
                c.username = data.getString("username");
                c.phoneNum = data.getString("phoneNum");
                c.name = data.getString("name");
                return c;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }



}
