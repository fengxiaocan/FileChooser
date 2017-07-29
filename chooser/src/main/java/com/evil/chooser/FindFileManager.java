package com.evil.chooser;

import android.os.AsyncTask;

import com.evil.chooser.iface.FindFileCallback;
import com.evil.chooser.utils.ChooserFinal;
import com.evil.chooser.utils.SDUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import static com.evil.chooser.ChooserUtils.sChooserString;
import static com.evil.chooser.iface.ChooserType.IMAGE;
import static com.evil.chooser.iface.ChooserType.VIDEO;

/**
 * @项目名： FileChooser
 * @包名： com.evil.chooser
 * @创建者: Noah.冯
 * @时间: 10:54
 * @描述： TODO
 */

class FindFileManager {
    private FindFileManager() {}

    public static FindFileManager build() {
        return new FindFileManager();
    }

    public static final String[] sImage = new String[]{"png", "jpg", "jpeg",
            "gif"};
    public static final String[] sVideo = new String[]{"avi", "rmvb", "rm",
            "wmv", "mp4", "mkv", "flv"};
    private static int mPosition;

    private List<ChooseInfo> mPhotoList;
    private HashMap<String, List<ChooseInfo>> mParentMap;

    public void startFind(FindFileCallback<List<ChooseInfo>, HashMap<String, List<ChooseInfo>>> callback) {
        mPosition = 1;
        mPhotoList = new ArrayList<>();
        mParentMap = new HashMap<>();
        FindTask findTask = new FindTask(callback);
        findTask.execute();
    }

    private class FindTask extends AsyncTask<Object, List<ChooseInfo>, List<ChooseInfo>> {
        private FindFileCallback<List<ChooseInfo>, HashMap<String, List<ChooseInfo>>> mCallback;

        @Override
        protected void onPreExecute() {
        }

        public FindTask(FindFileCallback<List<ChooseInfo>, HashMap<String, List<ChooseInfo>>> callback) {
            this.mCallback = callback;
        }

        @Override
        protected void onPostExecute(List<ChooseInfo> list) {
            mCallback.finish(mPhotoList, mParentMap);
        }

        @Override
        protected void onProgressUpdate(List<ChooseInfo>... values) {
            mCallback.update(mPhotoList, mParentMap);
        }

        @Override
        protected List<ChooseInfo> doInBackground(Object... params) {
            File sdRoot = SDUtils.getSdRoot();
            mParentMap.put(sChooserString, mPhotoList);
            findAV(sdRoot);
            Collections.sort(mPhotoList, new Comparator<ChooseInfo>() {
                @Override
                public int compare(ChooseInfo o1, ChooseInfo o2) {
                    if (o1.getChangeTime() > o2.getChangeTime()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });
            return mPhotoList;
        }

        /**
         * @hide
         */
        public void findAV(File parent) {
            if (parent == null) {
                return;
            }
            File[] files = parent.listFiles();
            if (files == null) {
                return;
            }
            for (File file : files) {
                if (file.isFile()) {
                    if (mPhotoList.size() % 300 == 0) {
                        publishProgress(mPhotoList);
                    }
                    if (file.length() < 10 *1024){
                        continue;
                    }
                    if (ChooserFinal.getChooserConfig().getChooserType().equals(IMAGE)) {
                        if (checkFileSuffix(file, sImage)) {
                            addFile(file, true);
                        }
                    } else if (ChooserFinal.getChooserConfig().getChooserType().equals(VIDEO)) {
                        if (checkFileSuffix(file, sVideo)) {
                            addFile(file, false);
                        }
                    } else {
                        if (checkFileSuffix(file, sImage)) {
                            addFile(file, true);
                        } else {
                            if (checkFileSuffix(file, sVideo)) {
                                addFile(file, false);
                            }
                        }
                    }
                } else {
                    findAV(file);
                }
            }
            return;
        }

        private void addFile(File file, boolean isPhoto) {
            ChooseInfo info = new ChooseInfo();
            info.setSize(file.length());
            info.setChangeTime(file.lastModified());
            info.setPath(file.getAbsolutePath());
            info.setAvId(mPosition++);
            info.setPhoto(isPhoto);
            mPhotoList.add(info);
            if (isPhoto) {
                String parent = file.getParentFile().getName();
                List<ChooseInfo> list = mParentMap.get(parent);
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(info);
                mParentMap.put(parent, list);
            } else {
                List<ChooseInfo> list = mParentMap.get("所有视频");
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(info);
                mParentMap.put("所有视频", list);
            }
        }
    }

    boolean checkFileSuffix(File file, String[] strings) {
        if (file == null) {
            return false;
        }
        if (strings == null) {
            return false;
        }
        String path = file.getAbsolutePath();
        String pa = path.toLowerCase();
        for (String suffix : strings) {
            if (pa.endsWith(suffix)) {
                return true;
            }
        }
        return false;
    }

}
