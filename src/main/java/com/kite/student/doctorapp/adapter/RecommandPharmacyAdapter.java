package com.kite.student.doctorapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kite.student.doctorapp.PharmacyConfirmActivity;
import com.kite.student.doctorapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecommandPharmacyAdapter extends RecyclerView.Adapter<RecommandPharmacyAdapter.PharmacyViewHolder> {

    public Context context;
    public RecommandPharmacyAdapter(Context context){
        this.context = context;

    }
    @NonNull
    @Override
    public PharmacyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pharmacy_list,parent,false);
        return new PharmacyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PharmacyViewHolder holder, int position) {
        holder.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,PharmacyConfirmActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class PharmacyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.book)
        Button book;

        public PharmacyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
