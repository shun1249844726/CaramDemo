package com.lexinsmart.cms.caramdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.lexinsmart.cms.caramdemo.ui.util.ActivityUtils;
import com.lexinsmart.cms.caramdemo.ui.util.DataManager;
import com.lexinsmart.cms.caramdemo.ui.util.EZUtils;
import com.videogo.errorlayer.ErrorInfo;
import com.videogo.exception.BaseException;
import com.videogo.exception.ErrorCode;
import com.videogo.openapi.EZPlayer;
import com.videogo.openapi.bean.EZCameraInfo;
import com.videogo.openapi.bean.EZDeviceInfo;
import com.videogo.util.ConnectionDetector;
import com.videogo.util.LogUtil;
import com.videogo.util.Utils;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static com.lexinsmart.cms.caramdemo.EzvizApplication.getOpenSDK;

/**
 * Created by xushun on 2017/7/11.
 */

public class RealPlayActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    protected static final String TAG = "RealPlayActivity";
    private SurfaceView mRealPlaySv = null;
    private SurfaceHolder mRealPlaySh = null;
    private EZPlayer mEZPlayer = null;

    private EZDeviceInfo mDeviceInfo = null;
    private EZCameraInfo mCameraInfo = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ez_realplay_page);
        mRealPlaySv = (SurfaceView) findViewById(R.id.realplay_sv);
        mRealPlaySh = mRealPlaySv.getHolder();
        getCameraInfoList(true);
    }

    /**
     * 从服务器获取最新事件消息
     */
    private void getCameraInfoList(boolean headerOrFooter) {
        if (this.isFinishing()) {
            return;
        }
        new GetCamersInfoListTask(headerOrFooter).execute();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (mEZPlayer != null) {
            mEZPlayer.setSurfaceHold(holder);
        }
        mRealPlaySh = holder;

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mEZPlayer != null) {
            mEZPlayer.setSurfaceHold(null);
        }
        mRealPlaySh = null;

    }

    /**
     * 获取事件消息任务
     */
    private class GetCamersInfoListTask extends AsyncTask<Void, Void, List<EZDeviceInfo>> {
        private boolean mHeaderOrFooter;
        private int mErrorCode = 0;

        public GetCamersInfoListTask(boolean headerOrFooter) {
            mHeaderOrFooter = headerOrFooter;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //mListView.setFooterRefreshEnabled(true);
            if (mHeaderOrFooter) {

            }
        }

        @Override
        protected List<EZDeviceInfo> doInBackground(Void... params) {
            if (RealPlayActivity.this.isFinishing()) {
                return null;
            }
            if (!ConnectionDetector.isNetworkAvailable(RealPlayActivity.this)) {
                mErrorCode = ErrorCode.ERROR_WEB_NET_EXCEPTION;
                return null;
            }

            try {
                List<EZDeviceInfo> result = null;
                result = getOpenSDK().getDeviceList(0, 20);

                return result;

            } catch (BaseException e) {
                ErrorInfo errorInfo = (ErrorInfo) e.getObject();
                mErrorCode = errorInfo.errorCode;
                LogUtil.debugLog(TAG, errorInfo.toString());

                return null;
            }
        }

        @Override
        protected void onPostExecute(List<EZDeviceInfo> result) {
            super.onPostExecute(result);
            if (RealPlayActivity.this.isFinishing()) {
                return;
            }

            if (result != null) {

                System.out.println("reslut.size():" + result.size());

                mDeviceInfo = result.get(0);
                mCameraInfo = EZUtils.getCameraInfoFromDevice(mDeviceInfo, 0);

                String verifyCode = "YEKKCT";
                LogUtil.debugLog(TAG, "verify code is " + verifyCode);
                DataManager.getInstance().setDeviceSerialVerifyCode(mCameraInfo.getDeviceSerial(), verifyCode);
                startRealPlay();

            }

            if (mErrorCode != 0) {
                onError(mErrorCode);
            }
        }

        protected void onError(int errorCode) {
            switch (errorCode) {
                case ErrorCode.ERROR_WEB_SESSION_ERROR:
                case ErrorCode.ERROR_WEB_SESSION_EXPIRE:
                    ActivityUtils.handleSessionException(RealPlayActivity.this);
                    break;
                default:

                    break;
            }
        }

        /**
         * 开始播放
         *
         * @see
         * @since V2.0
         */
        private void startRealPlay() {
            Log.d(TAG, "startrealplay");
            if (mCameraInfo != null) {

                if (mEZPlayer == null) {
                    mEZPlayer = EzvizApplication.getOpenSDK().createPlayer(mCameraInfo.getDeviceSerial(), mCameraInfo.getCameraNo());
                }

                if (mEZPlayer == null)
                    return;
                if (mDeviceInfo == null) {
                    return;
                }
                if (mDeviceInfo.getIsEncrypt() == 1) {
                    mEZPlayer.setPlayVerifyCode(DataManager.getInstance().getDeviceSerialVerifyCode(mCameraInfo.getDeviceSerial()));
                }

          //      mEZPlayer.setHandler(mHandler);
                mEZPlayer.setSurfaceHold(mRealPlaySh);
                mEZPlayer.startRealPlay();
            }
        }

    }
}
