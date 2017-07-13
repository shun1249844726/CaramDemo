package com.lexinsmart.cms.caramdemo.http;

import android.support.annotation.NonNull;

import com.lexinsmart.cms.caramdemo.R;
import com.lexinsmart.cms.caramdemo.entity.LoginResult;


import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by xushun on 2017/7/13.
 */

public class LoginMethod {

    public static final String BASE_LOGIN_URL = "http://erm.lexinsmart.com/";
    private  static final  int DEFAULT_TIMEOUT = 5;

    private Retrofit mRetrofit;
    private LoginService mLoginService;

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final LoginMethod INSTANCE = new LoginMethod();
    }

    //获取单例
    public static LoginMethod getInstance(){
        return SingletonHolder.INSTANCE;
    }
    private LoginMethod(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_LOGIN_URL)
                .build();
        mLoginService  = mRetrofit.create(LoginService.class);

    }

    public void  login(Subscriber<LoginResult> subscriber, String username , String password){
        Observable observable = mLoginService.login(username,password);

        toSubscribe(observable,subscriber);

    }

    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s){
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Observer<? super T>) s);
    }

}
