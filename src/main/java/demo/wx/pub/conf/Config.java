package demo.wx.pub.conf;


import demo.wx.pub.utils.AccessTokenUtils;
import demo.wx.pub.utils.TemplateUtils;
import demo.wx.pub.utils.WXApiUrls;
import demo.wx.pub.utils.WXNoneCryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    private final WXProp wxProp;

    @Autowired
    public Config(WXProp wxProp) {
        this.wxProp = wxProp;
    }

    @Bean
    public WXNoneCryptUtils wxNoneCrpytUtil() {
        return new WXNoneCryptUtils(wxProp.getToken());
    }

    @Bean
    public AccessTokenUtils accessTokenUtils() {
        AccessTokenUtils accessTokenUtils = new AccessTokenUtils(wxProp.getAppId(), wxProp.getSecret());
        WXApiUrls.addAccessToken(accessTokenUtils.getAccessToken());
        return accessTokenUtils;
    }

    @Bean
    public TemplateUtils templateUtils() {
        return new TemplateUtils();
    }
}
