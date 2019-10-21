package com.example.carinsurance;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<DataModel> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView model,number,engine,chassis;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.model = (TextView) itemView.findViewById(R.id.carName);
            this.number = (TextView) itemView.findViewById(R.id.numberPlate);
            this.engine = (TextView) itemView.findViewById(R.id.engineNum);
            this.chassis = (TextView) itemView.findViewById(R.id.chassisNum);

        }
    }

    public CustomAdapter(ArrayList<DataModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);

        view.setOnClickListener(CarsFragment.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView model=holder.model;
        TextView number=holder.number;
        TextView engine=holder.engine;
        TextView chassis=holder.chassis;

        model.setText(dataSet.get(listPosition).getModel());
        number.setText(dataSet.get(listPosition).getNumber());
        engine.setText(dataSet.get(listPosition).getEngine());
        chassis.setText(dataSet.get(listPosition).getChassis());


    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}