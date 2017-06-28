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

    @Test
    public void testCSApi() {
        System.out.println(api);
        System.out.println(api.getKey());
        System.out.println(api.getAppId());
    }
}
