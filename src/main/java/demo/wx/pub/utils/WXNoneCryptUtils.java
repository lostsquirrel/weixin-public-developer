package demo.wx.pub.utils;

public class WXNoneCryptUtils {

    private String token;

    public WXNoneCryptUtils(String token) {
        this.token = token;
    }

    public boolean verify(String msgSignature, String timeStamp, String nonce) {
        String signature = Sign.signBySHA1(token, timeStamp, nonce);
        return signature.equals(msgSignature);
    }
}
