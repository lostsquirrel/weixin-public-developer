package demo.wx.pub.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class HttpUtils {

    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);
    private static final CloseableHttpClient httpclient = HttpClients.createDefault();

    public static String getAsString(String url) {
        HttpGet httpGet = new HttpGet(url);

        String content = null;
        try (CloseableHttpResponse response1 = httpclient.execute(httpGet)) {
            log.debug("{}\n{}", url, response1.getStatusLine());
            HttpEntity entity1 = response1.getEntity();
            InputStream res = entity1.getContent();
            content = readStreamAsString(res);
            EntityUtils.consume(entity1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    private static String readStreamAsString(InputStream res) {
        StringBuilder sb = new StringBuilder();
        if (res != null){
            try (
                BufferedReader br = new BufferedReader(new InputStreamReader(res))
            ) {
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(new String(line.getBytes(), Charset.defaultCharset()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static String postJsonAsString(String url, String JsonParams) {
        HttpPost httpPost = new HttpPost(url);
//        httpPost.
        log.debug("{}", url);
        log.debug("{}", JsonParams);
        String content = null;
        if (StringUtils.hasLength(JsonParams)){
            httpPost.setEntity(new StringEntity(JsonParams, ContentType.APPLICATION_JSON));
        }

        try (
            CloseableHttpResponse response2 = httpclient.execute(httpPost)
            ){
            HttpEntity entity2 = response2.getEntity();
            content = readStreamAsString(entity2.getContent());
            EntityUtils.consume(entity2);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        log.debug(content);
        return content;
    }



}
