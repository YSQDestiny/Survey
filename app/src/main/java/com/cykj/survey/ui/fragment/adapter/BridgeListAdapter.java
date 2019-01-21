package com.cykj.survey.ui.fragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cykj.survey.R;
import com.cykj.survey.model.Bridge;

import java.util.List;

public class BridgeListAdapter extends RecyclerView.Adapter<BridgeListAdapter.MyViewHolder>{

    private List<Bridge> mDatas;
    private Context mContext;
    private LayoutInflater inflater;

    public BridgeListAdapter(Context mContext,List<Bridge> mDatas){
        this.mDatas = mDatas;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_bridge_list_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bridgeName.setText(mDatas.get(position).getBridgeName());
        holder.stationNumber.setText(mDatas.get(position).getStationNumber());
        holder.length.setText(Double.toString(mDatas.get(position).getLength()) + "m");
        holder.porespanType.setText(mDatas.get(position).getPorespanType());
        holder.type.setText(mDatas.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView bridgeName;
        TextView stationNumber;
        TextView length;
        TextView porespanType;
        TextView type;

        public MyViewHolder(View itemView) {
            super(itemView);
            bridgeName = itemView.findViewById(R.id.item_bridge_name);
            stationNumber = itemView.findViewById(R.id.tiem_bridge_stationNumber);
            length = itemView.findViewById(R.id.tiem_bridge_length);
            porespanType = itemView.findViewById(R.id.tiem_bridge_porespanType);
            type = itemView.findViewById(R.id.item_bridge_type);
        }
    }
}
