package com.example.carinsurance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewCarActivity extends AppCompatActivity {

    EditText company,model,eno,cno,authID;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_car);

        company = (EditText) findViewById(R.id.company);
        model = (EditText) findViewById(R.id.model_name);
        eno = (EditText) findViewById(R.id.eno);
        cno = (EditText) findViewById(R.id.cno);
        authID = (EditText) findViewById(R.id.authID);

        button=(Button) findViewById(R.id.new_car_btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getStrings();
            }
        });
    }

    private void getStrings(){
        String company_str,model_str,eno_str,cno_str,authID_str;
        company_str=company.getText().toString();
        model_str=model.getText().toString();
        eno_str=eno.getText().toString();
        cno_str=cno.getText().toString();
        authID_str=authID.getText().toString();

        //GOTO:Update to database if authID is correct
        if(authID_str.length()!=0){
            finish();
        }
    }
}
