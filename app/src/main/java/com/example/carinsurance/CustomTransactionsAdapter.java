package com.example.carinsurance;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.carinsurance.Models.Transactions;

import java.util.ArrayList;
import java.util.List;

public class CustomTransactionsAdapter extends RecyclerView.Adapter<CustomTransactionsAdapter.MyViewHolder> {

    private ArrayList<Transactions> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tr_id,status,vehicle_num,date;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tr_id = (TextView) itemView.findViewById(R.id.premium_id);
            this.status = (TextView) itemView.findViewById(R.id.status_trans);
            this.vehicle_num = (TextView) itemView.findViewById(R.id.car_trans);
            this.date = (TextView) itemView.findViewById(R.id.date_trans);

        }
    }

    public CustomTransactionsAdapter(ArrayList<Transactions> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trans_card_layout, parent, false);


        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        TextView tr_id=myViewHolder.tr_id;
        TextView date=myViewHolder.date;
        TextView status=myViewHolder.status;
        TextView vehicle_num=myViewHolder.vehicle_num;

        tr_id.setText(""+dataSet.get(i).getPremiumId());
        date.setText(dataSet.get(i).getTime());
        status.setText(dataSet.get(i).getStatus());
        vehicle_num.setText(dataSet.get(i).getCar());
    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
