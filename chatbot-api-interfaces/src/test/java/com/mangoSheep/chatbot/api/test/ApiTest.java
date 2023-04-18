package com.mangoSheep.chatbot.api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;

/**
 * 单元测试
 */
public class ApiTest {

    /**
     * 获取知识星球"等我回答"列表
     * @throws IOException
     */
    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/51112182554214/topics?scope=unanswered_questions&count=20");
        get.addHeader("cookie", "zsxqsessionid=e595a98ba4ea94bb9b0ff4593c238b5e; abtest_env=product; zsxq_access_token=8CBAC055-5C25-0FE5-B828-338F8437C85C_596478B249E4F4FF");
        get.addHeader("Content-Type", "application/json; charset=UTF-8");
        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    /**
     * 回答问题
     */
    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/181448124558122/answer");

        post.addHeader("cookie", "zsxqsessionid=e595a98ba4ea94bb9b0ff4593c238b5e; abtest_env=product; zsxq_access_token=8CBAC055-5C25-0FE5-B828-338F8437C85C_596478B249E4F4FF");
        post.addHeader("Content-Type", "application/json; charset=UTF-8");

        String paramJson = "{\"req_data\":{\"text\":\"我也不会呀啊哈哈！\\n\",\"image_ids\":[],\"silenced\":true}}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));

        post.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }
}
