package com.lexinsmart.cms.caramdemo.entity;

import java.util.List;

/**
 * Created by xushun on 2017/7/24.
 */

public class error {
    /**
     * success : 1
     * msg : Invalid token!
     * data : []
     */

    private int success;
    private String msg;
    private List<?> data;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
