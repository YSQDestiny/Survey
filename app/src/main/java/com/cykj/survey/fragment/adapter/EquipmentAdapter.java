package com.cykj.survey.fragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cykj.survey.R;
import com.cykj.survey.model.Equipment;

import java.util.List;

public class EquipmentAdapter  extends RecyclerView.Adapter<EquipmentAdapter.MyViewHolder>{

    private List<Equipment> data;
    private Context context;
    private LayoutInflater inflater;

    public EquipmentAdapter(Context context,List<Equipment> data){
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_equipment_item,null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(data.get(position).getName());
        holder.type.setText(data.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView type;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.equipment_name);
            type = itemView.findViewById(R.id.equipment_type);
        }
    }
}
