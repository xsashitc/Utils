package com.xsa.ballgame;

import java.util.Random;

/**
 * @author xsa
 * @package com.xsa.ballgame
 * @fileName ColorUtil
 * @date on 2017/12/26 10:43
 */


public class ColorUtil {
    
    /**
     * 随机颜色，返回rgb字符串，不带#
     */
    public static String getRandColorCode(){
        String r,g,b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();

        r = r.length()==1 ? "0" + r : r ;
        g = g.length()==1 ? "0" + g : g ;
        b = b.length()==1 ? "0" + b : b ;

        return r+g+b;
    }
}
