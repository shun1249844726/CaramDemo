package com.lexinsmart.cms.caramdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lexinsmart.cms.caramdemo.Constant;
import com.lexinsmart.cms.caramdemo.R;
import com.lexinsmart.cms.caramdemo.entity.DeviceListData;
import com.lexinsmart.cms.caramdemo.ui.util.IdToType;
import com.orhanobut.logger.Logger;

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

        IdToType mIdToType = new IdToType();
        int type = 0;
        type = mIdToType.idToType(DeviceListDataBean.get(position).getTopic());
        String value = DeviceListDataBean.get(position).getValue();
        int valueData = Integer.parseInt(value);
        String  id = DeviceListDataBean.get(position).getTopic();
        switch (type){
            case Constant.TYPE_UNKNOW:
                value = "未知设备";
                holder.imgType.setImageResource(R.mipmap.face);
                break;
            case Constant.TYPE_AIRCOND:
                if (valueData == 0){
                    holder.imgType.setImageResource(R.mipmap.ic_aircond_off);
                    value = "空调 关";

                }else {
                    holder.imgType.setImageResource(R.mipmap.ic_aircond_on);
                    value = "空调 开";

                }
                break;
            case Constant.TYPE_HUMIDITY:
                value = "湿度:"+valueData;
                holder.imgType.setImageResource(R.mipmap.ic_humidity_on);
                break;
            case Constant.TYPE_INFRARED:

                if (valueData == 0){
                    value = "人体检测";
                    holder.imgType.setImageResource(R.mipmap.ic_body_detect_off);
                }
                else {
                    holder.imgType.setImageResource(R.mipmap.ic_body_detect_on);
                    value = "人体检测:有人";

                }
                break;
            case Constant.TYPE_SMOKE:
                value = "烟雾浓度:"+valueData;
                holder.imgType.setImageResource(R.mipmap.ic_smoke_off);
                break;
            case Constant.TYPE_TEMPETURE:
                value = "温度:"+valueData+" 度";

                holder.imgType.setImageResource(R.mipmap.ic_tempeture_on);
                break;
            case Constant.TYPE_DOOR:
                Logger.d("data:"+valueData);
                if (valueData == 0){
                    value = "门禁 关";
                    holder.imgType.setImageResource(R.mipmap.ic_close_door);
                }else {
                    value = "门禁 开";
                    holder.imgType.setImageResource(R.mipmap.ic_open_door);
                }
                break;

            case Constant.TYPE_POWER:
                value = "UPS电压:"+valueData;
                holder.imgType.setImageResource(R.mipmap.ic_power_off);
                break;
        }
        holder.tvValue.setText(value);
        return convertView;
    }
    public final class ViewHolder{
        public TextView tvValue;
        public ImageView imgType;
    }

}
