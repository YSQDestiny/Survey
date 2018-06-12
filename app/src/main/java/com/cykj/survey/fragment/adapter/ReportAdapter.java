package com.cykj.survey.fragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cykj.survey.R;
import com.cykj.survey.model.Company;
import com.cykj.survey.util.DateUtil;

import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.MyViewHolder>{

    private List<Company> companies;
    private Context context;
    private LayoutInflater inflater;

    public ReportAdapter(Context context,List<Company> companies){
        this.context = context;
        this.companies = companies;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_report_list_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.name.setText(companies.get(position).getName());
        holder.industry.setText(companies.get(position).getIndustry());
        holder.time.setText(DateUtil.parseToString(companies.get(position).getMakeTime(),DateUtil.yyyyMMddHHmmss));
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
        return companies.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView time;
        TextView industry;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.report_name);
            time = itemView.findViewById(R.id.report_time);
            industry = itemView.findViewById(R.id.report_industry);
        }
    }

    OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener{
        void onClick( int position);
        void onLongClick( int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this. mOnItemClickListener=onItemClickListener;
    }
}
