package com.lexinsmart.cms.caramdemo.entity;

import java.util.List;

/**
 * Created by xushun on 2017/7/24.
 */

public class AddDeviceResultEntity {
    /**
     * success : 0
     * msg : Insert device success!
     * data : [{"device_name":"门禁","topic":"er/1234567/12348","value":"0","remarks":"门禁"}]
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

    public static class DataBean {
        /**
         * device_name : 门禁
         * topic : er/1234567/12348
         * value : 0
         * remarks : 门禁
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
