package com.lexinsmart.cms.caramdemo.http.ys;

import com.lexinsmart.cms.caramdemo.entity.AccessToken;
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

/**
 * Created by xushun on 2017/7/15.
 */

public class GetTokenMethod {
    public static final String BASE_GETTOKEN_URL = "https://open.ys7.com/api/";
    private  static final  int DEFAULT_TIMEOUT = 5;

    private Retrofit mRetrofit;
    private GetTokenService mGetTokenService ;

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final GetTokenMethod INSTANCE = new GetTokenMethod();
    }
    //获取单例
    public static GetTokenMethod getInstance(){
        return GetTokenMethod.SingletonHolder.INSTANCE;
    }
    private GetTokenMethod(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_GETTOKEN_URL)
                .build();
        mGetTokenService  = mRetrofit.create(GetTokenService.class);
    }
    public void  getToken(Subscriber<AccessToken> subscriber, String username , String password){
        Observable observable = mGetTokenService.getToken(username,password);

        toSubscribe(observable,subscriber);

    }

    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s){
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Observer<? super T>) s);
    }
}
