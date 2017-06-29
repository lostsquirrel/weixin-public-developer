package demo.wx.pub.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class AccessTokenUtils {

    private static final Logger log = LoggerFactory.getLogger(AccessTokenUtils.class);

    private String appId;

    private String secret;

    private String accessToken;

    /**
     * access_token 获取时的时间
     */
    private Long expireOffset;

    /**
     * access_token 过期时间，7200秒（2个小时）
     */
    private int expireTime = 7200;

    public AccessTokenUtils(String appId, String secret) {
        this.appId = appId;
        this.secret = secret;
    }

    private void createAccessToken() {
        synchronized (AccessTokenUtils.class) {
            expireOffset = new Date().getTime() / 1000;
            String accessTokenUrl = WXApiUrls.getAccessTokenUrl(appId, secret);

            Object res = HttpUtils.getAsString(accessTokenUrl);
            JSONObject obj = JSON.parseObject(res.toString());
            log.debug(obj.toString());
            this.accessToken = obj.getString("access_token");
            expireTime = obj.getInteger("expires_in");
        }
    }

    public String getAccessToken() {
        synchronized (AccessTokenUtils.class) {
            if (accessToken == null || isExpired()) {
                createAccessToken();
            }
            return accessToken;
        }
    }

    private boolean isExpired() {
        Long current = new Date().getTime() / 1000;
        return  current - expireOffset >= expireTime;
    }
}
