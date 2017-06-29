package demo.wx.test;

import demo.wx.pub.utils.TemplateUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TemplateUtilsTest {

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
}
