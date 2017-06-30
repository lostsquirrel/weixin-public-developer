package demo.wx.pub.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TemplateUtils {

    public String getIndustry() {
        return HttpUtils.getAsString(WXApiUrls.getQueryIndustryUrl());
    }

    public String setIndustry(String primary, String secondary) {
        Map<String, String> param = new HashMap<>();
        param.put("industry_id1", primary);
        param.put("industry_id2", secondary);
        return HttpUtils.postJsonAsString(WXApiUrls.getSetIndustryUrl(), JSON.toJSONString(param));
    }

    public String getTemplateList() {
        return HttpUtils.getAsString(WXApiUrls.getTemplateListUrl());
    }

    public String sendTemplateMessage(String templateId, String toUser, String url, Object data) {
        JSONObject params = new JSONObject();
        params.put("touser", toUser);
        params.put("template_id", templateId);
        params.put("url", url);
        params.put("data", data);
        return HttpUtils.postJsonAsString(WXApiUrls.getTemplateSendUrl(), JSON.toJSONString(params));
    }
}
