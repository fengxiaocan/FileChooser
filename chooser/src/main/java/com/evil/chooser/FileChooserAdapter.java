package com.evil.chooser;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.ColorInt;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.evil.chooser.iface.ImageLoader;
import com.evil.chooser.utils.ChooserFinal;

import java.util.ArrayList;
import java.util.List;

import static com.evil.chooser.ChooserUtils.sSelectChooseInfos;


/**
 * @项目名： FileChooser
 * @包名： com.evil.chooser
 * @创建者: Noah.冯
 * @时间: 18:06
 * @描述： TODO
 */

class FileChooserAdapter extends BaseAdapter implements AbsListView.OnScrollListener {
    //定义当前listview是否在滑动状态
    protected boolean mLvScrollState = false;
    protected Context mContext;
    protected List<ChooseInfo> mData;
    protected
    @ColorInt
    int mSelectColor;
    protected
    @ColorInt
    int mUnSelectColor;
    public int sSelectNumber;

    public FileChooserAdapter(Context context) {
        sSelectNumber = 0;
        mContext = context;
        mSelectColor = context.getResources().getColor(R.color.f_photo_select_screen_bg);
        mUnSelectColor = context.getResources().getColor(R.color.f_photo_unselect_screen_bg);
    }

    public void bindAbsListView(AbsListView listView) {
        listView.setAdapter(this);
        listView.setOnScrollListener(this);
    }

    public List<ChooseInfo> getData() {
        return mData;
    }

    public void setData(List<ChooseInfo> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void addData(List<ChooseInfo> data) {
        if (mData == null) {
            mData = data;
        } else {
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void addData(ChooseInfo data) {
        if (mData != null) {
            mData.add(data);
        } else {
            mData = new ArrayList<>();
            mData.add(data);
        }
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public ChooseInfo getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChooseInfo info = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_photo_chooser, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mCheckbox.setChecked(info.isSelect());
        holder.mCheckbox.setOnClickListener(new OnClickListener(info, holder.mCheckbox));
        holder.mCheckbox.setOnCheckedChangeListener(new OnCheckedChange(holder.mViewScreen));
        ImageLoader imageLoader = ChooserFinal.getChooserConfig().getImageLoader();
        if (mLvScrollState) {
            holder.mIvImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageLoader.loadRes(mContext, R.drawable.image, holder.mIvImage);
        } else {
            holder.mIvImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageLoader.loadFile(mContext, info.getPath(), R.drawable.image, R.drawable.error_load, holder.mIvImage);
        }
        holder.rootView.setOnClickListener(new OnItemClick(position));
        return convertView;
    }

    private class OnItemClick implements View.OnClickListener {
        int position;

        public OnItemClick(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            ChooserUtils.sLookType = 0;
            Intent intent = new Intent(mContext, ImageActivity.class);
            intent.putExtra("position", position);
            mContext.startActivity(intent);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE://停止滚动
                //设置为停止滚动
                mLvScrollState = false;
                notifyDataSetChanged();
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_FLING://滚动做出了抛的动作
                //设置为正在滚动
                mLvScrollState = true;
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL://正在滚动
                //设置为正在滚动
                mLvScrollState = true;
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    }

    private class OnClickListener implements View.OnClickListener {
        private ChooseInfo mChooseInfo;
        private CheckBox mCheckBox;

        public OnClickListener(ChooseInfo ChooseInfo, CheckBox checkBox) {
            mChooseInfo = ChooseInfo;
            mCheckBox = checkBox;
        }

        @Override
        public void onClick(View v) {
            int maxSize = ChooserFinal.getChooserConfig().getMaxSize();
            if (sSelectNumber >= maxSize) {
                if (!mChooseInfo.isSelect()) {
                    mCheckBox.setChecked(false);
                    ToastUtils.showShort(mContext.getResources().getString(R.string.a_photo_select_max_text, maxSize));
                    return;
                }
            }
            if (mChooseInfo.isSelect()) {
                sSelectChooseInfos.remove(mChooseInfo);
                sSelectNumber--;
            } else {
                sSelectChooseInfos.add(mChooseInfo);
                sSelectNumber++;
            }
            mChooseInfo.setSelect(!mChooseInfo.isSelect());
            ChooserUtils.sSelectCallback.selectChange(sSelectNumber);
        }
    }

    private class OnCheckedChange implements CompoundButton.OnCheckedChangeListener {
        private View mScreenView;

        public OnCheckedChange(View screenView) {
            mScreenView = screenView;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                mScreenView.setBackgroundColor(mSelectColor);
            } else {
                mScreenView.setBackgroundColor(mUnSelectColor);
            }
        }
    }


    public static class ViewHolder {
        public View rootView;
        public ImageView mIvImage;
        public CheckBox mCheckbox;
        public View mViewScreen;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mIvImage = (ImageView) rootView.findViewById(R.id.iv_image);
            this.mCheckbox = (CheckBox) rootView.findViewById(R.id.checkbox);
            this.mViewScreen = rootView.findViewById(R.id.view_screen);
        }
    }
}
