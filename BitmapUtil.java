package com.xsa.editmenu.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * @author xsa
 * @package com.xsa.editmenu.util
 * @fileName BitmapUtil
 * @date on 2018/2/25 14:06
 */


public class BitmapUtil {

    /**
     * bitmap缩放为指定大小
     *
     * @param origin 原图
     * @param newWidth 新图宽
     * @param newHeight 新图高
     * @return 新图片
     */
    public static Bitmap scaleBitmap(Bitmap origin,int newWidth,int newHeight){
        if (origin == null) {
            return null;
        }
        int height = origin.getHeight();
        int width = origin.getWidth();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        // 使用后乘
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (!origin.isRecycled()) {
            origin.recycle();
        }
        return newBM;
    }

    /**
     * bitmap按比例缩放
     *
     * @param origin 原图
     * @param ratio 缩放比例
     * @return 新图片
     */
    private Bitmap scaleBitmap(Bitmap origin, float ratio) {
        if (origin == null) {
            return null;
        }
        int width = origin.getWidth();
        int height = origin.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(ratio, ratio);
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (newBM.equals(origin)) {
            return newBM;
        }
        origin.recycle();
        return newBM;
    }
}
