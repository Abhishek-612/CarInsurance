package com.example.carinsurance;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.carinsurance.Models.Car;
import com.example.carinsurance.Models.Transactions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class TransactionFragment extends Fragment {

    View rootView;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Transactions> transactions;
    static View.OnClickListener myOnClickListener;

    public TransactionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_transaction, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewSetter();
    }

    void viewSetter() {
        getTransactions();

        recyclerView = (RecyclerView) rootView.findViewById(R.id.transaction_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager) layoutManager).setReverseLayout(true);
        ((LinearLayoutManager) layoutManager).setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        transactions = new ArrayList<Transactions>();

        transactions.add(new Transactions("Maruti Swift","MH02CV7175","NEYF32SVN",1));
        transactions.add(new Transactions("Maruti Swift","MH02CV7175","NEYF32SVN",2));

        adapter = new CustomTransactionsAdapter(transactions);
        recyclerView.setAdapter(adapter);
    }


    void getTransactions(){
        final ProgressDialog dialog = ProgressDialog.show(getContext(),"Fetching transactions","Please wait while we load your previous transactions",false);
        dialog.setCancelable(false);
        String userName = getActivity().getSharedPreferences("user",Context.MODE_PRIVATE).getString("username","kds");
        VolleyHelper helper = new VolleyHelper(getContext());
        HashMap<String,Object> data = new HashMap<>();
        data.put("customer",userName);
        helper.callApi("androidApi/getCars", data, new VolleyHelper.VolleyCallBack() {
            @Override
            public void data(JSONObject data, String error) { //TODO: change function
                dialog.dismiss();
                if(data != null){
                    try {
                        JSONArray array = new JSONArray(data.getString("result"));
                        transactions.clear();
                        for(int i =0;i<array.length();i++){
//                            transactions.add(Transactions.fromJson(array.getJSONObject(i)));
                        }
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }
    
}


