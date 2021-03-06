package com.cykj.survey.fragment.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cykj.survey.R;
import com.cykj.survey.activity.AccidentActivity;
import com.cykj.survey.model.Options;

import java.util.List;

public class OptionAdapter  extends RecyclerView.Adapter<OptionAdapter.MyViewHolder>{

    private List<Options> mDatas;
    private Context mContext;
    private LayoutInflater inflater;
    private SparseBooleanArray yesCheckStates = new SparseBooleanArray();
    private SparseBooleanArray noCheckStates = new SparseBooleanArray();

    public OptionAdapter(Context mContext,List<Options> mDatas){
        this.mContext = mContext;
        this.mDatas = mDatas;
        inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public OptionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.layout_options_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        if (mDatas.get(position).getImageId() == 0){
            holder.imageView.setVisibility(View.GONE);
        }else {
            final Dialog dia;
            dia = new Dialog(mContext,R.style.edit_AlertDialog_style);
            dia.setContentView(R.layout.layout_dialog);
            ImageView imageView = dia.findViewById(R.id.dialog_img);
            imageView.setImageResource(mDatas.get(position).getImageId());
            dia.setCanceledOnTouchOutside(true);
            Window w = dia.getWindow();
            WindowManager.LayoutParams lp = w.getAttributes();
            lp.x = 0;
            lp.y = 40;
            dia.onWindowAttributesChanged(lp);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dia.show();
                }
            });
        }

        holder.checkBoxYes.setTag(position);
        holder.checkBoxNo.setTag(position);
        holder.tv.setText(mContext.getString(mDatas.get(position).getName()));
        holder.checkBoxYes.setOnCheckedChangeListener(null);
        holder.checkBoxNo.setOnCheckedChangeListener(null);

        holder.checkBoxYes.setChecked(yesCheckStates.get(position,false));
        holder.checkBoxNo.setChecked(noCheckStates.get(position,false));

        holder.checkBoxYes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int index = (int) holder.checkBoxYes.getTag();
                if (index == -2){
                    return;
                }
                if (holder.checkBoxNo.isChecked() && holder.checkBoxYes.isChecked()){
                    holder.checkBoxNo.setChecked(false);
                    noCheckStates.delete(position);
                }
                if (isChecked){
                    yesCheckStates.put(position,true);
                    Toast.makeText(mContext,"你勾选了是",Toast.LENGTH_LONG).show();
                }
            }
        });

        holder.checkBoxNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int index = (int) holder.checkBoxNo.getTag();
                if (index == -2){
                    return;
                }
                if (holder.checkBoxNo.isChecked() && holder.checkBoxYes.isChecked()){
                    holder.checkBoxYes.setChecked(false);
                    yesCheckStates.delete(position);
                }
                if (isChecked){
                    noCheckStates.put(position,true);
                    Intent intent = new Intent(mContext, AccidentActivity.class);
                    mContext.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onViewRecycled(@NonNull OptionAdapter.MyViewHolder holder) {
        CheckBox checkBoxYes = holder.checkBoxYes;
        CheckBox checkBoxNo = holder.checkBoxNo;
        checkBoxYes.setTag(-2);
        checkBoxNo.setTag(-2);
        super.onViewRecycled(holder);

    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv;
        CheckBox checkBoxYes;
        CheckBox checkBoxNo;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.options_name);
            checkBoxYes = itemView.findViewById(R.id.optins_checkbox_yes);
            checkBoxNo = itemView.findViewById(R.id.optins_checkbox_no);
            imageView = itemView.findViewById(R.id.options_tip);
        }
    }

}
