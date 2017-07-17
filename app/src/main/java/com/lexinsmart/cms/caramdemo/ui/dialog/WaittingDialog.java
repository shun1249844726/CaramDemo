package com.lexinsmart.cms.caramdemo.ui.dialog;

import android.app.ProgressDialog;
import android.content.Context;

import com.lexinsmart.cms.caramdemo.R;

/**
 * Created by xushun on 2017/7/17.
 */

public class WaittingDialog extends ProgressDialog {
    private Context mContext;
    private ProgressDialog mProgressDialog;
    public WaittingDialog(Context context) {
        super(context);
        mContext = context;
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setIcon(R.mipmap.ic_launcher);
        mProgressDialog.setTitle("提示");
        mProgressDialog.setMessage("正在加载请稍后……");

    }
    public void showDialog(){
        mProgressDialog.show();
    }
    public void dismissDialog(){
        mProgressDialog.dismiss();
    }

}
