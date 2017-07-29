package com.evil.chooser;

import android.widget.Toast;

import com.evil.chooser.utils.ChooserFinal;

/**
 * @项目名： FileChooser
 * @包名： com.evil.chooser
 * @创建者: Noah.冯
 * @时间: 16:07
 * @描述： TODO
 */

class ToastUtils {
    private ToastUtils() {
    }

    private static Toast sToast;

    public static void showShort(CharSequence sequence){
        if (sToast == null){
            sToast = Toast.makeText(ChooserFinal.getContext(),sequence,Toast.LENGTH_SHORT);
        }else{
            sToast.setText(sequence);
        }
        sToast.show();
    }
    public static void showLong(CharSequence sequence){
        if (sToast == null){
            sToast = Toast.makeText(ChooserFinal.getContext(),sequence,Toast.LENGTH_LONG);
        }else{
            sToast.setText(sequence);
        }
        sToast.show();
    }
}
