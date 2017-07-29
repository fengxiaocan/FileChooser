package com.evil.chooser;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.GridView;

import com.evil.chooser.iface.FindFileCallback;
import com.evil.chooser.pmsion.CheckPermissionsCallback;
import com.evil.chooser.pmsion.RequestPermissionsCallback;
import com.evil.chooser.utils.ChooserFinal;

import java.util.HashMap;
import java.util.List;

/**
 * @项目名： FileChooser
 * @包名： com.evil.chooser
 * @创建者: Noah.冯
 * @时间: 18:00
 * @描述： TODO
 */

public class FileChooserFragment extends BaseFragment implements RequestPermissionsCallback, CheckPermissionsCallback, ActivityCompat.OnRequestPermissionsResultCallback {

    private GridView mGridView;
    private FileChooserAdapter mAdapter;

    @Override
    public int inflaterRes() {
        return R.layout.fragment_photo_chooser;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mGridView = (GridView) view.findViewById(R.id.grid_view);
        mAdapter = new FileChooserAdapter(getContext());
        mAdapter.bindAbsListView(mGridView);
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        requestPermissions(permissions, 0x12A);
    }

    @Override
    public void requestPremissionsSuccess(int requestCode) {
        FindFileManager.build().startFind(new FindFileCallback<List<ChooseInfo>, HashMap<String, List<ChooseInfo>>>() {
            @Override
            public void update(List<ChooseInfo> list, HashMap<String, List<ChooseInfo>> map) {
                if (ChooserUtils.sChooseInfos == null) {
                    ChooserUtils.sChooseInfos = list;
                }
                if (ChooserUtils.sParentMap == null) {
                    ChooserUtils.sParentMap = map;
                }
                if (ChooserUtils.isSelectAll) {
                    mAdapter.setData(list);
                }
            }

            @Override
            public void finish(List<ChooseInfo> list, HashMap<String, List<ChooseInfo>> map) {
                ChooserUtils.sChooseInfos = list;
                ChooserUtils.finishFinish();
                if (ChooserUtils.sRefreshCallback != null) {
                    ChooserUtils.sRefreshCallback.refresh();
                }
                if (ChooserUtils.isSelectAll) {
                    mAdapter.setData(list);
                }
            }
        });
    }

    @Override
    public void requestPremissionsDefeat(int requestCode, @Nullable String[] permissions) {
        ChooserFinal.onChooseFailure("获取读写sd卡权限失败!");
        getActivity().finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        RequestPermissionsUtils.checkPermissions(getActivity(), requestCode, permissions, this);
    }

    @Override
    public void hasPremissions(int requestCode) {
        requestPremissionsSuccess(requestCode);
    }

    @Override
    public void nonePremissions(int requestCode, @Nullable String[] permissions) {
        requestPremissionsDefeat(requestCode, permissions);
    }

    public void choose(List<ChooseInfo> chooseInfos){
        mAdapter.setData(chooseInfos);
    }
}
