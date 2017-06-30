package demo.wx.pub.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class WXApiUrls {

    private static final String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    private static final String MENU_SAVE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";

    private static final String MENU_DELETE = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=%s";

    private static final String MENU_QUERY = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=%s";

    private static final String SNSAPI_BASE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_base&state=%s#wechat_redirect";

    private static final String QUERY_INDUSTRY = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=%s";

    private static final String SET_INDUSTRY = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=%s";

    private static final String WEB_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

    private static final String TEMPLATE_LIST = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=%s";

    private static final String TEMPLATE_SEND = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";

    public static String getAccessTokenUrl(String appId, String secret) {
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

    public static String getQueryIndustryUrl() {
        return String.format(QUERY_INDUSTRY, getAccessToken());
    }

    public static String getSetIndustryUrl() {
        return String.format(SET_INDUSTRY, getAccessToken());
    }

    public static String getTemplateSendUrl() {
        return String.format(TEMPLATE_SEND, getAccessToken());
    }

    public static String getTemplateListUrl() {
        return String.format(TEMPLATE_LIST, getAccessToken());
    }

    public static String getWebAccessTokenUrl(String appId, String secret, String code) {
        return String.format(WEB_ACCESS_TOKEN, appId, secret, code);
    }

    public static String getBaseAuth(String appId, String url, String other) {
        try {
            return String.format(SNSAPI_BASE, appId, URLEncoder.encode(url, "utf-8"), other);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getAccessToken() {
        return "c6v02KfKrHhw7QljBAeOQd3HmLcD3n1Xkdi7ie9VMZIpAXvefzXkwTYfX9Fpk7fSoQARMLCRJhfZICyYOKTpkjrPINUwbo9OUs1UjmJwU_7cibepZ9hSGdFo_xVKmO9kKTOdAFAENI";
    }
}
