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
            case "er/6636366/00011":
                return Constant.TYPE_TEMPETURE;
            case "er/6636366/00012":
                return Constant.TYPE_HUMIDITY;
            case "er/6636366/00013":
                return Constant.TYPE_SMOKE;

            case "er/6636366/00014":
                return Constant.TYPE_INFRARED;

            case "er/6636366/00015":
                return Constant.TYPE_POWER;

            case "er/6636366/00016":
                return Constant.TYPE_DOOR;

            case "er/6636366/00017":
                return Constant.TYPE_AIRCOND;

            default:
                return Constant.TYPE_UNKNOW;
        }
    }
}
