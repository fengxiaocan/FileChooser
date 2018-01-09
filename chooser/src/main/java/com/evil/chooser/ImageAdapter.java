package com.evil.chooser;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.evil.chooser.iface.ImageLoader;
import com.evil.chooser.utils.ChooserFinal;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.File;
import java.util.List;

/**
 * @项目名： FileChooser
 * @包名： com.evil.chooser
 * @创建者: Noah.冯
 * @时间: 16:48
 * @描述： TODO
 */

class ImageAdapter extends PagerAdapter {
    private Context mContext;
    private List<ChooseInfo> mData;

    public ImageAdapter(Context context, List<ChooseInfo> data) {
        mContext = context;
        mData = data;
    }

    public void setData(List<ChooseInfo> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public List<ChooseInfo> getData() {
        return mData;
    }

    @Override
    public int getCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container,final int position) {
        final ViewHolder holder;
        View inflate;

        ChooseInfo info = mData.get(position);
        if (info.isPhoto()) {
            inflate = View.inflate(mContext, R.layout.view_pager_image, null);
            holder = new ViewHolder(inflate);
        } else {
            inflate = View.inflate(mContext, R.layout.view_pager_video, null);
            holder = new ViewHolder(inflate);
            holder.mIvPlay.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    ChooseInfo info1 = mData.get(position);
                    Intent intent = getVideoFileIntent(info1.getPath());
                    mContext.startActivity(intent);
                }
            });
        }
        ImageLoader imageLoader = ChooserFinal.getChooserConfig().getImageLoader();
        imageLoader.loadFile(mContext, info.getPath(),R.drawable.image,R.drawable.error_load, holder.mPhotoView);
        container.addView(inflate);

        return inflate;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(((View) object));
    }

    public static class ViewHolder {
        public View rootView;
        public PhotoView mPhotoView;
        public ImageView mIvPlay;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mPhotoView = (PhotoView) rootView.findViewById(R.id.photo_view);
            this.mIvPlay = (ImageView) rootView.findViewById(R.id.iv_play);
        }

    }

    //Android获取一个用于打开VIDEO文件的intent
    public static Intent getVideoFileIntent(String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "video/*");
        return intent;
    }
}
