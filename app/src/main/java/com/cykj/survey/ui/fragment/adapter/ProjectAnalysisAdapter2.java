package com.cykj.survey.ui.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.cykj.survey.R;

import java.util.ArrayList;
import java.util.List;

public class ProjectAnalysisAdapter2 extends BaseAdapter {

    private List<String> mData;
    private Context context;
    private LayoutInflater inflater;
    public List<String> checkData = new ArrayList<>();

    public ProjectAnalysisAdapter2(Context context,List<String> mData){
        this.context = context;
        this.mData = mData;
        inflater = LayoutInflater.from(context);
        init();
    }

    private void init(){
        checkData.clear();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.layout_project_analysis_list_2_item,null);
            holder.name = convertView.findViewById(R.id.project_analysis_2_item_name);
            holder.checkBox = convertView.findViewById(R.id.project_analysis_2_item_checkbox);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(mData.get(position));
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    checkData.add(mData.get(position));
                }else {
                    checkData.remove(mData.get(position));
        }
    }
        });
        return convertView;
    }

    private final class ViewHolder{
        TextView name;
        CheckBox checkBox;
    }

}
