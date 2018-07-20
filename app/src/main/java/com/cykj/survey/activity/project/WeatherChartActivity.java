package com.cykj.survey.activity.project;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragmentActivity;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.CombinedChart.DrawOrder;
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
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherChartActivity extends BaseFragmentActivity {

    private String[] mMonths = new String[]{
            "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"
    };

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.weather_chart)
    CombinedChart mChart;

    @Override
    protected int getContextViewId() {
        return R.id.survey;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_chart);
        ButterKnife.bind(this);
        initTopbar();
        mChart.getDescription().setEnabled(false);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);
        mChart.setHighlightFullBarEnabled(false);

        mChart.setDrawOrder(new DrawOrder[]{
//                DrawOrder.BAR, DrawOrder.BUBBLE, DrawOrder.CANDLE, DrawOrder.LINE, DrawOrder.SCATTER
                DrawOrder.BAR, DrawOrder.LINE
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
        data.setData(generateLineData());
        data.setData(gengrateBarData());
        xAxis.setAxisMaximum(data.getXMax() + 0.25f);

        mChart.setData(data);
        mChart.invalidate();
    }

    private void initTopbar(){
        topbar.setTitle("历史气温");
        topbar.addRightTextButton("下一步",R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeatherChartActivity.this,ProjectAccidentActivity.class);
                startActivity(intent);
            }
        });
    }

    private LineData generateLineData(){

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0f,9.51f));
        entries.add(new Entry(1f,11.52f));
        entries.add(new Entry(2f,16.4f));
        entries.add(new Entry(3f,21.86f));
        entries.add(new Entry(4f,25.99f));
        entries.add(new Entry(5f,28f));
        entries.add(new Entry(6f,29.83f));
        entries.add(new Entry(7f,29.74f));
        entries.add(new Entry(8f,25.44f));
        entries.add(new Entry(9f,20.6f));
        entries.add(new Entry(10f,15.82f));
        entries.add(new Entry(11f,10.82f));

        ArrayList<Entry> entries1 = new ArrayList<>();
        entries1.add(new Entry(0f,2.71f));
        entries1.add(new Entry(1f,4.79f));
        entries1.add(new Entry(2f,8.5f));
        entries1.add(new Entry(3f,13.08f));
        entries1.add(new Entry(4f,17.31f));
        entries1.add(new Entry(5f,20.44f));
        entries1.add(new Entry(6f,22.25f));
        entries1.add(new Entry(7f,21.7f));
        entries1.add(new Entry(8f,18.66f));
        entries1.add(new Entry(9f,14.53f));
        entries1.add(new Entry(10f,9.52f));
        entries1.add(new Entry(11f,4.55f));

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

    private BarData gengrateBarData(){

        ArrayList<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(0f,6.67f));
        entries.add(new BarEntry(1f,11.28f));
        entries.add(new BarEntry(2f,20.21f));
        entries.add(new BarEntry(3f,47.23f));
        entries.add(new BarEntry(4f,82.03f));
        entries.add(new BarEntry(5f,110.24f));
        entries.add(new BarEntry(6f,222.59f));
        entries.add(new BarEntry(7f,227.42f));
        entries.add(new BarEntry(8f,115.18f));
        entries.add(new BarEntry(9f,40.12f));
        entries.add(new BarEntry(10f,15.9f));
        entries.add(new BarEntry(11f,5.55f));

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
}
