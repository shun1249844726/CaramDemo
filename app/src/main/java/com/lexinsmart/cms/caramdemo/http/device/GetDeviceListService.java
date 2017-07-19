package com.lexinsmart.cms.caramdemo.http.device;

import com.lexinsmart.cms.caramdemo.entity.DeviceListData;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by xushun on 2017/7/17.
 */

public interface GetDeviceListService {
    @GET("device")
    Observable<DeviceListData> getDeviceList(@Query("token") String token);
}
