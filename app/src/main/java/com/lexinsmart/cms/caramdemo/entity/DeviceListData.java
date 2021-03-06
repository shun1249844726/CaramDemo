package com.lexinsmart.cms.caramdemo.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xushun on 2017/7/17.
 */

public class DeviceListData implements Serializable {
    /**
     * success : 0
     * msg : Get device list success!
     * data : [{"device_name":"tempeture1","topic":"123456712341","value":"0","remarks":"温度传感器1"},{"device_name":"tempeture1","topic":"123456712342","value":"0","remarks":"湿度1"},{"device_name":"湿度1","topic":"123456712343","value":"0","remarks":"湿度1"}]
     */

    private int success;
    private String msg;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * device_name : tempeture1
         * topic : 123456712341
         * value : 0
         * remarks : 温度传感器1
         */

        private String device_name;
        private String topic;
        private String value;
        private String remarks;

        public String getDevice_name() {
            return device_name;
        }

        public void setDevice_name(String device_name) {
            this.device_name = device_name;
        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

    }

}
