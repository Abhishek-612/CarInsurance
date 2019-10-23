package com.example.carinsurance;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class VolleyHelper {

    private static final String baseUrl = "http://192.168.43.47:8000/";
    public static final String predictionsUrl = baseUrl+"androidApi/getPredictions/";
    Context c;

    public VolleyHelper(Context c){
        this.c = c;
    }

    public void callApi(String url, HashMap<String,Object> data, final VolleyCallBack listener){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, baseUrl + url,
                new JSONObject(data),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response",response.toString());
                        listener.data(response,null);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("response error",error.toString());
                        listener.data(null,error.getLocalizedMessage());
                    }
                });
        request.setRetryPolicy(new DefaultRetryPolicy(90000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(c).add(request);
    }

    public interface VolleyCallBack{
        void data(JSONObject data,String error);
    }

}
