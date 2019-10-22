package com.example.carinsurance.Models;


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