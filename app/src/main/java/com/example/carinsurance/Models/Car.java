package com.example.carinsurance.Models;


import org.json.JSONException;
import org.json.JSONObject;

public class Car {

    String model;
    String vehicleNum;
    String engine;
    String chassis;
    String purchasedAt;
    int claims;


    public Car(String model, String vehicleNum, String engine, String chassis, String purchasedAt,int claims) {
        this.model = model;
        this.vehicleNum = vehicleNum;
        this.engine = engine;
        this.chassis = chassis;
        this.purchasedAt = purchasedAt;
        this.claims = claims;
    }

    public Car(String model, String vehicleNum, String engine, String chassis) {
        this.model = model;
        this.vehicleNum = vehicleNum;
        this.engine = engine;
        this.chassis = chassis;
    }

    public static Car fromJson(JSONObject o) {
        try {
            Car c = new Car(o.getString("model"),o.getString("vehicleNum"),
                    o.getString("model"),o.getString("vehicleNum"),
                    o.getString("purchasedAt"),o.getInt("claims"));
            return c;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getModel() {
        return model;
    }

    public String getVehicleNum() {
        return vehicleNum;
    }

    public String getEngine() {
        return engine;
    }

    public String getChassis() {
        return chassis;
    }

    public String getPurchasedAt() {
        return purchasedAt;
    }

    public int getClaims() {
        return claims;
    }


    public void sellCar(){
        //TODO: Add sell car
    }
}