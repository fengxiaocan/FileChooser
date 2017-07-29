package com.evil.chooser;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.evil.chooser.iface.RefreshCallback;
import com.evil.chooser.ui.HackyViewPager;

import java.util.ArrayList;
import java.util.List;

import static com.evil.chooser.ChooserUtils.isFinishFind;
import static com.evil.chooser.ChooserUtils.sChooseInfos;
import static com.evil.chooser.ChooserUtils.sSelectChooseInfos;


/**
 * @项目名： FileChooser
 * @包名： com.evil.chooser
 * @创建者: Noah.冯
 * @时间: 16:36
 * @描述： TODO
 */

public class ImageActivity extends BaseActivity implements RefreshCallback, ViewPager.OnPageChangeListener, View.OnClickListener {
    private HackyViewPager mViewPager;
    private ImageView mIvBack;
    private TextView mTvTitle;
//    private TextView mCtvSelect;
//    private RelativeLayout mRlTop;
//    private TextView mTvSelectDir;
//    private CheckBox mRbYuantu;
//    private CheckBox mCheckbox;
//    private RelativeLayout mRlBootom;
    private ImageAdapter mImageAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        initView();
        initData();
    }

    private void initView() {
        mViewPager = (HackyViewPager) findViewById(R.id.view_pager);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
//        mCtvSelect = (TextView) findViewById(R.id.ctv_select);
//        mRlTop = (RelativeLayout) findViewById(R.id.rl_top);
//        mTvSelectDir = (TextView) findViewById(R.id.tv_select_dir);
//        mRbYuantu = (CheckBox) findViewById(R.id.rb_yuantu);
//        mCheckbox = (CheckBox) findViewById(R.id.checkbox);
//        mRlBootom = (RelativeLayout) findViewById(R.id.rl_bootom);
        mIvBack.setOnClickListener(this);
    }

    private void initData() {
        if (ChooserUtils.sLookType == 1){
            mImageAdapter = new ImageAdapter(this, sSelectChooseInfos);
        }else {
            if (isFinishFind) {
                mImageAdapter = new ImageAdapter(this, sChooseInfos);
            } else {
                List<ChooseInfo> list = new ArrayList<>();
                list.addAll(sChooseInfos);
                mImageAdapter = new ImageAdapter(this, list);
            }
        }

        mViewPager.setAdapter(mImageAdapter);
        int position = getIntent().getIntExtra("position", 0);
        mTvTitle.setText((position+1)+"/"+mImageAdapter.getCount());
        mViewPager.setCurrentItem(position);
        ChooserUtils.setRefreshCallback(this);
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    public void refresh() {
        if (ChooserUtils.sLookType == 1){
            return;
        }
        if (ChooserUtils.isSelectAll) {
            mImageAdapter.setData(sChooseInfos);
        }
    }

    @Override
    protected void onDestroy() {
        ChooserUtils.setRefreshCallback(null);
        super.onDestroy();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mTvTitle.setText((position+1)+"/"+mImageAdapter.getCount());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            finish();
        }
    }
}
