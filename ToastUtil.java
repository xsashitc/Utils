package com.example.xsa;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by xsa on 2017/9/19.
 */

public class ToastUtil {
    private static Toast toast;

    public static void makeText(Context context, String message){
        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }
}
