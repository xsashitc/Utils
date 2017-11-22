package com.example.xsa;

import android.content.Context;

/**
 * Created by apple on 2017/8/19.
 */

public class DimenUtil {

    public static int dip2px(Context context, int dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int px2dip(Context context, int px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}
