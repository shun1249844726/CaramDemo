package com.lexinsmart.cms.caramdemo.http.login;

import com.lexinsmart.cms.caramdemo.entity.LoginResult;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by xushun on 2017/7/13.
 */

public interface LoginService {
    @FormUrlEncoded
    @POST("login")
    Observable<LoginResult> login(@Field("username") String username, @Field("password") String password);
}
