package com.example.carinsurance.Models;

import org.json.JSONObject;

public class Claims {

    boolean verfiedAt;
    String raisedAt,settledAt,location,severity;
    String price;

    public Claims(){

    }

    public Claims(boolean verfiedAt, String raisedAt, String settledAt, String location, String severity, String price) {
        this.verfiedAt = verfiedAt;
        this.raisedAt = raisedAt;
        this.settledAt = settledAt;
        this.location = location;
        this.severity = severity;
        this.price = price;
    }

    public Claims(String raisedAt, String location, String severity, String price) {
        this.raisedAt = raisedAt;
        this.location = location;
        this.severity = severity;
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

    public String getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }

    public String getSeverity() {
        return severity;
    }

    private void settleClaim(){

    }

    private void addCost(){

    }

    public static Claims getJSON(String  data){
        Claims c = new Claims();
        try {
            JSONObject j = new JSONObject(data);
            j = new JSONObject(j.getString("max"));
            String region = j.getString("maxResult");
            c.price = j.getString("cost");
            c.location = region.split("_")[0];
            c.severity = region.split("_")[1];
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return c;
    }

}
