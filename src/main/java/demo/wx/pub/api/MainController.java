package demo.wx.pub.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import demo.wx.pub.conf.WXProp;
import demo.wx.pub.domain.Msg;
import demo.wx.pub.utils.HttpUtils;
import demo.wx.pub.utils.TemplateUtils;
import demo.wx.pub.utils.WXApiUrls;
import demo.wx.pub.utils.WXNoneCryptUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by lisong on 2017/4/7.
 */
@Controller
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    private final WXNoneCryptUtils wxNoneCryptUtil;

    @Value("${weixin.developer.token}")
    private String token;

    @Value("${weixin.bind.url}")
    private String bindUrl;

    @Value("${weixin.bind.real}")
    private String realBindUrl;

    private final WXProp wxProp;

    @Autowired
    private TemplateUtils templateUtils;

    @Autowired
    public MainController(WXNoneCryptUtils wxNoneCryptUtil, WXProp wxProp) {
        this.wxNoneCryptUtil = wxNoneCryptUtil;
        this.wxProp = wxProp;
    }

    @GetMapping("/developer")
    @ResponseBody
    public String verify(
        @RequestParam(value = "signature", required = false) String signature,
        @RequestParam(value = "timestamp", required = false) String timestamp,
        @RequestParam(value = "nonce", required = false) String nonce,
        @RequestParam(value = "echostr", required = false) String echostr
    ) {

        log.debug("{}-{}-{}-{}", signature, timestamp, nonce, echostr);
        String msg;
        if (StringUtils.hasLength(signature) && wxNoneCryptUtil.verify(signature, timestamp, nonce)) {
            msg = echostr;
        } else {
            msg = "验签失败";
        }
        return msg;

    }

    @PostMapping("/developer")
    @ResponseBody
    public String business(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = req.getReader();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String data = sb.toString();
        log.debug("{}", data);
        try {
            Msg msg = new Msg(data);
            msg.setBindUrl(bindUrl);
            msg.setTemplateUtils(templateUtils);
            String fu = msg.getFromUserName();
            String tu = msg.getToUserName();
//            交换收发人
            msg.setFromUserName(tu);
            msg.setToUserName(fu);
            String s = msg.toString();
            log.debug("returned msg: {}", s);
            return s;
        } catch (SAXException e) {
            e.printStackTrace();
        }
        log.info("failed to hand msg");
        return "success";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(HttpServletRequest req) {
        return JSON.toJSONString(req.getParameterMap());
    }

    @RequestMapping("/bind")
    public void bind(HttpServletRequest req,
                     HttpServletResponse resp) throws IOException {
        log.debug(JSON.toJSONString(req.getParameterMap()));
//        第一次没有参数，调用接口获取code
        String url = WXApiUrls.getBaseAuth(wxProp.getAppId(), bindUrl, "");
        resp.sendRedirect(url);
    }

    @RequestMapping("/auth")
    public void auth(@RequestParam(value = "code") String code, HttpServletRequest req,
                     HttpServletResponse resp) {
        log.debug(JSON.toJSONString(req.getParameterMap()));
        //         第二次以code为参数 请求 web access_token 和 openId,并重写向到 带openId参数绑定页面
        String res = HttpUtils.getAsString(WXApiUrls.getWebAccessTokenUrl(wxProp.getAppId(), wxProp.getSecret(), code));

        if (res.contains("errcode")) {
            log.error(res);
        } else {
            JSONObject obj = JSON.parseObject(res);
            String openId = obj.getString("openid");
            try {
                String realBindUrlx = String.format("%s?openid=%s", realBindUrl, openId);
                log.debug("redirect to {}", realBindUrlx);
                resp.sendRedirect(realBindUrlx);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
