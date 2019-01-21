package com.cykj.survey.ui.fragment.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cykj.survey.R;
import com.cykj.survey.model.WeatherInfo;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class WeatherRecAdapter extends RecyclerView.Adapter<WeatherRecAdapter.MyViewHolder>{

    private List<WeatherInfo> mData;
    private Context mContext;
    private LayoutInflater inflater;
    private String[] mMonths = new String[]{
            "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"
    };

    public WeatherRecAdapter(Context mContext,List<WeatherInfo> mData){
        this.mContext = mContext;
        this.mData = mData;
        inflater = LayoutInflater.from(mContext);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_weather_chart_rec_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(mData.get(position).getName());
        CombinedChart mChart = holder.mChart;
        mChart.getDescription().setEnabled(false);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);
        mChart.setHighlightFullBarEnabled(false);

        mChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.BUBBLE, CombinedChart.DrawOrder.CANDLE, CombinedChart.DrawOrder.LINE, CombinedChart.DrawOrder.SCATTER,
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.LINE
        });

        Legend l = mChart.getLegend();
        l.setWordWrapEnabled(true);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mMonths[(int) value % mMonths.length];
            }
        });

        CombinedData data = new CombinedData();
        data.setData(generateLineData(position));
        data.setData(gengrateBarData(position));
        xAxis.setAxisMaximum(data.getXMax() + 0.25f);

        mChart.setData(data);
        mChart.invalidate();
    }

    private LineData generateLineData(int position){

        WeatherInfo weatherInfo = mData.get(position);

        ArrayList<Entry> entries = new ArrayList<>();
        String[] highWeather = weatherInfo.getHighWeather().split(",");
        for (int i = 0;i < highWeather.length;i++){
            entries.add(new Entry(i,Float.parseFloat(highWeather[i])));
        }


        ArrayList<Entry> entries1 = new ArrayList<>();
        String[] lowWeather = weatherInfo.getLowWeather().split(",");
        for (int i = 0;i < lowWeather.length;i++){
            entries1.add(new Entry(i,Float.parseFloat(lowWeather[i])));
        }

        LineDataSet set = new LineDataSet(entries, "最高气温");
        set.setColor(Color.rgb(255, 0, 0));
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.rgb(255, 0, 0));
        set.setCircleRadius(5f);
        set.setFillColor(Color.rgb(255, 0, 0));
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.rgb(255, 0, 0));
        set.setAxisDependency(YAxis.AxisDependency.LEFT);

        LineDataSet set1 = new LineDataSet(entries1, "最低气温");
        set1.setColor(Color.rgb(0, 0, 255));
        set1.setLineWidth(2.5f);
        set1.setCircleColor(Color.rgb(0, 0, 255));
        set1.setCircleRadius(5f);
        set1.setFillColor(Color.rgb(0, 0, 255));
        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set1.setDrawValues(true);
        set1.setValueTextSize(10f);
        set1.setValueTextColor(Color.rgb(0, 0, 255));
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);

        LineData d = new LineData(set,set1);

        return d;

    }

    private BarData gengrateBarData(int position){

        WeatherInfo weatherInfo = mData.get(position);

        ArrayList<BarEntry> entries = new ArrayList<>();
        String[] rainfull = weatherInfo.getRainfall().split(",");
        for (int i = 0; i < rainfull.length;i++){
            entries.add(new BarEntry(i,Float.parseFloat(rainfull[i])));
        }

        BarDataSet set1 = new BarDataSet(entries, "降雨量");
        set1.setColor(Color.rgb(60, 220, 78));
        set1.setValueTextColor(Color.rgb(0, 255, 0));
        set1.setValueTextSize(10f);
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);

        float groupSpace = 0.06f;
        float barSpace = 0.02f; // x2 dataset
        float barWidth = 0.45f; // x2 dataset
        // (0.45 + 0.02) * 2 + 0.06 = 1.00 -> interval per "group"

        BarData d = new BarData(set1);
        d.setBarWidth(barWidth);

        return d;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        CombinedChart mChart;
        TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);
            mChart = itemView.findViewById(R.id.char_rec_weather_chart);
            name = itemView.findViewById(R.id.char_rec_name);
        }


    }
}
