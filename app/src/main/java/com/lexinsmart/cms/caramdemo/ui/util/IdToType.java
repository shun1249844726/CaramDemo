package com.lexinsmart.cms.caramdemo.ui.util;

import android.view.SurfaceHolder;

import com.lexinsmart.cms.caramdemo.Constant;
import com.orhanobut.logger.Logger;

/**
 * Created by xushun on 2017/7/20.
 */

public class IdToType {
    public int idToType(String id){
        switch (id){
            case "er/1234567/12341":
                return Constant.TYPE_TEMPETURE;
            case "er/1234567/12342":
                return Constant.TYPE_HUMIDITY;
            case "er/1234567/12343":
                return Constant.TYPE_SMOKE;

            case "er/1234567/12344":
                return Constant.TYPE_INFRARED;

            case "er/1234567/12345":
                return Constant.TYPE_POWER;

            case "er/1234567/12346":
                return Constant.TYPE_AIRCOND;

            case "er/1234567/12347":
                return Constant.TYPE_DOOR;

            default:
                return Constant.TYPE_UNKNOW;
        }
    }
}
