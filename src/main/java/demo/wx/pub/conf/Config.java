package demo.wx.pub.conf;


import demo.wx.pub.utils.AccessTokenUtils;
import demo.wx.pub.utils.TemplateUtils;
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
        return new AccessTokenUtils(wxProp.getAppId(), wxProp.getSecret());
    }

    @Bean
    public TemplateUtils templateUtils() {
        return new TemplateUtils();
    }
}
