package com.mortintech.poseidon.Util;

import android.text.TextUtils;

/**
 * @author xsa
 * @package com.mortintech.poseidon.Util
 * @fileName StringUtil
 * @date on 2017/12/18 12:01
 */


public final class StringUtil {

    /**
     * 判断字符串是不是一个ip地址
     *
     * @param ip 源字符串
     * @return
     */
    public static boolean isIp(String ip){
        if (TextUtils.isEmpty(ip)){
            return false;
        }

        String checkIp = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."

                        +"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."

                        +"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."

                        +"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        return ip.matches(checkIp);
    }

    /**
     * 判断字符串是不是一个手机号码
     *
     * @param telNum 源字符串
     * @return
     */
    public static boolean isTelphoneNumber(String telNum){
        if (TextUtils.isEmpty(telNum)){
            return false;
        }

        String checkNum = "^[1][3,4,5,7,8][0-9]{9}$";
        return telNum.matches(checkNum);
    }

    public static boolean isVertificationCode(String vertificationCode){
        if (TextUtils.isEmpty(vertificationCode) ||!TextUtils.isDigitsOnly(vertificationCode)){
            return false;
        }
        return vertificationCode.length() == 6;
    }

    public static boolean isDigitsOnly(String s){
        if (TextUtils.isEmpty(s) && !TextUtils.isDigitsOnly(s)){
            return false;
        } else {
            return true;
        }
    }
}
