package demo.cognitive.conf;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
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

    @Bean public WXBizMsgCrypt wxBizMsgCrypt() throws AesException {
        return new WXBizMsgCrypt(wxProp.getToken(), wxProp.getAppId());
    }
}
