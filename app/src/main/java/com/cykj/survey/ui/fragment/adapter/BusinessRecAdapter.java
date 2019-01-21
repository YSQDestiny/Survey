package com.cykj.survey.ui.fragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cykj.survey.R;
import com.cykj.survey.model.Record;

import java.util.List;

public class BusinessRecAdapter extends RecyclerView.Adapter<BusinessRecAdapter.MyViewHolder>{

    private List<Record> records;
    private Context context;
    private LayoutInflater inflater;

    public BusinessRecAdapter(Context context,List<Record> records){
        this.context = context;
        this.records = records;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_business_rec_item,null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.time.setText(records.get(position).getTime());
        holder.reson.setText(records.get(position).getReason());
        holder.amount.setText(records.get(position).getAmount());
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView time;
        TextView amount;
        TextView reson;

        public MyViewHolder(View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.rec_time);
            amount = itemView.findViewById(R.id.rec_amount);
            reson = itemView.findViewById(R.id.rec_reson);
        }
    }
}
