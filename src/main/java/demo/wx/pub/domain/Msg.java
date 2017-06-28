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

/**
 * 微信消息对象
 */
public class Msg {

    private static final Logger log = LoggerFactory.getLogger(Msg.class);
    
    private String toUserName;

    private String fromUserName;

    private String createTime;

    private String type;

    private String id;

    private String content;

    public Msg(String xmldata) throws SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
            StringReader sr = new StringReader(xmldata);
            InputSource is = new InputSource(sr);
            Document document = db.parse(is);
            Element root = document.getDocumentElement();
            this.toUserName = getField(root, "ToUserName");
            this.fromUserName = getField(root, "FromUserName");
            this.createTime = getField(root, "CreateTime");
            this.content = getField(root, "Content");
            this.type = getField(root, "MsgType");
            this.id = getField(root, "MsgId");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getField(Element root, String fieldName) {
        NodeList nodeList = root.getElementsByTagName(fieldName);
        return nodeList.item(0).getTextContent();
    }

    @Override
    public String toString() {
        String fmt = "<xml>" +
            "   <ToUserName><![CDATA[{%s}]]></ToUserName>" +
            "   <FromUserName><![CDATA[{%s}]]></FromUserName>" +
            "   <CreateTime>{%s}</CreateTime>" +
            "   <MsgType><![CDATA[%s]]></MsgType>" +
            "   <Content><![CDATA[{%s}]]></Content>" +
            "</xml>";

        return String.format(fmt, toUserName, fromUserName, createTime, type, new String(content.getBytes(), Charset.forName("UTF-8")));
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
