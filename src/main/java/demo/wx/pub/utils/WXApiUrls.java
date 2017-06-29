package demo.wx.pub.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class WXApiUrls {

    private static final String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    private static final String MENU_SAVE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";

    private static final String MENU_DELETE = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=%s";

    private static final String MENU_QUERY = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=%s";

    private static final String SNSAPI_BASE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_base&state=%s#wechat_redirect";
    public static String getAccessToken(String appId, String secret) {
        return String.format(ACCESS_TOKEN, appId, secret);
    }

    public static String getMenuSaveUrl() {
        return String.format(MENU_SAVE, getAccessToken());
    }

    public static String getMenuDeleteUrl() {
        return String.format(MENU_DELETE, getAccessToken());
    }

    public static String getMenuQueryUrl() {
        return String.format(MENU_QUERY, getAccessToken());
    }

    public static String getPageBase(String appId, String url, String other) {
        try {
            return String.format(SNSAPI_BASE, appId, URLEncoder.encode(url, "utf-8"), other);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static String getAccessToken() {
        return "FjvJIIPvGGQ9NlE4ofo8EHY_VztMKswXYNRbQsDsJIl9Ml7Df6UiojkPpxSFtuypzH7NCKEH5VwUIp8M4dtnIjBdaVPsfdA9IM6yAqRgGMf7K7HVvmbgfgAfTWSKHZXeNQTfAHASVY";
    }
}
