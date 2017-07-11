package com.lexinsmart.cms.caramdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;

import com.lexinsmart.cms.caramdemo.ui.util.ActivityUtils;
import com.lexinsmart.cms.caramdemo.ui.util.EZUtils;
import com.videogo.errorlayer.ErrorInfo;
import com.videogo.exception.BaseException;
import com.videogo.exception.ErrorCode;
import com.videogo.openapi.bean.EZCameraInfo;
import com.videogo.openapi.bean.EZDeviceInfo;
import com.videogo.util.ConnectionDetector;
import com.videogo.util.LogUtil;
import com.videogo.util.Utils;

import java.util.Date;
import java.util.List;

import static com.lexinsmart.cms.caramdemo.EzvizApplication.getOpenSDK;

/**
 * Created by xushun on 2017/7/11.
 */

public class RealPlayActivity extends AppCompatActivity {

    protected static final String TAG = "RealPlayActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ez_realplay_page);
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

                System.out.println("reslut.size():"+result.size());
                EZDeviceInfo deviceInfo = null;
                EZCameraInfo cameraInfo = null;
                deviceInfo = result.get(0);
                cameraInfo = EZUtils.getCameraInfoFromDevice(deviceInfo, 0);

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
    }
}
