package com.lexinsmart.cms.caramdemo.http.getlog;

import com.lexinsmart.cms.caramdemo.entity.DetailsListEntity;
import com.lexinsmart.cms.caramdemo.entity.LoginResult;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by xushun on 2017/9/24.
 * 功能描述：
 * 心情：
 */

public interface GetLogService {
    @GET("log")
    Observable<DetailsListEntity> getLog(@Query("topic") String topic, @Query("per") int per, @Query("page") int page,
                                         @Query("token") String token);
}
