package demo.wx.pub.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 微信签名生成方法
 * <p>
 */
public class Sign {

    private static final String ALGORITHM_MD5 = "MD5";

    private static final String ALGORITHM_SHA1 = "SHA-1";

    private static final Logger log = LoggerFactory.getLogger(Sign.class);

    /**
     * 用SHA1算法生成安全签名
     * param token 票据
     * param timestamp 时间戳
     * param nonce 随机字符串
     * param encrypt 密文
     *
     * @return 安全签名
     */
    public static String signBySHA1(String... params) {
        StringBuilder sb = new StringBuilder();
        // 字符串排序
        Arrays.sort(params);
        for (String item : params) {
            sb.append(item);
        }
        return sign(ALGORITHM_SHA1, sb.toString());
    }

    public static String signByMD5(String key, Map<String, String> params) {
        List<String> list = new ArrayList<>();
        list.addAll(params.keySet());
        list.sort(String::compareTo);
        StringBuilder sb = new StringBuilder();
        for (String k : list) {
            String val = params.get(k);
            if (StringUtils.hasLength(val)) {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(k);
                sb.append("=");
                sb.append(val);
            }
        }
        String stringSignTemp = String.format("%s&key=%s", sb.toString(), key);
        return sign(ALGORITHM_MD5, stringSignTemp).toUpperCase();
    }

    /**
     * @param algorithm 签名算法
     * @param source    签名参数
     * @return 签名结果
     */
    private static String sign(String algorithm, String source) {

        // 签名生成
        MessageDigest md;
        String s = null;
        try {
            md = MessageDigest.getInstance(algorithm);
            md.update(source.getBytes());
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
     *
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
