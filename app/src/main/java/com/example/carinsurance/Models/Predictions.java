package com.example.carinsurance.Models;

public class Predictions {

    String predictedOn;
    double predictedPrice;
    double actualPrice;

    public Predictions(String predictedOn, double predictedPrice, double actualPrice) {
        this.predictedOn = predictedOn;
        this.predictedPrice = predictedPrice;
        this.actualPrice = actualPrice;
    }

    public String getPredictedOn() {
        return predictedOn;
    }

    public double getPredictedPrice() {
        return predictedPrice;
    }

    public double getActualPrice() {
        return actualPrice;
    }

    public void predict(){

    }

    public void uploadImage(){

    }
}
