package com.xingyun.jiujiugk.common;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 3DES加密、解密工具
 */
public class ThreeDESUtil {
    public static void run2() throws Exception {
        //加密的key
        byte[] key = new String("1234567890abcdefghijklmnopqrst").getBytes();
        //向量
        byte[] keyiv = new String("UbtyQj1M").getBytes();
        //要加密的数据
        byte[] data = new String("1234567890").getBytes("gbk");

        //加密后的16进制数据
        byte[] str5 = des3EncodeCBC(key, keyiv, data);
        for (int i = 0; i < str5.length; i++) {
            //拼成字符串上传到服务器
            System.out.print(Integer.toHexString(str5[i] & 0xff) + "\t");
        }
        byte[] str6 = des3DecodeCBC(key, keyiv, str5);
//        System.out.println("str5:" + new BASE64Encoder().encode(str5));
//        System.out.println("str6:" + new String(str6, "utf-8"));

        System.out.println("\nphp");
        ///////////////////////////////
        byte[] str7 = {0x34, 0x02, (byte) 0xE7, 0x6f, 0x7b, (byte) 0xce, (byte) 0xe4, 0x4f, (byte) 0xd9, 0x0e, (byte) 0x92, (byte) 0xd1, 0x53, (byte) 0xe5, (byte) 0xf8, 0x61};
        //3402e76f7bcee44fd90e92d153e5f861
//        System.out.println("str7:" + new BASE64Encoder().encode(str7));
        byte[] str8 = des3DecodeCBC(key, keyiv, str7);
        System.out.println("str8:" + new String(str8, "utf-8"));

//        byte[] bytes = hexStringToBytes("3402e76f7bcee44fd90e92d153e5f861");
//        System.out.println(new BASE64Encoder().encode(bytes));
    }

    /**
     * 3Des加密
     *
     * @param keyIV
     * @param data
     * @return
     */
    public static String des3EncodeCBC(String keyIV, String data) {
        return des3EncodeCBC(ConstantValue.DESKEY, keyIV, data);
    }


    /**
     * 3Des加密
     *
     * @param key
     * @param keyiv
     * @param data
     * @return
     */
    public static String des3EncodeCBC(String key, String keyiv, String data) {
        try {
            byte[] keyByte = key.getBytes();
            byte[] keyivByte = keyiv.getBytes();
            byte[] dataByte = null;
            if (data.equals(new String(data.getBytes("utf-8")))) {
                dataByte = data.getBytes("utf-8");
            } else if (data.equals(new String(data.getBytes("gbk")))) {
                dataByte = data.getBytes("gbk");
            }
            byte[] result = des3EncodeCBC(keyByte, keyivByte, dataByte);
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < result.length; i++) {
                //拼成字符串上传到服务器
                String tempCode = Integer.toHexString(result[i] & 0xff);
                buffer.append(tempCode.length() == 1 ? "0" + tempCode : tempCode);
            }
            return buffer.toString();
        } catch (Exception e) {
            MyLog.e(e.toString(), e);
        }
        return null;
    }

    /**
     * CBC加密
     *
     * @param key   密钥
     * @param keyiv IV
     * @param data  明文
     * @return Base64编码的密文
     * @throws Exception
     */
    private static byte[] des3EncodeCBC(byte[] key, byte[] keyiv, byte[] data)
            throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(keyiv);
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        // byte[] temp = cipher.update(data);
        byte[] bOut = cipher.doFinal(data);
        return bOut;
    }


    /**
     * 解密
     */
    public static String des3DecodeCBC(String keyiv, String data) {
        return des3DecodeCBC(ConstantValue.DESKEY, keyiv, data);
    }

    /**
     * 3Des解密
     *
     * @param key
     * @param keyiv
     * @param data
     * @return
     */
    public static String des3DecodeCBC(String key, String keyiv, String data) {
        try {
            byte[] keyByte = key.getBytes();
            byte[] keyivByte = keyiv.getBytes();
            byte[] dataByte = convertHexStringToBytes(data);
            return new String(des3DecodeCBC(keyByte, keyivByte, dataByte), "utf-8");
        } catch (Exception e) {
            MyLog.e(e.toString(), e);
        }
        return null;
    }

    /**
     * CBC解密
     *
     * @param key   密钥
     * @param keyiv IV
     * @param data  Base64编码的密文
     * @return 明文
     * @throws Exception
     */
    private static byte[] des3DecodeCBC(byte[] key, byte[] keyiv, byte[] data)
            throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(keyiv);
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
        //byte[] temp = cipher.update(data);
        byte[] bOut = cipher.doFinal(data);
        return bOut;
    }

    /**
     * 根据16进制字符串获取字符串原来对应的byte数组
     *
     * @param hexString 16进制的字符串
     * @return
     */
    public static byte[] convertHexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * 获取字符在16进制中对应的byte
     *
     * @param c
     * @return
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
