package com.kite.student.doctorapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.kite.student.doctorapp.DoctorConfirmActivity;
import com.kite.student.doctorapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;
public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.DoctorListViewHolder> {

    private Context context;
    public DoctorListAdapter(Context context){
        this.context = context;

    }
    @NonNull
    @Override
    public DoctorListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_doctor_list,parent,false);
        return new DoctorListViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull DoctorListViewHolder holder, int position) {
        holder.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DoctorConfirmActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class DoctorListViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.book)
        Button book;

        public DoctorListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
