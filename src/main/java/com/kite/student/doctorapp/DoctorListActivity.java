package com.kite.student.doctorapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import com.kite.student.doctorapp.adapter.DoctorListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DoctorListActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_doctor_list);
        ButterKnife.bind(this);
        DoctorListAdapter doctorListAdapter = new DoctorListAdapter(DoctorListActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayout.VERTICAL,false));
        recyclerView.setAdapter(doctorListAdapter);
    }
}
