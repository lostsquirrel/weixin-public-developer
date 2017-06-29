package demo.wx.test;

import demo.wx.pub.app.Application;
import demo.wx.pub.utils.AccessTokenUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class AccessTokenUtilsTest {

    private static final Logger log = LoggerFactory.getLogger(AccessTokenUtilsTest.class);

    @Autowired
    private AccessTokenUtils accessTokenUtils;

    @Test
    public void testGetToken() {
        String at = accessTokenUtils.getAccessToken();
        log.debug(at);
        Assert.assertNotNull(at);
    }
}
