package com.lexinsmart.cms.caramdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lexinsmart.cms.caramdemo.entity.AccessToken;
import com.lexinsmart.cms.caramdemo.entity.DeviceListData;
import com.lexinsmart.cms.caramdemo.entity.LoginResult;
import com.lexinsmart.cms.caramdemo.http.ys.GetTokenMethod;
import com.lexinsmart.cms.caramdemo.ui.adapter.DeviceListAdapter;
import com.lexinsmart.cms.caramdemo.ui.dialog.WaittingDialog;
import com.lexinsmart.cms.caramdemo.ui.util.ActivityUtils;
import com.orhanobut.logger.Logger;
import com.videogo.openapi.bean.EZAccessToken;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import static com.lexinsmart.cms.caramdemo.Constant.APPKEY;
import static com.lexinsmart.cms.caramdemo.Constant.APPSECRET;
import static com.lexinsmart.cms.caramdemo.Constant.ACCESSTOKEN;


public class MainActivity extends AppCompatActivity {
    private Subscriber<AccessToken> subscribe;
    private WaittingDialog mWaittingDialog;
    private ListView deviceList;
    private List<DeviceListData.DataBean> deviceListData ;
    DeviceListAdapter deviceListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWaittingDialog = new WaittingDialog(this);
        mWaittingDialog.showDialog();

//        if (TextUtils.isEmpty(EzvizApplication.AppKey)){
//            Toast.makeText(this,"Appkey为空",Toast.LENGTH_LONG).show();
//            return;
//        }
////                EzvizApplication.getOpenSDK().startPushService();
//        ActivityUtils.goToLoginAgain(MainActivity.this);

        initSubScribe();

        GetTokenMethod.getInstance().getToken(subscribe,APPKEY,APPSECRET);

        deviceList = (ListView) findViewById(R.id.lv_device_list);
        deviceListAdapter = new DeviceListAdapter(this,deviceListData);
        deviceList.setAdapter(deviceListAdapter);


    }

    private void initSubScribe() {

        subscribe = new Subscriber<AccessToken>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(AccessToken accessToken) {
                Gson gson = new Gson();
                Logger.json(gson.toJson(accessToken));
                if (accessToken.getCode().equals("200")){
                    ACCESSTOKEN = accessToken.getData().getAccessToken();
                    //设置获取的token
                    com.lexinsmart.cms.caramdemo.EzvizApplication.getOpenSDK().setAccessToken(ACCESSTOKEN);


                    mWaittingDialog.dismissDialog();

                    Intent toIntent = new Intent(MainActivity.this, RealPlayActivity.class);
                    toIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(toIntent);
                }
            }
        };
    }
}
