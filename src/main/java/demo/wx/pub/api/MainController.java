package demo.wx.pub.api;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.qq.weixin.mp.aes.XMLParse;
import demo.wx.pub.domain.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by lisong on 2017/4/7.
 */
@Controller
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    private final WXBizMsgCrypt wxBizMsgCrypt;

    @Value("${weixin.developer.token}")
    private String token;

    @Autowired
    public MainController(WXBizMsgCrypt wxBizMsgCrypt) {
        this.wxBizMsgCrypt = wxBizMsgCrypt;
    }

    @GetMapping("/developer")
    @ResponseBody
    public String verify(
            @RequestParam(value = "signature", required = false) String signature,
            @RequestParam(value = "timestamp", required = false) String timestamp,
            @RequestParam(value = "nonce", required = false) String nonce,
            @RequestParam(value = "echostr", required = false) String echostr
                          ) {

        try {
            log.debug("{}-{}-{}-{}",signature, timestamp, nonce, echostr);
            return wxBizMsgCrypt.verifyUrlWithoutDecrypt(signature, timestamp, nonce, echostr);
        } catch (AesException e) {
            e.printStackTrace();
            return  e.getMessage();
        }
    }

    @PostMapping("/developer")
    @ResponseBody  public String business(HttpServletRequest req) throws AesException {
        String data = req.getParameter("data");
        log.debug("{}", data);
        try {
            Msg msg = new Msg(data);
            return msg.toString();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return null;
    }
}
