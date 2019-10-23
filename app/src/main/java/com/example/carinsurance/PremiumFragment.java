package com.example.carinsurance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.carinsurance.Models.Insurance;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


public class PremiumFragment extends Fragment {

    View rootView;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Insurance> data;
    static View.OnClickListener myPremiumOnClickListener;
    private static final int REQ_UPIPAYMENT = 54;

    public PremiumFragment() {
        // Required empty public constructor
    }

    private void launchUPI(String amount){
        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", "prathmesh080@oksbi")
                .appendQueryParameter("pn", "Car insuarance")
                .appendQueryParameter("tn", "Pay for your car insuarance")
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                .build();

        Intent upiPayIntent = new Intent(Intent.ACTION_VIEW);
        upiPayIntent.setData(uri);

        Intent chooser = Intent.createChooser(upiPayIntent, "Pay with");

        if(null != chooser.resolveActivity(getContext().getPackageManager())) {
            Log.d(PremiumFragment.class.getSimpleName(), "UPI Payment resolved to activity");
            startActivityForResult(chooser, REQ_UPIPAYMENT);
        } else {
            Log.d(PremiumFragment.class.getSimpleName(), "No activity found to handle UPI Payment");
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_UPIPAYMENT){
            if(resultCode == RESULT_OK){
                Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                Log.d("data upi",data.toString());
            }else{
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_premium, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewSetter();
    }

    void viewSetter(){
//        myPremiumOnClickListener = new PremiumFragment.MyPremiumOnClickListener(getContext());

        recyclerView = (RecyclerView) rootView.findViewById(R.id.premium_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<Insurance>();

        data.add(new Insurance("Maruti Swift",20000,"12/02/89"));

        adapter = new CustomPremiumAdapter(data, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchUPI("5");
            }
        });
        recyclerView.setAdapter(adapter);

    }

    private static class MyPremiumOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyPremiumOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();

        }

//        private void removeItem(View v) {
//            int selectedItemPosition = recyclerView.getChildPosition(v);
//            RecyclerView.ViewHolder viewHolder
//                    = recyclerView.findViewHolderForPosition(selectedItemPosition);
//            TextView model
//                    = (TextView) viewHolder.itemView.findViewById(R.id.carName);
//            String selectedName = (String) model.getText();
//            int selectedItemId = -1;
//            removedItems.add(selectedItemId);
//            data.remove(selectedItemPosition);
//            adapter.notifyItemRemoved(selectedItemPosition);
//        }
    }



}
