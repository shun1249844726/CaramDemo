package com.lexinsmart.cms.caramdemo.http.device.add;

import com.lexinsmart.cms.caramdemo.entity.AddDeviceResultEntity;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.lexinsmart.cms.caramdemo.Constant.BASE_URL;
/**
 * Created by xushun on 2017/7/24.
 */

public class AddDevceMethord {

    private static final int DEFAULT_TIMEOUT = 5;
    private Retrofit mRetrofit;
    private AddDeviceService mAddDeviceService;

    private AddDevceMethord() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(builder.build())
                .build();
        mAddDeviceService = mRetrofit.create(AddDeviceService.class);
    }
    public static class SingletonHolder{
        private static final AddDevceMethord INSTANCE  = new AddDevceMethord();
    }
    public static AddDevceMethord getInstance(){
        return SingletonHolder.INSTANCE;
    }
    public void addDevice(Subscriber<AddDeviceResultEntity> subscriber,String deviceName,String topic,String remarks,String token){
        Observable observable = mAddDeviceService.addDevice(deviceName,topic,remarks,token);
        toSubscribe(observable,subscriber);

    }

    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Observer<? super T>) s);
    }
}
