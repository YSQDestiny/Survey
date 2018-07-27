package com.cykj.survey.activity.project;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.cykj.survey.Constants;
import com.cykj.survey.R;
import com.cykj.survey.base.BaseFragmentActivity;
import com.cykj.survey.fragment.adapter.ReportAdapter;
import com.cykj.survey.fragment.adapter.WeatherRecAdapter;
import com.cykj.survey.model.WeatherData;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherChartActivity extends BaseFragmentActivity {

    @BindView(R.id.char_rec)
    RecyclerView charRec;
    private String[] mMonths = new String[]{
            "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"
    };

    private List<WeatherData> weatherDataList = new ArrayList<>();

    @BindView(R.id.topbar)
    QMUITopBar topbar;


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
        initData();
        initView();

    }

    private void initData() {

        for (String addr : Constants.districtList) {

            try {
                getData(PinyinHelper.convertToPinyinString(addr, "", PinyinFormat.WITHOUT_TONE), addr);
            } catch (PinyinException e) {
                e.printStackTrace();
            }

        }

    }

    private WeatherRecAdapter adapter;
    private void initView() {
        adapter = new WeatherRecAdapter(this,weatherDataList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        charRec.setLayoutManager(layoutManager);
        charRec.setAdapter(adapter);
        charRec.setItemAnimator(new DefaultItemAnimator());
        charRec.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

    }

    private void initTopbar() {
        topbar.setTitle("历史气温");
        topbar.addRightTextButton("下一步", R.id.topbar_right_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeatherChartActivity.this, ProjectAccidentActivity.class);
                startActivity(intent);
            }
        });
    }

    private String dataStr;
    private String[] dataSub;

    private void getData(String pinyin, final String hanzi) {

        String url = "http://www.nmc.cn/publish/forecast/ASC/" + pinyin + ".html";

        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Document document = Jsoup.parse(str);
                Elements elements = document.getElementsByTag("script");
                Element element = elements.get(7);
                String temp = element.data().toString();
                String substring = temp.substring(temp.indexOf("climate("), temp.indexOf("});"));
                dataStr = substring.substring(8, 223);
                dataSub = dataStr.split("],");

                WeatherData weatherData = new WeatherData();
                weatherData.setName(hanzi);

                String highWeather = dataSub[0].replace("[", "");
                weatherData.setHighWeather(highWeather);

                String lowWeather = dataSub[1].replace("[", "");
                weatherData.setLowWeather(lowWeather);

                String rainfall;
                rainfall = dataSub[2].replace("[", "");
                rainfall = rainfall.replace("]", "");
                weatherData.setRainfall(rainfall);

                weatherDataList.add(weatherData);
                dataStr = null;
                dataSub = null;
            }

        });


    }
}
