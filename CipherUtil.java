package com.example.xsa;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑
 * 　　　　┃　　　┃代码无BUG！
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━━━━━━
 */

public final class CipherUtil {

    private static final String KEY_ALGORITHM = "DESede";

    private static final String DEFAULT_CIPHER_ALGORITHM = "DESede/ECB/PKCS5Padding";

    /**
     * 生成md5
     *
     * @param message
     * @return
     */
    public static byte[] getMD5(String message) {
        //String md5str = "";
        byte[]buff = new byte[16];
        try {
            // 1 创建一个提供信息摘要算法的对象，初始化为md5算法对象
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 2 将消息变成byte数组
            byte[] input = message.getBytes();

            // 3 计算后获得字节数组,这就是那128位了
            buff = md.digest(input);

            // 4 把数组每一字节（一个字节占八位）换成16进制连成md5字符串
            //md5str = bytesToHex(buff);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return buff;
    }

    /**
     * 二进制转十六进制
     *
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer md5str = new StringBuffer();
        // 把数组每一字节换成16进制连成md5字符串
        int digital;
        for (int i = 0; i < bytes.length; i++) {
            digital = bytes[i];

            if (digital < 0) {
                digital += 256;
            }
            if (digital < 16) {
                md5str.append("0");
            }
            md5str.append(Integer.toHexString(digital));
        }
        return md5str.toString().toUpperCase();
    }

    /***
     * encode by Base64
     */
    public static String encodeBase64(byte[]input) throws Exception{
        return Base64.encodeToString(input, Base64.DEFAULT);
    }
    
    /***
     * decode by Base64
     */
    public static byte[] decodeBase64(String input) throws Exception{
        return Base64.decode(input,Base64.DEFAULT);
    }

    /**
     * 3DES获取密钥
     * 转换密钥
     *
     * @param key	二进制密钥
     * @return Key	密钥
     * @throws Exception
     */
    private static Key toKey(byte[] key) throws Exception{
        //实例化DES密钥规则
        DESedeKeySpec dks = new DESedeKeySpec(key);
        //实例化密钥工厂
        SecretKeyFactory skf = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        //生成密钥
        SecretKey secretKey = skf.generateSecret(dks);
        return secretKey;
    }

    /**
     * 3DES
     * 加密
     *
     * @param data	待加密数据
     * @param key	密钥
     * @return byte[]	加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data,Key key) throws Exception{
        return encrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
    }

    /**
     * 加密
     *
     * @param data	待加密数据
     * @param key	二进制密钥
     * @return byte[]	加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data,byte[] key) throws Exception{
        return encrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
    }


    /**
     * 加密
     *
     * @param data	待加密数据
     * @param key	二进制密钥
     * @param cipherAlgorithm	加密算法/工作模式/填充方式
     * @return byte[]	加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data,byte[] key,String cipherAlgorithm) throws Exception{
        //还原密钥
        Key k = toKey(key);
        return encrypt(data, k, cipherAlgorithm);
    }

    /**
     * 加密
     *
     * @param data	待加密数据
     * @param key	密钥
     * @param cipherAlgorithm	加密算法/工作模式/填充方式
     * @return byte[]	加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data,Key key,String cipherAlgorithm) throws Exception{
        //实例化
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        //使用密钥初始化，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, key);
        //执行操作
        return cipher.doFinal(data);
    }

    /**
     * 解密
     *
     * @param data	待解密数据
     * @param key	二进制密钥
     * @return byte[]	解密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data,byte[] key) throws Exception{
        return decrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
    }

    /**
     * 解密
     *
     * @param data	待解密数据
     * @param key	密钥
     * @return byte[]	解密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data,Key key) throws Exception{
        return decrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
    }

    /**
     * 解密
     *
     * @param data	待解密数据
     * @param key	二进制密钥
     * @param cipherAlgorithm	加密算法/工作模式/填充方式
     * @return byte[]	解密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data,byte[] key,String cipherAlgorithm) throws Exception{
        //还原密钥
        Key k = toKey(key);
        return decrypt(data, k, cipherAlgorithm);
    }

    /**
     * 解密
     *
     * @param data	待解密数据
     * @param key	密钥
     * @param cipherAlgorithm	加密算法/工作模式/填充方式
     * @return byte[]	解密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data,Key key,String cipherAlgorithm) throws Exception{
        //实例化
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        //使用密钥初始化，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, key);
        //执行操作
        return cipher.doFinal(data);
    }
}
