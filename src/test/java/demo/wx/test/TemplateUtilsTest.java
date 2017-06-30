package demo.wx.test;

import com.alibaba.fastjson.JSONObject;
import demo.wx.pub.utils.TemplateUtils;
import org.apache.http.client.utils.DateUtils;
import org.assertj.core.util.DateUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Date;
import java.util.Random;

public class TemplateUtilsTest extends WXTestBase {

    private static final Logger log = LoggerFactory.getLogger(TemplateUtilsTest.class);

    private TemplateUtils tpl = new TemplateUtils();

    @Test
    public void testGetIndustry() {
        log.debug("{}", tpl.getIndustry());
    }

    @Test
    public void setIndustry() {
        log.debug("{}", tpl.setIndustry("1", "16"));
    }


    @Test
    public void getTemplateList() {
        log.debug("{}", tpl.getTemplateList());
    }


    @Test
    public void sendTemplate() {
        tpl.sendTemplateMessage("aSGBRB4mNw-B2xnXBBtd-GWqSSvXq4sxg_LWyq9ypmo", "obmQ0wZVRNqX9Sg81-2xSobcOdpw", "https://demos.shangao.tech", new Object());
    }

    @Test
    public void sendTemplateWithData() {
        JSONObject data = new JSONObject();
        Random r = new Random();
        data.put("num1", Collections.singletonMap("value", r.nextInt()));
        data.put("num2", Collections.singletonMap("value", r.nextFloat()));
        data.put("ct", Collections.singletonMap("value", DateUtils.formatDate(new Date())));
        tpl.sendTemplateMessage("-Gl8BXaphBPovHUdz0eNI5uYm5maYlYmWBnGRQhc8so", "obmQ0wZVRNqX9Sg81-2xSobcOdpw", "https://demos.shangao.tech",data);
    }
}
