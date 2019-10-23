package com.example.carinsurance.Models;

import org.json.JSONException;
import org.json.JSONObject;

public class Transactions {

    String time,status,car;
    int premiumId;

    public Transactions(String time, String status, String car, int premiumId) {
        this.time = time;
        this.status = status;
        this.car = car;
        this.premiumId = premiumId;
    }

    public String getTime() {       //this is the date
        return time;
    }

    public String getStatus() {
        return status;
    }

    public String getCar() {
        return car;
    }

    public int getPremiumId() {
        return premiumId;
    }

    //TODO: functions need to be filled
    private void refund() {

    }

    private void initiatePayment() {

    }

//    public static Transactions fromJson(JSONObject o) {
//        try {
//            Transactions t = new Transactions(o.getString("model"),o.getString("vehicleNum"),
//                    o.getString("model"),o.getString("vehicleNum"),
//                    o.getString("purchasedAt"),o.getInt("claims"));
//            return t;
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

}
