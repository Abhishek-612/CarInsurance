package com.example.carinsurance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carinsurance.Models.Customer;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    Button procced;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        procced = findViewById(R.id.proceed);
        procced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer c = new Customer();
                c.username = username.getText().toString();
                c.password = password.getText().toString();
                c.verifyUser(LoginActivity.this, new VolleyHelper.VolleyCallBack() {
                    @Override
                    public void data(JSONObject data, String error) {
                        Customer customer = Customer.getObject(data);
                        if(customer == null){
                            Toast.makeText(LoginActivity.this, "Error getting details", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(LoginActivity.this, "Welcome "+customer.name, Toast.LENGTH_LONG).show();
                            saveUser(customer);
                            finish();
                        }
                    }
                });
            }
        });


    }

    private void saveUser(Customer customer) {
        getSharedPreferences("user",MODE_PRIVATE).edit().putString("username",customer.username)
                .putString("phoneNum",customer.phoneNum).putInt("ownedCars",customer.ownedCars)
                .putString("name",customer.name).apply();
    }


}
