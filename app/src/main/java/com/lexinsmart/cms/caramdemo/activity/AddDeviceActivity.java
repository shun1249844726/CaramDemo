package com.lexinsmart.cms.caramdemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lexinsmart.cms.caramdemo.R;
import com.lexinsmart.cms.caramdemo.entity.AddDeviceResultEntity;
import com.lexinsmart.cms.caramdemo.http.device.add.AddDevceMethord;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import static com.lexinsmart.cms.caramdemo.Constant.TOKEN;
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
    private Subscriber<AddDeviceResultEntity> mSubscriber ;
    private String devicename ,topic,remarks;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivity_add_device);
        ButterKnife.bind(this);
        mContext = this;
        initSubscriber();

    }
    @OnClick(R.id.btn_add_device_ok)
    public void addDevice(){
        devicename = mEdtDeviceName.getText().toString();
        topic = mEdtDeviceTopic.getText().toString();
        remarks = mEdtDeviceRemarks.getText().toString();
        if (!devicename.equals("") && !topic.equals("") && !remarks.equals("")){
            initSubscriber();
            AddDevceMethord.getInstance().addDevice(mSubscriber,devicename,topic,remarks,TOKEN);
        }else {
            Toast.makeText(mContext,"请输入完整信息！",Toast.LENGTH_SHORT).show();
        }

    }
    private  void initSubscriber(){
        mSubscriber = new Subscriber<AddDeviceResultEntity>() {
            @Override
            public void onCompleted() {
                Logger.d("completer");

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext,"设备已存在！",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(AddDeviceResultEntity addDeviceResultEntity) {
                Gson gson = new Gson();
                Logger.json(gson.toJson(addDeviceResultEntity));
                if (addDeviceResultEntity.getSuccess() == 0){
                    Toast.makeText(mContext,"插入成功！",Toast.LENGTH_SHORT).show();
                }
            }
        };
    }
}
