package com.kite.student.doctorapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kite.student.doctorapp.PrecscriptionActivity;
import com.kite.student.doctorapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.PatientListViewHolder> {

    private Context context;
    public PatientListAdapter(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public PatientListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_patient_list,parent,false);
        return new PatientListViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull PatientListViewHolder holder, int position) {
        holder.report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,PrecscriptionActivity.class);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return 10;
    }

    class PatientListViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.report)
        Button report;
        public PatientListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
