package com.example.carinsurance;

public class DataModel {

    String model;
    String number;
    String engine;
    String chassis;


    public DataModel(String model, String number, String engine, String chassis) {
        this.model = model;
        this.number = number;
        this.engine = engine;
        this.chassis = chassis;
    }

    public String getModel() {
        return model;
    }

    public String getNumber() {
        return number;
    }

    public String getEngine() {
        return engine;
    }

    public String getChassis() {
        return chassis;
    }
}