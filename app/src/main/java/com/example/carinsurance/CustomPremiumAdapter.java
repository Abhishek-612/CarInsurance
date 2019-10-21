package com.example.carinsurance;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomPremiumAdapter extends RecyclerView.Adapter<CustomPremiumAdapter.MyViewHolder> {

    private ArrayList<PremiumDataModel> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView premium, model, doi;
        Button pay;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.model = (TextView) itemView.findViewById(R.id.car_premium);
            this.premium = (TextView) itemView.findViewById(R.id.premium);
            this.doi = (TextView) itemView.findViewById(R.id.doi);
            this.pay = (Button) itemView.findViewById(R.id.pay_premium);

        }
    }

    public CustomPremiumAdapter(ArrayList<PremiumDataModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.premium_card_layout, parent, false);

        view.setOnClickListener(PremiumFragment.myPremiumOnClickListener);

        CustomPremiumAdapter.MyViewHolder myViewHolder = new CustomPremiumAdapter.MyViewHolder(view);
        return myViewHolder;
    }



    @Override
    public void onBindViewHolder(final CustomPremiumAdapter.MyViewHolder holder, final int listPosition) {

        TextView model = holder.model;
        TextView premium = holder.premium;
        TextView doi = holder.doi;
        Button pay=holder.pay;


        model.setText(dataSet.get(listPosition).getModel());
        premium.setText(dataSet.get(listPosition).getPremium());
        doi.setText(dataSet.get(listPosition).getDoi());

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your helper class", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}