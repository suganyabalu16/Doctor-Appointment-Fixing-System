package com.kite.student.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import com.kite.student.doctorapp.service.APIService;
import com.kite.student.doctorapp.service.RetrofitInstance;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("ALL")
public class PatientRegisterActivity extends AppCompatActivity {

    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.city)
    EditText city;
    @BindView(R.id.address)
    EditText address;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.register)
    public void register(){
        onRegisterRequest(name.getText().toString(),email.getText().toString(),password.getText().toString(),city.getText().toString(),address.getText().toString());
    }

    private void onRegisterRequest(String name, String email, String password, String city, String address) {
        APIService service = RetrofitInstance.getRetrofit().create(APIService.class);
        Call<ResponseBody> call = service.register(name, email, password, city, address);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
