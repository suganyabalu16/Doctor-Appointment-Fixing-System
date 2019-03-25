package com.kite.student.doctorapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.kite.student.doctorapp.service.APIService;
import com.kite.student.doctorapp.service.RetrofitInstance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    @BindView(R.id.spinner)
    Spinner spinner;
    String type = "doctor";
    EditText usr,pas;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        usr = (EditText) findViewById(R.id.textview);
        pas = (EditText) findViewById(R.id.textview1);


        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Doctor");
        categories.add("Patient");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    @OnClick(R.id.register)
    public void onRegister() {
        onSelectRegister();
    }

    @OnClick(R.id.login)
    public void onLogin() {
        if (type.equals("doctor")) {
            String Username = usr.getText().toString();
            String Password = pas.getText().toString();
             onLoginRequest(Username,Password,type);

        } else {
            String Username = usr.getText().toString();
            String Password = pas.getText().toString();
            onLoginRequest(Username,Password,type);

        }
    }


    public void onSelectRegister() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("Choose an registration ");

// add a list
        String[] animals = {"Doctor", "patient"};
        builder.setItems(animals, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                        startActivity(intent);
                        dialog.dismiss();
                        break;
                    case 1:
                        Intent intentTwo = new Intent(LoginActivity.this, MapsActivity.class);
                        startActivity(intentTwo);
                        dialog.dismiss();
                        break;
                }
            }
        });

// create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        if (position == 0) {
            type = "doctor";
        } else {
            type = "patient";
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }




    public void onLoginRequest(String username, String password, final String type){
        APIService apiService = RetrofitInstance.getRetrofit().create(APIService.class);
        Call<ResponseBody> call = apiService.login(username, password,type);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        if (response.body().string().equals("login successful...!")) {
                            if (type.equals("doctor")) {
                                Intent intent = new Intent(getApplicationContext(),PatientListActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
