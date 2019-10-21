package com.example.carinsurance;

public class PremiumDataModel {

    String model;
    String premium;
    String doi;

    public PremiumDataModel(String model, String premium, String doi) {
        this.model = model;
        this.premium = premium;
        this.doi = doi;
    }

    public String getModel() {
        return model;
    }

    public String getPremium() {
        return premium;
    }

    public String getDoi() {
        return doi;
    }
}
