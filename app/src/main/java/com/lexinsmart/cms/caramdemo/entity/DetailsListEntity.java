package com.lexinsmart.cms.caramdemo.entity;

import java.util.List;

/**
 * Created by xushun on 2017/9/24.
 * 功能描述：
 * 心情：
 */

public class DetailsListEntity {

    /**
     * success : 0
     * msg : Get device logs success!
     * page : 13
     * current_page : 1
     * data : [{"value":"027","date_time":"2017-07-26 10:02:50"},{"value":"028","date_time":"2017-09-14 03:30:19"},{"value":"028","date_time":"2017-09-14 03:30:50"},{"value":"028","date_time":"2017-09-14 03:32:43"},{"value":"027","date_time":"2017-09-14 03:32:58"},{"value":"027","date_time":"2017-09-14 03:33:13"},{"value":"028","date_time":"2017-09-14 03:33:32"},{"value":"028","date_time":"2017-09-14 03:33:46"},{"value":"027","date_time":"2017-09-14 03:34:01"},{"value":"028","date_time":"2017-09-14 03:34:16"},{"value":"027","date_time":"2017-09-14 03:34:34"},{"value":"029","date_time":"2017-09-14 03:34:49"},{"value":"028","date_time":"2017-09-14 03:35:05"},{"value":"027","date_time":"2017-09-14 03:35:20"},{"value":"028","date_time":"2017-09-14 03:35:38"},{"value":"027","date_time":"2017-09-14 03:35:53"},{"value":"027","date_time":"2017-09-14 03:36:09"},{"value":"029","date_time":"2017-09-14 03:36:23"},{"value":"028","date_time":"2017-09-14 03:36:42"},{"value":"027","date_time":"2017-09-14 03:36:56"}]
     */

    private int success;
    private String msg;
    private int page;
    private int current_page;
    private List<DataBean> data;

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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * value : 027
         * date_time : 2017-07-26 10:02:50
         */

        private String value;
        private String date_time;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getDate_time() {
            return date_time;
        }

        public void setDate_time(String date_time) {
            this.date_time = date_time;
        }
    }
}
