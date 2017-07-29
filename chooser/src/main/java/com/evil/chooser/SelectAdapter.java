package com.evil.chooser;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.evil.chooser.iface.ImageLoader;
import com.evil.chooser.ui.MyImageView;
import com.evil.chooser.utils.ChooserFinal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static com.evil.chooser.ChooserUtils.sChooserString;


/**
 * @项目名： FileChooser
 * @包名： com.evil.chooser.adapter
 * @创建者: Noah.冯
 * @时间: 13:44
 * @描述： TODO
 */

class SelectAdapter extends BaseAdapter {
    private Context mContext;
    private HashMap<String, List<ChooseInfo>> mMap;
    private List<String> mList;
    private String mSelectTitle;

    public SelectAdapter(Context context) {
        mContext = context;
    }

    public HashMap<String, List<ChooseInfo>> getMap() {
        return mMap;
    }

    public void setMap(HashMap<String, List<ChooseInfo>> map) {
        mMap = map;
        Set<String> set = mMap.keySet();
        mList = new ArrayList();
        for (String s : set) {
            if (sChooserString.equals(s)){
                mList.add(0,s);
            }else if ("所有视频".equals(s)){
                mList.add(0,s);
            }else{
                mList.add(s);
            }
        }
        mSelectTitle = sChooserString;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_chooser, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String key = mList.get(position);
        if (key.equals(mSelectTitle)) {
            holder.mCheckbox.setVisibility(View.VISIBLE);
        } else {
            holder.mCheckbox.setVisibility(View.GONE);
        }
        holder.mTvTitle.setText(key);
        List<ChooseInfo> chooseInfos = mMap.get(key);
        ImageLoader loader = ChooserFinal.getChooserConfig().getImageLoader();
        if (chooseInfos != null && chooseInfos.size() > 0) {
            ChooseInfo info = chooseInfos.get(0);
            loader.loadFile(mContext, info.getPath(), R.drawable.image, R.drawable.error_load, holder.mIv);
            holder.mTvNum.setText(mContext.getString(R.string.a_photo_num_text, chooseInfos.size()));
        } else {
            loader.loadRes(mContext, R.drawable.image, holder.mIv);
            holder.mTvNum.setText(mContext.getString(R.string.a_photo_num_text, 0));
        }
        holder.rootView.setOnClickListener(new OnClick(chooseInfos,position));
        return convertView;
    }

    private class OnClick implements View.OnClickListener{
        List<ChooseInfo> mChooseInfos;
        int position;

        public OnClick(List<ChooseInfo> chooseInfos, int position) {
            this.mChooseInfos = chooseInfos;
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            mSelectTitle = mList.get(position);
            if (position == 0){
                ChooserUtils.isSelectAll=true;
            }else{
                ChooserUtils.isSelectAll=false;
            }
            if (mChooseInfos != null) {
                ChooserUtils.sChooseCallback.choose(mChooseInfos);
            }
        }
    }

    public static class ViewHolder {
        public View rootView;
        public MyImageView mIv;
        public TextView mTvTitle;
        public TextView mTvNum;
        public CheckBox mCheckbox;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mIv = (MyImageView) rootView.findViewById(R.id.iv);
            this.mTvTitle = (TextView) rootView.findViewById(R.id.tv_title);
            this.mTvNum = (TextView) rootView.findViewById(R.id.tv_num);
            this.mCheckbox = (CheckBox) rootView.findViewById(R.id.checkbox);
        }

    }
}
