package com.lexinsmart.cms.caramdemo;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lexinsmart.cms.caramdemo.activity.AddDeviceActivity;
import com.lexinsmart.cms.caramdemo.activity.DateChartActivity;
import com.lexinsmart.cms.caramdemo.activity.RealPlayActivity;
import com.lexinsmart.cms.caramdemo.entity.AccessToken;
import com.lexinsmart.cms.caramdemo.entity.DeviceListData;
import com.lexinsmart.cms.caramdemo.http.device.get.GetDeviceMethod;
import com.lexinsmart.cms.caramdemo.http.ys.GetTokenMethod;
import com.lexinsmart.cms.caramdemo.ui.adapter.DeviceListAdapter;
import com.lexinsmart.cms.caramdemo.ui.dialog.WaittingDialog;
import com.orhanobut.logger.Logger;

import java.io.Serializable;
import java.util.ArrayList;

import rx.Subscriber;

import static com.lexinsmart.cms.caramdemo.Constant.APPKEY;
import static com.lexinsmart.cms.caramdemo.Constant.APPSECRET;
import static com.lexinsmart.cms.caramdemo.Constant.ACCESSTOKEN;
import static com.lexinsmart.cms.caramdemo.Constant.TOKEN;


public class MainActivity extends AppCompatActivity {
    private Subscriber<AccessToken> subscribe;
    private WaittingDialog mWaittingDialog;
    private ListView deviceList;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ArrayList<DeviceListData.DataBean> DeviceListDataBean = new ArrayList<DeviceListData.DataBean>();
    private DeviceListAdapter deviceListAdapter;
    private Subscriber<DeviceListData> deviceSubscribe;

    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.sw_refresh_layout);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TOKEN.equals("")) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("bean",(Serializable) DeviceListDataBean);
                    intent.setClass(MainActivity.this,RealPlayActivity.class);
                    intent.putExtras(bundle);

              //      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);


                }
            }
        });
        mWaittingDialog = new WaittingDialog(this);
        mWaittingDialog.showDialog();

//        if (TextUtils.isEmpty(EzvizApplication.AppKey)){
//            Toast.makeText(this,"Appkey为空",Toast.LENGTH_LONG).show();
//            return;
//        }
////                EzvizApplication.getOpenSDK().startPushService();
//        ActivityUtils.goToLoginAgain(MainActivity.this);

        deviceList = (ListView) findViewById(R.id.lv_device_list);
        deviceListAdapter = new DeviceListAdapter(this, DeviceListDataBean);
        deviceList.setAdapter(deviceListAdapter);

        initSubScribe();
        initGetDeviceSubscribe();

        GetTokenMethod.getInstance().getToken(subscribe, APPKEY, APPSECRET);
        GetDeviceMethod.getInstance().getDeviceList(deviceSubscribe, TOKEN);


        //刷新界面
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                DeviceListDataBean.clear();
                initGetDeviceSubscribe();
                GetDeviceMethod.getInstance().getDeviceList(deviceSubscribe, TOKEN);
            }
        });
        deviceList.setOnItemClickListener(mOnItemClickListener);


    }
    AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String topic = DeviceListDataBean.get(position).getTopic();
            Intent intent = new Intent();
            intent.putExtra("topic", topic);
            intent.setClass(MainActivity.this, DateChartActivity.class);
            startActivity(intent);
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Intent intent  = new Intent();
            intent.setClass(MainActivity.this, AddDeviceActivity.class);
            startActivityForResult(intent,REQUEST_CODE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && requestCode == 0){
            initGetDeviceSubscribe();
            GetDeviceMethod.getInstance().getDeviceList(deviceSubscribe, TOKEN);
        }

    }

    /**
     * 初始化获取设备列表的观察者
     */
    private void initGetDeviceSubscribe() {
        deviceSubscribe = new Subscriber<DeviceListData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(DeviceListData deviceListData) {
                Logger.d("---====");
                Gson gson = new Gson();
                Logger.json(gson.toJson(deviceListData));
                refresh(deviceListData);

                Logger.d("refreshed!");

                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }

            }
        };

    }

    /**
     * 更新list view 注意浅拷贝，深拷贝的知识。
     *
     * @param deviceListDataBean
     */
    private void refresh(DeviceListData deviceListDataBean) {
        for (DeviceListData.DataBean bean : deviceListDataBean.getData()) {
            DeviceListDataBean.add(bean);
        }
        deviceListAdapter.notifyDataSetChanged();
    }

    /**
     * 初始化获取token的观察者
     */
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
//                Gson gson = new Gson();
//                Logger.json(gson.toJson(accessToken));
                if (accessToken.getCode().equals("200")) {
                    ACCESSTOKEN = accessToken.getData().getAccessToken();
                    //设置获取的token
                    com.lexinsmart.cms.caramdemo.EzvizApplication.getOpenSDK().setAccessToken(ACCESSTOKEN);


                    mWaittingDialog.dismissDialog();

                }
            }
        };
    }
}
