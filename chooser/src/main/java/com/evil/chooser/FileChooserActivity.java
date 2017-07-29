package com.evil.chooser;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.evil.chooser.iface.ChooseCallback;
import com.evil.chooser.iface.SelectCallback;
import com.evil.chooser.utils.AnimationUtils;
import com.evil.chooser.utils.ChooserFinal;
import com.evil.chooser.utils.FragmentUtils;

import java.util.List;

import static android.view.View.VISIBLE;
import static com.evil.chooser.ChooserUtils.sChooserString;
import static com.evil.chooser.iface.ChooserType.IMAGE;
import static com.evil.chooser.iface.ChooserType.VIDEO;

/**
 * @项目名： FileChooser
 * @包名： com.evil.chooser
 * @创建者: Noah.冯
 * @时间: 11:56
 * @描述： TODO
 */

public class FileChooserActivity extends BaseActivity implements View.OnClickListener, SelectCallback, ChooseCallback {
    private TextView mTvTitle;
    private LinearLayout mContent;
    //    private TextView mTvDay;
    private TextView mTvSelectDir;
//    private CheckBox mRbYuantu;
    private CheckedTextView mCtvLook;
    private CheckedTextView mCtvSelect;
    private View mBg;
    private ListView mLv;
    private SelectAdapter mAdapter;
    private FileChooserFragment mChooserFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_chooser);
        initView();
        ChooserUtils.setSelectCallback(this);
        ChooserUtils.isSelectAll = true;
        mChooserFragment = new FileChooserFragment();
        FragmentUtils.addFragment(this, R.id.content, mChooserFragment);
    }

    private void initView() {
        setOnClick(R.id.iv_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mContent = (LinearLayout) findViewById(R.id.content);
        //        mTvDay = (TextView) findViewById(R.id.tv_day);
        mTvSelectDir = (TextView) findViewById(R.id.tv_select_dir);
//        mRbYuantu = (CheckBox) findViewById(R.id.rb_yuantu);
        mCtvLook = (CheckedTextView) findViewById(R.id.ctv_look);
        mCtvSelect = (CheckedTextView) findViewById(R.id.ctv_select);
        mBg = findViewById(R.id.bg);
        mLv = (ListView) findViewById(R.id.lv);
        mAdapter = new SelectAdapter(this);
        mLv.setAdapter(mAdapter);
        setOnClick(mBg);
        setOnClick(mTvSelectDir);
        setOnClick(mCtvSelect);
        setOnClick(mCtvLook);
        if (ChooserFinal.getChooserConfig().getChooserType().equals(IMAGE)) {
            String string = getString(R.string.a_photo_title_photo_text);
            sChooserString = string;
            mTvTitle.setText(string);
            mTvSelectDir.setText(string);
        } else if (ChooserFinal.getChooserConfig().getChooserType().equals(VIDEO)) {
            ChooserFinal.getChooserConfig().setMaxSize(1);
            String string = getString(R.string.a_photo_title_vodeo_text);
            sChooserString = string;
            mTvTitle.setText(string);
            mTvSelectDir.setText(string);
        } else {
            String string = getString(R.string.a_photo_title_av_text);
            sChooserString = string;
            mTvTitle.setText(string);
            mTvSelectDir.setText(string);
        }
        ChooserUtils.setChooseCallback(this);
    }

    public void setOnClick(@IdRes int id) {
        findViewById(id).setOnClickListener(this);
    }

    public void setOnClick(View v) {
        v.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            finish();
        } else if (v.getId() == R.id.ctv_look) {
            ChooserUtils.sLookType = 1;
            if (mCtvLook.isChecked()) {
                startActivity(new Intent(this, ImageActivity.class));
            }
        } else if (v.getId() == R.id.tv_select_dir) {
            if (mLv.getVisibility() == VISIBLE) {
                hide();
            } else {
                show();
            }
        } else if (v.getId() == R.id.bg) {
            hide();
        }else if (v.getId() == R.id.ctv_select) {
            if (mCtvSelect.isChecked()) {
                ChooserFinal.onChooseSuccess(ChooserUtils.sSelectChooseInfos);
                finish();
            }
        }
    }

    private void show() {
        mAdapter.setMap(ChooserUtils.sParentMap);
        AnimationUtils.moveToViewLocation(mLv, 500);
        AnimationUtils.showAlpha(mBg, 500);
    }

    private void hide() {
        AnimationUtils.moveToViewBottom(mLv, 500);
        AnimationUtils.hideAlpha(mBg, 500);
    }

    @Override
    public void selectChange(int number) {
        if (number <= 0) {
            mCtvSelect.setChecked(false);
            mCtvLook.setChecked(false);
            mCtvSelect.setText(getString(R.string.a_photo_select_text));
            mCtvLook.setText(getString(R.string.a_photo_look_text));
        } else {
            mCtvSelect.setChecked(true);
            mCtvLook.setChecked(true);
            mCtvSelect.setText(
                    getString(R.string.a_photo_select_text) + "(" + number +
                    "/" + ChooserFinal.getChooserConfig().getMaxSize() + ")");
            mCtvLook.setText(
                    getString(R.string.a_photo_look_text) + "(" + number + ")");
        }
    }

    @Override
    public void choose(List<ChooseInfo> chooseInfos) {
        hide();
        mAdapter.notifyDataSetChanged();
        ChooserUtils.sChooseInfos = chooseInfos;
        mChooserFragment.choose(chooseInfos);
    }

    @Override
    protected void onDestroy() {
        ChooserUtils.clearAll();
        super.onDestroy();
    }
}
