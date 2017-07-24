package com.lexinsmart.cms.caramdemo.http.device.add;

import com.lexinsmart.cms.caramdemo.entity.AddDeviceResultEntity;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by xushun on 2017/7/24.
 */

public interface AddDeviceService {
    @FormUrlEncoded
    @POST("device")
    Observable<AddDeviceResultEntity> addDevice(@Field("device_name") String deviceName, @Field("topic")String topic, @Field("remarks")String remarks, @Field("token") String token);
}
