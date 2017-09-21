package com.lexinsmart.cms.caramdemo.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.lexinsmart.cms.caramdemo.Constant;
import com.lexinsmart.cms.caramdemo.EzvizApplication;
import com.lexinsmart.cms.caramdemo.R;
import com.lexinsmart.cms.caramdemo.entity.DeviceListData;
import com.lexinsmart.cms.caramdemo.entity.SensorData;
import com.lexinsmart.cms.caramdemo.http.mqtt.MqttV3Service;
import com.lexinsmart.cms.caramdemo.ui.adapter.DeviceDetailsGridAdapter;
import com.lexinsmart.cms.caramdemo.ui.util.ActivityUtils;
import com.lexinsmart.cms.caramdemo.ui.util.DataManager;
import com.lexinsmart.cms.caramdemo.ui.util.EZUtils;
import com.lexinsmart.cms.caramdemo.ui.util.IdToType;
import com.orhanobut.logger.Logger;
import com.videogo.errorlayer.ErrorInfo;
import com.videogo.exception.BaseException;
import com.videogo.exception.ErrorCode;
import com.videogo.openapi.EZPlayer;
import com.videogo.openapi.bean.EZCameraInfo;
import com.videogo.openapi.bean.EZDeviceInfo;
import com.videogo.util.ConnectionDetector;
import com.videogo.util.LogUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.lexinsmart.cms.caramdemo.EzvizApplication.getOpenSDK;
import static com.lexinsmart.cms.caramdemo.Constant.MQTT_ADDRESS;
import static com.lexinsmart.cms.caramdemo.Constant.MQTT_PORT;

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
    private Context context;

    private GridView gvDetails;
    private DeviceDetailsGridAdapter mDeviceDetailsGridAdapter;

    int Qos = 1;
    ArrayList<String> topicList = new ArrayList<String>();
    private static List<DeviceListData.DataBean> DeviceListDataBean = new ArrayList<DeviceListData.DataBean>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ez_realplay_page);

        context = this;
        DeviceListDataBean = (List<DeviceListData.DataBean>) getIntent().getSerializableExtra("bean");

        mRealPlaySv = (SurfaceView) findViewById(R.id.realplay_sv);
        mRealPlaySh = mRealPlaySv.getHolder();
        getCameraInfoList(true);

        for (int topicListIndex = 0; topicListIndex < DeviceListDataBean.size(); topicListIndex++) {
            topicList.add(DeviceListDataBean.get(topicListIndex).getTopic());
        }

        gvDetails = (GridView) findViewById(R.id.gv_device_details);
        mDeviceDetailsGridAdapter = new DeviceDetailsGridAdapter(context, DeviceListDataBean);
        gvDetails.setAdapter(mDeviceDetailsGridAdapter);

        gvDetails.setOnItemClickListener(onclick);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (MqttV3Service.client == null){
            new Thread(new MqttProcThread()).start();
        }else if (!MqttV3Service.isConnected()){
            new Thread(new MqttProcThread()).start();

        }


    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    AdapterView.OnItemClickListener onclick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            IdToType mIdToType = new IdToType();
            Map<String, String> linkedHashMap = new LinkedHashMap<String, String>();
            Gson gson = new Gson();
            String message ="";
            int type = mIdToType.idToType(DeviceListDataBean.get(position).getTopic());
            if (type > 10 && MqttV3Service.isConnected()) {

                linkedHashMap.put("id", DeviceListDataBean.get(position).getTopic());
                linkedHashMap.put("type", "c");

                switch (type) {
                    case Constant.TYPE_AIRCOND:
                        Logger.d("value:" + DeviceListDataBean.get(position).getValue());
                        linkedHashMap.put("ctype", "airc");

                        if (Integer.parseInt(DeviceListDataBean.get(position).getValue()) > 0) {
                            DeviceListDataBean.get(position).setValue("000");
                            linkedHashMap.put("data", "000");
                            mDeviceDetailsGridAdapter.notifyDataSetChanged();

                        } else {
                            linkedHashMap.put("data", "100");
                            DeviceListDataBean.get(position).setValue("100");
                            mDeviceDetailsGridAdapter.notifyDataSetChanged();

                        }
                        message = gson.toJson(linkedHashMap);
                        MqttV3Service.publishMsg(message.trim().replace(":",": "), Qos,position);
                        Logger.d("message:"+message.trim().replace(":",": "));
                        break;
                    case Constant.TYPE_DOOR:
                        linkedHashMap.put("ctype", "door");
                        if (Integer.parseInt(DeviceListDataBean.get(position).getValue()) > 0) {
                            DeviceListDataBean.get(position).setValue("000");
                            linkedHashMap.put("data", "000");
                            mDeviceDetailsGridAdapter.notifyDataSetChanged();

                        } else {
                            linkedHashMap.put("data", "100");
                            DeviceListDataBean.get(position).setValue("100");
                            mDeviceDetailsGridAdapter.notifyDataSetChanged();

                        }
//                        Iterator<Map.Entry<String, String>> it = linkedHashMap.entrySet().iterator();
//                        while (it.hasNext()) {
//                            Map.Entry<String, String> e = it.next();
//                           Logger.d("Key: " + e.getKey() + ";   Value: "       + e.getValue());
//                        }
                        message = gson.toJson(linkedHashMap);
                        MqttV3Service.publishMsg(message.trim().replace(":",": "), Qos,position);
                        Logger.d("message:"+message.trim().replace(":",": "));

                        break;
                    default:
                        break;
                }
            }else if (!MqttV3Service.isConnected()){
                Toast.makeText(context,"设备未连接！",Toast.LENGTH_SHORT).show();
            }
        }
    };

    public class MqttProcThread implements Runnable {

        String clientid = "xushun";

        @Override
        public void run() {
            Message msg = new Message();
            boolean ret = MqttV3Service.connectionMqttServer(myHandler, MQTT_ADDRESS, MQTT_PORT, clientid, topicList);
            if (ret) {
                msg.what = 1;
            } else {
                msg.what = 0;
            }
            msg.obj = "strresult";
            myHandler.sendMessage(msg);
        }
    }

    @SuppressWarnings("HandlerLeak")
    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                Toast.makeText(context, "连接成功", Toast.LENGTH_SHORT).show();

            } else if (msg.what == 0) {
                Toast.makeText(context, "连接失败", Toast.LENGTH_SHORT).show();
            } else if (msg.what == 2) {
                String strContent = "";
                strContent += msg.getData().getString("content");

                strContent = strContent.replaceAll("\\s*", "");
                strContent = strContent.trim();
                Logger.d("strcontent:" + strContent);

                if (isGoodJson(strContent)) {
                    Gson gson = new Gson();
                    SensorData res = gson.fromJson(strContent, SensorData.class);
                    for (int j = 0; j < DeviceListDataBean.size(); j++) {
                        if (res.getId().equals(DeviceListDataBean.get(j).getTopic())) {
                            DeviceListDataBean.get(j).setValue(res.getData());
                        }
                    }
                    Logger.json(new Gson().toJson(DeviceListDataBean));
                    mDeviceDetailsGridAdapter.notifyDataSetChanged();
                }
            } else if (msg.what == 3) {
                if (MqttV3Service.closeMqtt()) {
                    Toast.makeText(context, "断开连接", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    public static boolean isGoodJson(String json) {

        try {
            new JsonParser().parse(json);
            return true;
        } catch (JsonParseException e) {
            System.out.println("bad json: " + json);
            return false;
        }
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
