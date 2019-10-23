package com.example.carinsurance.Models;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.example.carinsurance.VolleyHelper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;

public class Predictions {

    String predictedOn;
    double predictedPrice;
    double actualPrice;
    private RequestParams params;

    public Predictions(String predictedOn, double predictedPrice, double actualPrice) {
        this.predictedOn = predictedOn;
        this.predictedPrice = predictedPrice;
        this.actualPrice = actualPrice;
        params = new RequestParams();
    }

    public Predictions() {
        params = new RequestParams();
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

    public void predict(final Context c, HashMap<String,String> data, final VolleyHelper.VolleyCallBack listener){
        for(String k:data.keySet()){
            params.put(k,data.get(k));
        }
        AsyncHttpClient myClient = new AsyncHttpClient();
        myClient.setConnectTimeout(90000);
        myClient.setResponseTimeout(90000);
        myClient.setTimeout(90000);
        myClient.post(c, VolleyHelper.predictionsUrl, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
//                Toast.makeText(c, "Success", Toast.LENGTH_SHORT).show();
                listener.data(response,null);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(c, "Fail", Toast.LENGTH_SHORT).show();
                listener.data(null,errorResponse.toString());


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(c, "Fail", Toast.LENGTH_SHORT).show();
                listener.data(null,errorResponse.toString());


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast.makeText(c, "Fail", Toast.LENGTH_SHORT).show();
                listener.data(null,responseString);

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
                listener.data(null,responseString);

            }
        });
    }



    public void uploadImage(Uri imageUri){
        try {
            params.put("uploadedFile",new File(imageUri.getPath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
