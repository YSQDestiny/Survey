package com.cykj.survey.fragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cykj.survey.R;
import com.cykj.survey.model.Crew;

import java.util.List;

public class CrewrecAdapter extends RecyclerView.Adapter<CrewrecAdapter.MyviewHolder>{

    private List<Crew> mData;
    private Context mContext;
    private LayoutInflater inflater;

    public CrewrecAdapter(Context mContext,List<Crew> mData){
        this.mContext = mContext;
        this.mData = mData;
        inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_crew_rec_item,parent,false);
        MyviewHolder holder = new MyviewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        holder.power.setText(Integer.toString(mData.get(position).getPower()));
        holder.count.setText(Integer.toString(mData.get(position).getCount()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyviewHolder extends RecyclerView.ViewHolder{

        TextView power;
        TextView count;

        public MyviewHolder(View itemView) {
            super(itemView);
            power = itemView.findViewById(R.id.crewrec_power);
            count = itemView.findViewById(R.id.crewrec_count);
        }
    }
}

