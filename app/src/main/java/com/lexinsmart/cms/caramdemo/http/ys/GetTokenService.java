package com.lexinsmart.cms.caramdemo.http.ys;

import com.lexinsmart.cms.caramdemo.entity.AccessToken;
import com.lexinsmart.cms.caramdemo.entity.LoginResult;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by xushun on 2017/7/15.
 */

public interface GetTokenService {
    @FormUrlEncoded
    @POST("lapp/token/get")
    Observable<AccessToken> getToken(@Field("appKey") String username, @Field("appSecret") String password);
}
