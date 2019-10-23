package com.example.carinsurance.Models;

public class Claims {

    boolean verfiedAt;
    String raisedAt,settledAt;
    double price;

    public Claims(boolean verfiedAt, String raisedAt, String settledAt, double price) {
        this.verfiedAt = verfiedAt;
        this.raisedAt = raisedAt;
        this.settledAt = settledAt;
        this.price = price;
    }

    public Claims(String raisedAt, double price) {
        this.raisedAt = raisedAt;
        this.price = price;
    }

    public boolean isVerfiedAt() {
        return verfiedAt;
    }

    public String getRaisedAt() {
        return raisedAt;
    }

    public String getSettledAt() {
        return settledAt;
    }

    public double getPrice() {
        return price;
    }

    private void settleClaim(){

    }

    private void addCost(){

    }
}
