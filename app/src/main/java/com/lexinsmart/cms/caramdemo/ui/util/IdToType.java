package com.lexinsmart.cms.caramdemo.ui.util;

import android.view.SurfaceHolder;

import com.lexinsmart.cms.caramdemo.Constant;
import com.orhanobut.logger.Logger;

/**
 * Created by xushun on 2017/7/20.
 */

public class IdToType {
    public int idToType(String id){
        Logger.d("id-+"+id);
        switch (id){
            case "er/123456712341":
                return Constant.TYPE_TEMPETURE;
            case "er/123456712342":
                return Constant.TYPE_HUMIDITY;
            case "er/123456712343":
                return Constant.TYPE_AIRCOND;
            case "er/123456712344":
                return Constant.TYPE_INFRARED;
            case "er/123456712345":
                return Constant.TYPE_SMOKE;
            case "er/123456712346":
                return Constant.TYPE_DOOR;
            case "er/123456712347":
                return Constant.TYPE_POWER;
            default:
                return Constant.TYPE_UNKNOW;
        }
    }
}
