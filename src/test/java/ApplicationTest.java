import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import demo.wx.pub.app.Application;
import demo.wx.pub.conf.WXProp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lisong on 2017/4/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {
    @Autowired
    private WXProp api;

    @Autowired
    private WXBizMsgCrypt wxBizMsgCrypt;

    @Test
    public void testCSApi() {
        System.out.println(api);
        System.out.println(api.getKey());
        System.out.println(api.getAppId());
        System.out.println(api.getToken());
    }

    @Test
    public void testVerifyUrl() throws AesException {
        wxBizMsgCrypt.verifyUrlWithoutDecrypt("34dc76a9824347e04a0dfec69dfa52207c0df4b4", "1498631554", "1649648946", "5188922958761385914");
    }
}
