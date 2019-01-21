package com.cykj.survey.ui.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cykj.survey.R;
import com.cykj.survey.model.PropertyOption;

import java.util.List;

public class PropertyOptionListAdapter extends BaseAdapter {

    private List<PropertyOption> data;
    private Context context;

    public PropertyOptionListAdapter(Context context,List<PropertyOption> data){
        this.context = context;
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
        convertView = LayoutInflater.from(context).inflate(R.layout.layout_property_option_list_item,null);
        TextView name = convertView.findViewById(R.id.option_list_name);
        name.setText(data.get(position).getName());
        return convertView;
    }
}
