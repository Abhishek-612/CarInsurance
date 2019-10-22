package com.example.carinsurance;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class BottomFragment extends BottomSheetDialogFragment {


    TextView company,model,veh,eno,cno,bought,claims;
    Button insurance;

    public static BottomFragment newInstance() {
        return new BottomFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bottom_sheet_layout, container,
                false);

        // get the views and attach the listener

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        company=(TextView)view.findViewById(R.id.company_name);
        model=(TextView)view.findViewById(R.id.car_name);
        veh=(TextView)view.findViewById(R.id.veh_no);
        eno=(TextView)view.findViewById(R.id.eng_no);
        cno=(TextView)view.findViewById(R.id.chas_no);
        bought=(TextView)view.findViewById(R.id.purchase_date);
        claims=(TextView)view.findViewById(R.id.claims);

        insurance=(Button)view.findViewById(R.id.check);


        //TODO: Get Car details
        setCarDetails("Maruti","Swift","MH02CV7175","DNIECV","BFEIUB","21/1/99",0);

        insurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AppCompatActivity)getContext()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new InsuranceFragment())
                        .commit();

                dismiss();
            }
        });
    }

    private void setCarDetails(String company_str,String model_str,String vehice_no_str,String engine_str,String chassis_str,String bought_str,int claims_str){
        company.setText("Company: "+company_str);
        model.setText("Model: "+model_str);
        veh.setText("Vehicle No.: "+vehice_no_str);
        eno.setText("Engine No.: "+engine_str);
        cno.setText("Chassis No.: "+chassis_str);
        bought.setText("Bought on: "+bought_str);
        claims.setText("No. of claims: "+claims_str);
    }


}