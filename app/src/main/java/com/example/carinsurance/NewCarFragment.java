package com.example.carinsurance;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.carinsurance.Models.Car;
import com.example.carinsurance.Models.Transactions;
import com.google.android.gms.auth.api.Auth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;


public class NewCarFragment extends Fragment {

    View rootView;
    EditText company,model,vnum,eno,cno,bought;
    Button button;
    AuthBottomFragment i;
    Bundle car;
    long code;


    public NewCarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_new_car, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewSetter();
    }


    final Calendar myCalendar = Calendar.getInstance();

    void viewSetter() {
        company = (EditText) rootView.findViewById(R.id.company);
        model = (EditText) rootView.findViewById(R.id.model_name);
        vnum = (EditText) rootView.findViewById(R.id.veNum);
        eno = (EditText) rootView.findViewById(R.id.eno);
        cno = (EditText) rootView.findViewById(R.id.cno);
        bought = (EditText) rootView.findViewById(R.id.car_bought);

        i=new AuthBottomFragment();

        car=new Bundle();
        car.putString("company",company.getText().toString());
        car.putString("model",model.getText().toString());
        car.putString("vehicleNum",vnum.getText().toString());
        car.putString("engine",eno.getText().toString());
        car.putString("chassis",cno.getText().toString());
        car.putString("bought",bought.getText().toString());


        final DatePickerDialog.OnDateSetListener dates = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        bought.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                new DatePickerDialog(getContext(), dates, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        button=(Button)rootView.findViewById(R.id.new_car_btn);
        

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                code=(Math.round(Math.random()*1000000)+1);
                car.putLong("code",code);
                Intent email = new Intent(Intent.ACTION_SEND);
                String receiver=getActivity().getSharedPreferences("user",MODE_PRIVATE).getString("username","DEFAULT");
                String name=getActivity().getSharedPreferences("user",MODE_PRIVATE).getString("name","DEFAULT");
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{receiver});
                email.putExtra(Intent.EXTRA_SUBJECT, "Verify your new Car");
                email.putExtra(Intent.EXTRA_TEXT, "Dear "+name+",\nThe Authentication ID for your new car is: \n"+ Html.fromHtml("<b>"+(Math.round(Math.random()*1000000)+1)+"</b>")+"\n\nPlease enter this number in the application");

                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));

                AuthBottomFragment authBottomFragment =
                        AuthBottomFragment.newInstance();
                authBottomFragment.show(((AppCompatActivity)getContext()).getSupportFragmentManager(),
                        "dialog_fragment");

            }
        });


        i.setArguments(car);
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        bought.setText(sdf.format(myCalendar.getTime()));
    }


}


