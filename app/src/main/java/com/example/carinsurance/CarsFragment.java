package com.example.carinsurance;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class CarsFragment extends Fragment {

    View rootView;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Car> carsArray;
    static View.OnClickListener myOnClickListener;

    public CarsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_cars, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewSetter();

    }

    void viewSetter(){
        getCars();
        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                startActivity(new Intent(getActivity(),NewCarActivity.class));
            }
        });

        myOnClickListener = new MyOnClickListener(getContext());

        recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        carsArray = new ArrayList<Car>();

        carsArray.add(new Car("Maruti Swift","MH02CV7175","NEYF32SVN","H3GIS8BEU"));

        adapter = new CustomAdapter(carsArray);
        recyclerView.setAdapter(adapter);

    }

    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            int selectedItemPosition = recyclerView.getChildPosition(v);
            RecyclerView.ViewHolder viewHolder
                    = recyclerView.findViewHolderForPosition(selectedItemPosition);
            TextView model
                    = (TextView) viewHolder.itemView.findViewById(R.id.carName);
            String selectedName = (String) model.getText();
//            Toast.makeText(context, selectedName, Toast.LENGTH_SHORT).show();
            BottomFragment addBottomDialogFragment =
                    BottomFragment.newInstance();
            addBottomDialogFragment.show(((AppCompatActivity)context).getSupportFragmentManager(),
                    "add_dialog_fragment");
        }


    }

    void getCars(){
        final ProgressDialog dialog = ProgressDialog.show(getContext(),"Loading carsArray","Please wait while we load your carsArray",false);
        dialog.setCancelable(false);
        String userName = getActivity().getSharedPreferences("user",Context.MODE_PRIVATE).getString("username","kds");
        VolleyHelper helper = new VolleyHelper(getContext());
        HashMap<String,Object> data = new HashMap<>();
        data.put("customer",userName);
        helper.callApi("androidApi/getCars", data, new VolleyHelper.VolleyCallBack() {
            @Override
            public void data(JSONObject data, String error) {
                dialog.dismiss();
                if(data != null){
                    try {
                        JSONArray array = new JSONArray(data.getString("result"));
                        carsArray.clear();
                        for(int i =0;i<array.length();i++){
                            carsArray.add(Car.fromJson(array.getJSONObject(i)));
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
