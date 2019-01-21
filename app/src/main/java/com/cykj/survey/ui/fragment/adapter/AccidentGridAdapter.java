package com.cykj.survey.ui.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cykj.survey.R;
import com.cykj.survey.model.AccidentGridModel;

import java.util.List;

public class AccidentGridAdapter extends BaseAdapter{

    private Context mContext;

    private List<AccidentGridModel> data;

    public AccidentGridAdapter(Context context, List<AccidentGridModel> data){
        mContext = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.gridview_accident_item,null);
        RelativeLayout relativeLayout = convertView.findViewById(R.id.accident_grid_layout);
        TextView tx = convertView.findViewById(R.id.accident_grid_text);
        tx.setText(data.get(position).getName());
        if (data.get(position).isSelect()){
            relativeLayout.setBackgroundResource(R.drawable.grid_shape_selected);
            tx.setTextColor(mContext.getResources().getColor(R.color.grid_text_selected));
        }else {
            relativeLayout.setBackgroundResource(R.drawable.grid_shape_normal);
            tx.setTextColor(mContext.getResources().getColor(R.color.grid_text_normal));
        }
        return convertView;
    }
}
