package com.lexinsmart.cms.caramdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.lexinsmart.cms.caramdemo.ui.util.ActivityUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (TextUtils.isEmpty(EzvizApplication.AppKey)){
            Toast.makeText(this,"Appkey为空",Toast.LENGTH_LONG).show();
            return;
        }
//                EzvizApplication.getOpenSDK().startPushService();
        ActivityUtils.goToLoginAgain(MainActivity.this);
    }
}
