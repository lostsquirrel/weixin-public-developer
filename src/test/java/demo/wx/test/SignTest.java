package demo.wx.test;

import demo.wx.pub.utils.Sign;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class SignTest {

    private static final Logger log = LoggerFactory.getLogger(SignTest.class);

    @Test
    public void testSignMD5() {
        String key = "192006250b4c09247ec02edce69f6a2d";
        Map<String, String> params = new HashMap<>();
        params.put("appid", "wxd930ea5d5a258f4f");
        params.put("mch_id", "10000100");
        params.put("device_info", "1000");
        params.put("body", "test");
        params.put("nonce_str", "ibuaiVcKdpRxkhJA");
        String s = Sign.signByMD5(key, params);
        log.debug(s);
        Assert.assertEquals("9A0A8659F005D6984697E2CA0A9CF3B7", s);
    }
}
