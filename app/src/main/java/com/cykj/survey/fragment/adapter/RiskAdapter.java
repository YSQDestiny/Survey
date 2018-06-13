package com.cykj.survey.fragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cykj.survey.R;
import com.cykj.survey.model.Accident;
import com.cykj.survey.util.ImgUtil;

import java.util.List;

public class RiskAdapter extends RecyclerView.Adapter<RiskAdapter.MyViewHolder>{

    private List<Accident> accidents;
    private Context context;
    private LayoutInflater inflater;

    public RiskAdapter(Context context,List<Accident> accidents){
        this.context = context;
        this.accidents = accidents;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_report_details_risk_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.instructions.setText(accidents.get(position).getInstructions());
        holder.type.setText(accidents.get(position).getType());
        holder.result.setText(accidents.get(position).getResult());
        holder.siteImg.setImageBitmap(ImgUtil.base64ToBitmap(accidents.get(position).getSitePhoto()));
        holder.surroundingsImg.setImageBitmap(ImgUtil.base64ToBitmap(accidents.get(position).getSurroundingsPhoto()));
    }

    @Override
    public int getItemCount() {
        return accidents.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView instructions;
        TextView type;
        TextView result;
        ImageView siteImg;
        ImageView surroundingsImg;

        public MyViewHolder(View itemView) {
            super(itemView);
            instructions = itemView.findViewById(R.id.risk_instructions);
            type = itemView.findViewById(R.id.risk_type);
            result = itemView.findViewById(R.id.risk_result);
            siteImg = itemView.findViewById(R.id.risk_site_img);
            surroundingsImg = itemView.findViewById(R.id.risk_surround_img);
        }
    }
}
