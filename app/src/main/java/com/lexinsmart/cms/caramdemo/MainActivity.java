package com.lexinsmart.cms.caramdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lexinsmart.cms.caramdemo.entity.AccessToken;
import com.lexinsmart.cms.caramdemo.entity.LoginResult;
import com.lexinsmart.cms.caramdemo.http.ys.GetTokenMethod;
import com.lexinsmart.cms.caramdemo.ui.util.ActivityUtils;
import com.orhanobut.logger.Logger;
import com.videogo.openapi.bean.EZAccessToken;

import java.util.ArrayList;

import rx.Subscriber;
import static com.lexinsmart.cms.caramdemo.Constant.APPKEY;
import static com.lexinsmart.cms.caramdemo.Constant.APPSECRET;
import static com.lexinsmart.cms.caramdemo.Constant.ACCESSTOKEN;


public class MainActivity extends AppCompatActivity {
    private Subscriber<AccessToken> subscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        if (TextUtils.isEmpty(EzvizApplication.AppKey)){
//            Toast.makeText(this,"Appkey为空",Toast.LENGTH_LONG).show();
//            return;
//        }
////                EzvizApplication.getOpenSDK().startPushService();
//        ActivityUtils.goToLoginAgain(MainActivity.this);

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

                    Intent toIntent = new Intent(MainActivity.this, RealPlayActivity.class);
                    toIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(toIntent);
                }
            }
        };

        GetTokenMethod.getInstance().getToken(subscribe,APPKEY,APPSECRET);


    }
}
