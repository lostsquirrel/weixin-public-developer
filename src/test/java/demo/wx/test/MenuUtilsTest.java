package demo.wx.test;

import demo.wx.pub.utils.AccessTokenUtils;
import demo.wx.pub.utils.MenuUtils;
import demo.wx.pub.utils.WXApiUrls;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class MenuUtilsTest {

    private static final Logger log = LoggerFactory.getLogger(MenuUtilsTest.class);

    private MenuUtils menu = new MenuUtils();

    String appId = "wxc1f80b55c40593d5";

    @Test
    public void testAdd() throws UnsupportedEncodingException {
//        String url = "https://demos.shangao.tech/api/wx/public/bind";
//        String url = WXApiUrls.getPageBase(appId, rl, "123");
        String menuJson = "{\n" +
            "    \"button\": [{\n" +
            "            \"name\": \"消息\",\n" +
            "            \"sub_button\": [{\n" +
            "                \"type\": \"click\",\n" +
            "                \"name\": \"无数据消息\",\n" +
            "                \"key\": \"msg1\"\n" +
            "            }, {\n" +
            "                \"type\": \"click\",\n" +
            "                \"name\": \"有数据消息\",\n" +
            "                \"key\": \"msg2\"\n" +
            "            }]\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"菜单\",\n" +
            "            \"sub_button\": [{\n" +
            "                \"type\": \"view\",\n" +
            "                \"name\": \"绑定\",\n" +
            "                \"url\": \"https://demos.shangao.tech/api/wx/public/bind\"\n" +
            "            }]\n" +
            "        }\n" +
            "    ]\n" +
            "}";
        Charset charset = Charset.defaultCharset();
        log.debug(charset.toString());
        boolean result = menu.addMenu(new String(menuJson.getBytes(), charset));
        Assert.assertTrue(result);
    }

    @Test
    public void testQuery() {
        String menuJson = menu.getMenu();
        log.debug(menuJson);
    }

    @Test
    public void testDelete() {
        boolean result = menu.deleteMenu();
        Assert.assertTrue(result);
    }
}
