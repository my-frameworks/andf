package com.and.framework.common.utils;

import android.content.Context;
import android.widget.Toast;


/**
 * Created by user on 2017/10/10.
 */

public class ToastUtils {

    /**
     * 显示浮动提示信息
     *
     * @param msg
     */
//    public static void showToast(String msg) {
//        if (TextUtils.isEmpty(msg)) {
//            return;
//        }
//        Toast.makeText(BaseApplication.get(), msg, Toast.LENGTH_SHORT).show();
//    }

    /**
     * 显示浮动提示信息
     *
     * @param context
     * @param msg
     */
    public static void showToast(Context context, String msg) {
        if (msg == null||context==null) {
            return;
        }
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示浮动提示信息
     *
     * @param context
     * @param msg
     */
    public static void showToast(Context context, int msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
