package com.cykj.survey.ui.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.cykj.survey.R;
import com.cykj.survey.model.Disaster;
import com.cykj.survey.model.ProjectConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectAnalysisAdapter extends BaseAdapter {

    private Context mContext;
    private List<Disaster> mData;
    private LayoutInflater inflater;
    public Map<String,String> map = new HashMap<>();

    public ProjectAnalysisAdapter(Context mContext,List<Disaster> mData){
        this.mContext = mContext;
        this.mData = mData;
        inflater = LayoutInflater.from(mContext);
        init();
    }

    private void init(){
        map.clear();
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
            convertView = inflater.inflate(R.layout.layout_project_analysis_item,null);
            holder.name = convertView.findViewById(R.id.project_analysis_item_name);
            holder.spinner = convertView.findViewById(R.id.project_analysis_item_spinner);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(mData.get(position).getHiddenDanger());
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(mContext,android.R.layout.simple_spinner_item, ProjectConstants.ANALYSIS_SPINNER_ITEM);
        spinnerSetAdapter(arrayAdapter,holder.spinner);
        holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position1, long id) {
                if (map.containsKey(map.containsKey(mData.get(position).getHiddenDanger()))){
                    map.put(mData.get(position).getHiddenDanger(),ProjectConstants.ANALYSIS_SPINNER_ITEM.get(position1));
                }else {
                    map.put(mData.get(position).getHiddenDanger(),ProjectConstants.ANALYSIS_SPINNER_ITEM.get(position1));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return convertView;
    }

    private void spinnerSetAdapter(ArrayAdapter<String> arrayAdapter, Spinner spinner) {
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    private final class ViewHolder {
        TextView name;
        Spinner spinner;
    }
}
