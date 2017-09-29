package com.lexinsmart.cms.caramdemo.http.getlog;

import com.lexinsmart.cms.caramdemo.entity.DetailsListEntity;
import com.lexinsmart.cms.caramdemo.entity.LoginResult;
import com.lexinsmart.cms.caramdemo.http.login.LoginMethod;
import com.lexinsmart.cms.caramdemo.http.login.LoginService;

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

/**
 * Created by xushun on 2017/9/24.
 * 功能描述：
 * 心情：
 */

public class GetLogMethod {
    private  static final  int DEFAULT_TIMEOUT = 5;

    private Retrofit mRetrofit;
    private GetLogService mGetLogService;

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final GetLogMethod INSTANCE = new GetLogMethod();
    }

    //获取单例
    public static GetLogMethod getInstance(){
        return GetLogMethod.SingletonHolder.INSTANCE;
    }
    private GetLogMethod(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        mGetLogService  = mRetrofit.create(GetLogService.class);

    }

    public void  getlog(Subscriber<DetailsListEntity> subscriber, String topic , int per, int page, String token){
        Observable observable = mGetLogService.getLog(topic,per,page,token);

        toSubscribe(observable,subscriber);

    }

    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s){
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Observer<? super T>) s);
    }
}
