package demo.wx.pub.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 公众号菜单管理
 * 1、自定义菜单最多包括3个一级菜单，每个一级菜单最多包含5个二级菜单。
 2、一级菜单最多4个汉字，二级菜单最多7个汉字，多出来的部分将会以“...”代替。
 3、创建自定义菜单后，菜单的刷新策略是，在用户进入公众号会话页或公众号profile页时，如果发现上一次拉取菜单的请求在5分钟以前，就会拉取一下菜单，如果菜单有更新，就会刷新客户端的菜单。测试时可以尝试取消关注公众账号后再次关注，则可以看到创建后的效果。
 */
public class MenuUtils {

    private static final Logger log = LoggerFactory.getLogger(MenuUtils.class);

    public boolean addMenu(String menuJson) {
        String res = HttpUtils.postJsonAsString(WXApiUrls.getMenuSaveUrl(), menuJson);
        JSONObject obj = JSON.parseObject(res);

        return obj.getInteger("errcode") == 0;
    }

    public boolean deleteMenu() {
        String res = HttpUtils.getAsString(WXApiUrls.getMenuDeleteUrl());
        JSONObject obj = JSON.parseObject(res);
        return obj.getInteger("errcode") == 0;
    }

    public String getMenu() {
        String res = HttpUtils.getAsString(WXApiUrls.getMenuQueryUrl());
        return res;
    }


}
