package com.example.carinsurance.Models;

import android.net.Uri;

public class Predictions {

    String predictedOn;
    double predictedPrice;
    double actualPrice;

    public Predictions(String predictedOn, double predictedPrice, double actualPrice) {
        this.predictedOn = predictedOn;
        this.predictedPrice = predictedPrice;
        this.actualPrice = actualPrice;
    }

    public Predictions() {

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

    public void uploadImage(Uri imageUri){

    }
}
