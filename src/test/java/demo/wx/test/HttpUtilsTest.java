package demo.wx.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import demo.wx.pub.utils.HttpUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class HttpUtilsTest {

    private static final Logger log = LoggerFactory.getLogger(HttpUtilsTest.class);

    @Test
    public void testGet() {
        String url = "http://api.avatardata.cn/BeijingTime/LookUp";
        url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxc1f80b55c40593d5&redirect_uri=https%3A%2F%2Fdemos.shangao.tech%2Fapi%2Fwx%2Fpublic%2Fdeveloper&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
        Object res = HttpUtils.getAsString(url);
        log.debug(res.toString());
        log.debug(res.getClass().getName());
//        JSONObject obj = JSON.parseObject(res.toString());
//        obj.get("");
//        log.debug(obj.toString());
//        log.debug(obj.getClass().getName());
    }
}
