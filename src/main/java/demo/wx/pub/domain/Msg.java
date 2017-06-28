package demo.wx.pub.domain;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.XMLParse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;

/**
 * 微信消息对象
 */
public class Msg {

    public static final String MSG_TYPE_TEXT = "text";

    public static final String MSG_TYPE_IMAGE = "image";

    private static final Logger log = LoggerFactory.getLogger(Msg.class);
    
    private String toUserName;

    private String fromUserName;

    private String createTime;

    private String type;

    private String id;

    private String content;

    private String picUrl;

    private String mediaId;

    public Msg(String xmldata) throws SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
            StringReader sr = new StringReader(xmldata);
            InputSource is = new InputSource(sr);
            Document document = db.parse(is);
            Element root = document.getDocumentElement();
            this.id = getField(root, "MsgId");
            this.toUserName = getField(root, "ToUserName");
            this.fromUserName = getField(root, "FromUserName");
            this.createTime = getField(root, "CreateTime");

            this.type = getField(root, "MsgType");
            switch (type) {
                case MSG_TYPE_TEXT:
                    this.content = getField(root, "Content");
                    break;
                case MSG_TYPE_IMAGE:
                    this.picUrl = getField(root, "PicUrl");
                    this.mediaId = getField(root, "MediaId");
                    break;
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getField(Element root, String fieldName) {
        NodeList nodeList = root.getElementsByTagName(fieldName);
        String f = "";
        if (nodeList != null && nodeList.getLength() > 0){
            f = nodeList.item(0).getTextContent();
        }
        return f;
    }

    @Override
    public String toString() {
        String msg = "success";
        long creatTime = new Date().getTime() / 1000;
        switch (type) {
            case MSG_TYPE_TEXT:
                String fmt = "<xml>" +
                    "<ToUserName><![CDATA[%s]]></ToUserName>" +
                    "<FromUserName><![CDATA[%s]]></FromUserName>" +
                    "<CreateTime>%s</CreateTime>" +
                    "<MsgType><![CDATA[%s]]></MsgType>" +
                    "<Content><![CDATA[%s]]></Content>" +
                    "</xml>\n";
                String content = new String(this.content.getBytes(), Charset.forName("UTF-8"));
                msg = String.format(fmt, toUserName, fromUserName, creatTime, type, content);
                break;
            case MSG_TYPE_IMAGE:
                fmt = "<xml>" +
                    "<ToUserName><![CDATA[%s]]></ToUserName>" +
                    "<FromUserName><![CDATA[%s]]></FromUserName>" +
                    "<CreateTime>%s</CreateTime>" +
                    "<MsgType><![CDATA[image]]></MsgType>" +
                    "<Image>" +
                    "<MediaId><![CDATA[%s]]></MediaId>" +
                    "</Image>" +
                    "</xml>\n";
                msg = String.format(fmt, toUserName, fromUserName, creatTime, mediaId);
                break;
        }




        return msg;
    }

    public static Logger getLog() {
        return log;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
