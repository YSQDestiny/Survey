package com.cykj.survey.fragment.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.cykj.survey.R;
import com.cykj.survey.model.AccidentGridModel;

import java.util.List;

public class ImageGridAdapter extends BaseAdapter {

    private Context mContext;

    private List<Bitmap> data;

    public ImageGridAdapter(Context mContext,List<Bitmap> data){
        this.mContext = mContext;
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

        convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_gridview_image_item,null);
        ImageView imageView = convertView.findViewById(R.id.geidview_image);
        imageView.setImageBitmap(data.get(position));

        return convertView;
    }

}
