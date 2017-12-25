package com.mortintech.poseidon.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author xsa
 * @package com.mortintech.poseidon.Util
 * @fileName SharedPreferenceUtil
 * @date on 2017/12/18 14:13
 */


public final class SharedPreferenceUtil {
    private static final String TAG = "SharedPreferenceUtil";

    public static void saveString(Context context,String spfName,String key,String value){
        SharedPreferences.Editor editor = context.getSharedPreferences(spfName,Context.MODE_PRIVATE).edit();
        editor.putString(key,value);
        editor.apply();
    }

    public static String getStringByName(Context context, String spfName, String key, String defaultValue) {
        SharedPreferences spf = context.getSharedPreferences(spfName, Context.MODE_PRIVATE);
        return spf.getString(key, defaultValue);
    }

    public static String getStringByName(Context context, String spfName, String key) {
        return getStringByName(context, spfName, key, "");
    }

    public static void saveInt(Context context,String spfName,String key,int value){
        SharedPreferences.Editor editor = context.getSharedPreferences(spfName,Context.MODE_PRIVATE).edit();
        editor.putInt(key,value);
        editor.apply();
    }

    public static int getIntByName(Context context, String spfName, String key, int defaultValue) {
        SharedPreferences spf = context.getSharedPreferences(spfName, Context.MODE_PRIVATE);
        return spf.getInt(key, defaultValue);
    }

    public static int getIntByName(Context context, String spfName, String key) {
        return getIntByName(context, spfName, key, 0);
    }

    public static void saveBoolean(Context context,String spfName,String key,boolean value){
        SharedPreferences.Editor editor = context.getSharedPreferences(spfName,Context.MODE_PRIVATE).edit();
        editor.putBoolean(key,value);
        editor.apply();
    }

    public static boolean getBooleanByName(Context context,String spfName,String key,boolean defaultValue){
        SharedPreferences spf = context.getSharedPreferences(spfName,Context.MODE_PRIVATE);
        return spf.getBoolean(key,defaultValue);
    }

    public static boolean getBooleanByName(Context context,String spfName,String key){
        return getBooleanByName(context,spfName,key,false);
    }

    public static void saveSerizable(Context context,String spfName,String key,Serializable value){
        if(value != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(spfName, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(value);//把对象写到流里
                String temp = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
                editor.putString(key, temp);
                editor.apply();
            } catch (IOException e) {
                LogUtil.e(TAG, "IOException", e);
            }
        }
    }

    public static Serializable getSerializableByName(Context context,String spfName,String key){
        SharedPreferences spf=context.getSharedPreferences(spfName,Context.MODE_PRIVATE);
        String temp = spf.getString(key, "");
        ByteArrayInputStream bais =  new ByteArrayInputStream(Base64.decode(temp.getBytes(), Base64.DEFAULT));
        Serializable result = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            result = (Serializable) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            result = null;
        }
        return result;
    }

    public static void removeDataByName(Context context,String spfName,String key){
        SharedPreferences.Editor editor = context.getSharedPreferences(spfName,Context.MODE_PRIVATE).edit();
        editor.remove(key).apply();
    }
}
