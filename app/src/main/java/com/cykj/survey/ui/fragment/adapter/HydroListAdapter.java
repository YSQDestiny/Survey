package com.cykj.survey.ui.fragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cykj.survey.R;
import com.cykj.survey.model.Hydro;
import com.cykj.survey.util.DateUtil;

import java.util.List;

public class HydroListAdapter extends RecyclerView.Adapter<HydroListAdapter.MyViewHolder> {

    private List<Hydro> data;
    private Context context;
    private LayoutInflater inflater;

    public HydroListAdapter(Context context,List<Hydro> data){
        this.data = data;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_property_list_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.name.setText(data.get(position).getName());
        holder.time.setText(DateUtil.parseToString(data.get(position).getMakeTime(),DateUtil.yyyyMMddHHmmss));
        if (mOnItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onLongClick(position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener{
        void onClick(int position);
        void onLongClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this. mOnItemClickListener=onItemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView time;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.property_list_name);
            time = itemView.findViewById(R.id.property_list_time);
        }
    }
}
