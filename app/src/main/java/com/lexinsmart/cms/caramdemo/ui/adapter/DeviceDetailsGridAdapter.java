package com.lexinsmart.cms.caramdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lexinsmart.cms.caramdemo.R;
import com.lexinsmart.cms.caramdemo.entity.DeviceListData;

import java.util.List;

/**
 * Created by xushun on 2017/7/19.
 */

public class DeviceDetailsGridAdapter extends BaseAdapter {

    private List<DeviceListData.DataBean> DeviceListDataBean;
    private LayoutInflater mLayoutInflater;

    Context mContext;
    public DeviceDetailsGridAdapter(Context context, List<DeviceListData.DataBean> deviceListDataBean){
        this.DeviceListDataBean = deviceListDataBean;
        this.mContext = context;

        mLayoutInflater = LayoutInflater.from(context);


    }
    @Override
    public int getCount() {
        return DeviceListDataBean.size();
    }

    @Override
    public Object getItem(int position) {
        return DeviceListDataBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();

            convertView = mLayoutInflater.inflate(R.layout.item_grid_device_details,null);

            holder.tvValue = (TextView) convertView.findViewById(R.id.tv_grid_item_value);
            holder.imgType = (ImageView) convertView.findViewById(R.id.item_img_type);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvValue.setText(DeviceListDataBean.get(position).getValue());
        holder.imgType.setImageResource(R.mipmap.ic_tempeture_on);
        return convertView;
    }
    public final class ViewHolder{
        public TextView tvValue;
        public ImageView imgType;
    }

}
