package demo.wx.pub.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * SHA1 
 * <p>
 *
 */
public class SHA1 {

    private static final Logger log = LoggerFactory.getLogger(SHA1.class);

    /**
     * 用SHA1算法生成安全签名
     * param token 票据
     * param timestamp 时间戳
     * param nonce 随机字符串
     * param encrypt 密文
     *
     * @return 安全签名
     */
    public static String getSHA1(String... params) {
        StringBuilder sb = new StringBuilder();
        // 字符串排序
        Arrays.sort(params);
        for (String item : params) {
            sb.append(item);
        }
        String str = sb.toString();
        // SHA1签名生成
        MessageDigest md;
        String s = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            s = byte2hex(md.digest());
            log.debug(s);
        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
            log.error(e.getMessage());
        }

        return s;

    }

    /**
     * 微信版 字节数组 转 十六进制字符串
     * @param digest 字节数组
     * @return 十六进制字符串
     */
    private static String byte2hex(byte[] digest) {
        StringBuilder hexstr = new StringBuilder();
        String shaHex;
        for (byte aDigest : digest) {
            shaHex = Integer.toHexString(aDigest & 0xFF);
            if (shaHex.length() < 2) {
                hexstr.append(0);
            }
            hexstr.append(shaHex);
        }
        return hexstr.toString();
    }
}
