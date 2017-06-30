package demo.wx.test;

import demo.wx.pub.utils.WXApiUrls;
import org.junit.BeforeClass;

public class WXTestBase {
    @BeforeClass
    public static void init() {
        WXApiUrls.addAccessToken(AccessTokenUtilsTest.ACCESS_TOKEN);
    }
}
