package com.lexinsmart.cms.caramdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.google.gson.Gson;
import com.lexinsmart.cms.caramdemo.Constant;
import com.lexinsmart.cms.caramdemo.R;
import com.lexinsmart.cms.caramdemo.entity.DetailsListEntity;
import com.lexinsmart.cms.caramdemo.http.getlog.GetLogMethod;
import com.lexinsmart.cms.caramdemo.ui.util.ChartUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by xushun on 2017/9/22.
 * 功能描述：
 * 心情：
 */

public class DateChartActivity extends AppCompatActivity {
    //    float dates[] = {10.2f,11.1f,19f,30f,29f,30f,20f};
    List<Integer> dates = new ArrayList<>();
    List<String> xaxis = new ArrayList<>();
    private Subscriber<DetailsListEntity> subscriber;
    String topic = "er/6636366/00033";
    LineChart chart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linechart);

        Intent intent = getIntent();
        topic = intent.getStringExtra("topic");

        chart = (LineChart) findViewById(R.id.chart);

//        String[] week = {"2017-09-14 03:35:53", "2017-09-14 03:35:53", "2017-09-14 03:35:53", "2017-09-14 03:35:53", "2017-09-14 03:35:53", "2017-09-14 03:35:53", "2017-09-14 03:35:53"};
        ChartUtils.initChart(chart);

        //    getDetails(subscriber,topic ,""+20,""+1,Constant.TOKEN);

        subscriber = new Subscriber<DetailsListEntity>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted:");

            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError:");

            }

            @Override
            public void onNext(DetailsListEntity detailsListEntity) {
                System.out.println("onNext:");

                Gson gson = new Gson();
                Logger.json(gson.toJson(detailsListEntity));

                for (int i = 0; i < detailsListEntity.getData().size(); i++) {
                    dates.add(Integer.valueOf(detailsListEntity.getData().get(i).getValue()));
                    xaxis.add(detailsListEntity.getData().get(i).getDate_time());
                }
                ChartUtils.notifyDataSetChanged(chart, getData(), xaxis);

            }
        };
        getDetails(subscriber, topic, 20, 1, Constant.TOKEN);

    }

    private List<Entry> getData() {
        List<Entry> values = new ArrayList<>();

        for (int i = 0; i < dates.size(); i++) {
            values.add(new Entry(i, dates.get(i)));
        }
        return values;
    }

    public void getDetails(Subscriber<DetailsListEntity> msubscriber, String topic, int per, int page, String token) {
        GetLogMethod.getInstance().getlog(msubscriber, topic, per, page, token);
    }

    @Override
    protected void onPause() {
        super.onPause();
        subscriber.unsubscribe();
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume:");
    }

}
