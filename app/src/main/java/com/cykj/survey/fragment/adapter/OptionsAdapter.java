package com.cykj.survey.fragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cykj.survey.R;

import java.util.List;

public class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.MyViewHolder>{

    private List<String> mDatas;
    private Context mContext;
    private LayoutInflater inflater;
    private boolean oneChecked = false;

    public OptionsAdapter(Context mContext,List<String> mDatas){
        this.mContext = mContext;
        this.mDatas = mDatas;
        inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.layout_options_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.tv.setText(mDatas.get(position));
        holder.checkBoxYes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (holder.checkBoxNo.isChecked() && holder.checkBoxYes.isChecked()){
                    holder.checkBoxNo.setChecked(false);
                }
                if (isChecked){
                    Toast.makeText(mContext,"你勾选了是",Toast.LENGTH_LONG).show();
                }
            }
        });

        holder.checkBoxNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (holder.checkBoxNo.isChecked() && holder.checkBoxYes.isChecked()){
                    holder.checkBoxYes.setChecked(false);
                }
                if (isChecked){
                    Toast.makeText(mContext,"你勾选了否",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv;
        CheckBox checkBoxYes;
        CheckBox checkBoxNo;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.options_name);
            checkBoxYes = itemView.findViewById(R.id.optins_checkbox_yes);
            checkBoxNo = itemView.findViewById(R.id.optins_checkbox_no);
        }
    }
}
