package com.lexinsmart.cms.caramdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lexinsmart.cms.caramdemo.Constant;
import com.lexinsmart.cms.caramdemo.MainActivity;
import com.lexinsmart.cms.caramdemo.R;
import com.lexinsmart.cms.caramdemo.entity.LoginResult;
import com.lexinsmart.cms.caramdemo.http.login.LoginMethod;
import com.orhanobut.logger.Logger;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Created by xushun on 2017/7/13.
 */

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.img_login_heder)
    ImageView mImgLoginHeder;
    @BindView(R.id.edt_login_userId)
    EditText mEdtLoginUserId;
    @BindView(R.id.edt_login_psd)
    EditText mEdtLoginPsd;
    @BindView(R.id.ll_login_input)
    LinearLayout mLlLoginInput;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.tv_rlogin_egist)
    TextView mTvRloginEgist;

    final String TAG = "loginActivity";

    private Subscriber<LoginResult> subscribe ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        subscribe = new Subscriber<LoginResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(LoginResult loginResult) {
//                Gson gson = new Gson();
//                Logger.json(gson.toJson(loginResult));
                if (loginResult.getSuccess() == 0){
                    Logger.d(loginResult.getData().getToken().toString());
                    Constant.TOKEN = loginResult.getData().getToken();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };
    }
    @OnClick(R.id.btn_login)
    public void login(){
        login(mEdtLoginUserId.getText().toString(),mEdtLoginPsd.getText().toString());
    }

    private void login(String uname, String psd) {
        LoginMethod.getInstance().login(subscribe,uname,psd);
    }

}
