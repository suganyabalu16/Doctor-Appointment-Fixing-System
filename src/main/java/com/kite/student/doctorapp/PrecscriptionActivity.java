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
public class PrecscriptionActivity extends AppCompatActivity {
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.age)
    EditText age;
    @BindView(R.id.healthissue)
    EditText healthissue;
    @BindView(R.id.healthdetail)
    EditText healthdetail;
    @BindView(R.id.validitydate)
    EditText validitydate;
    @BindView(R.id.prescription)
    EditText prescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_prescription);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.send)
    public void register(){
        onRegisterRequest(name.getText().toString(),age.getText().toString(),healthissue.getText().toString(),healthdetail.getText().toString(),prescription.getText().toString(),validitydate.getText().toString());
    }

    private void onRegisterRequest(String name, String age, String healthissue, String healthdetail, String prescription,String validitydate) {
        APIService service = RetrofitInstance.getRetrofit().create(APIService.class);
        Call<ResponseBody> call = service.patientRegister(name, age, healthissue, healthdetail, prescription);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(), RecommandPharmacy.class);
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
