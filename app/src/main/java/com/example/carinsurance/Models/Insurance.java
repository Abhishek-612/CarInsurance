package com.example.carinsurance.Models;

public class Insurance {

    String vehicleNum;
    double premium;
    String boughtAt;
    double claim;

    public Insurance(String vehicleNum, double premium, String boughtAt, double claim) {
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
    private void pay(){

    }

    private void updatePremium(){

    }

    private void raiseClaim(){

    }

}
