package com.lexinsmart.cms.caramdemo.entity;

/**
 * Created by xushun on 2017/7/19.
 */

public class SensorData {
    /**
     * id : er/12345671234x
     * type : s
     * stype : temp
     * data : 025
     */

    private String id;
    private String type;
    private String stype;
    private String data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
