package com.lexinsmart.cms.caramdemo.http.device;

/**
 * Created by xushun on 2017/7/17.
 */


import com.lexinsmart.cms.caramdemo.entity.DeviceListData;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.lexinsmart.cms.caramdemo.Constant.BASE_URL;

public class GetDeviceMethod {
    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit mRetrofit;
    private GetDeviceListService mGetDeviceListService;

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final GetDeviceMethod INSTANCE = new GetDeviceMethod();
    }

    //获取单例
    public static GetDeviceMethod getInstance() {
        return GetDeviceMethod.SingletonHolder.INSTANCE;
    }

    private GetDeviceMethod() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mGetDeviceListService = mRetrofit.create(GetDeviceListService.class);


    }

    public void getDeviceList(Subscriber<DeviceListData> subscriber, String token) {
        Observable observable = mGetDeviceListService.getDeviceList(token);

        toSubscribe(observable, subscriber);

    }

    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Observer<? super T>) s);
    }

}
