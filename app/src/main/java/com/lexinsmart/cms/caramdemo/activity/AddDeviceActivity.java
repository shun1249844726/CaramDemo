package com.lexinsmart.cms.caramdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.lexinsmart.cms.caramdemo.R;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xushun on 2017/7/22.
 */

public class AddDeviceActivity extends AppCompatActivity {
    @BindView(R.id.edt_device_name)
    TextInputEditText mEdtDeviceName;
    @BindView(R.id.edt_device_topic)
    TextInputEditText mEdtDeviceTopic;
    @BindView(R.id.edt_device_remarks)
    TextInputEditText mEdtDeviceRemarks;
    @BindView(R.id.btn_add_device_ok)
    Button mBtnAddDeviceOk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivity_add_device);
        ButterKnife.bind(this);

    }
    @OnClick(R.id.btn_add_device_ok)
    public void addDevice(){
        Logger.d("add");

    }
}
