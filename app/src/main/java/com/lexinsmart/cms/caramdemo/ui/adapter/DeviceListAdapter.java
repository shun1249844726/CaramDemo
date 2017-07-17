package com.lexinsmart.cms.caramdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lexinsmart.cms.caramdemo.R;
import com.lexinsmart.cms.caramdemo.entity.DeviceListData;

import java.util.List;

/**
 * Created by xushun on 2017/7/17.
 */

public class DeviceListAdapter extends BaseAdapter {
    private List<DeviceListData.DataBean> deviceListData ;
    private LayoutInflater mLayoutInflater;
    public DeviceListAdapter(Context context, List<DeviceListData.DataBean> data){
        this.deviceListData = data;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return deviceListData.size();
    }

    @Override
    public Object getItem(int position) {
        return deviceListData.get(position);
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

            convertView = mLayoutInflater.inflate(R.layout.item_device_list,null);

            holder.deviceName = (TextView) convertView.findViewById(R.id.tv_device_list_name);
            holder.deviceData = (TextView) convertView.findViewById(R.id.tv_device_list_value);
            holder.deviceTopic = (TextView) convertView.findViewById(R.id.tv_device_list_topic);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.deviceName.setText(deviceListData.get(position).getDevice_name());
        holder.deviceData.setText(deviceListData.get(position).getValue());
        holder.deviceTopic.setText(deviceListData.get(position).getTopic());
        return convertView;
    }
    public final class ViewHolder{
        public TextView deviceName;
        public TextView deviceTopic;
        public TextView deviceData;
    }
}
