package com.project.common.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil
{
    /**
     * 发送http get请求
     * @param url
     * @param params
     * @return 
     * @see 
     */
    public static String get(String url, Map<String, String> params)
    {
		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(6000)
				.setConnectionRequestTimeout(2000).build();
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
        String body = null;
        String apiUrl = getAPIUrl(url, params);
        HttpGet get = new HttpGet(apiUrl);
        CloseableHttpResponse response = null;
        try
        {
            response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK)
            {
                if (entity != null)
                {
                    body = EntityUtils.toString(entity, "UTF-8");
                }
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                // 关闭连接,释放资源  
                if (response != null)
                {
                    response.close();
                }
                if (httpClient != null)
                {
                    httpClient.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return body;
    }
    
    /**
     * 拼接请求url
     * @param url
     * @param params
     * @return 
     * @see 
     */
    private static String getAPIUrl(String url, Map<String, String> params)
    {
        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet())
        {
            if (i == 0)
                param.append("?");
            else
                param.append("&");
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl += param;
        return apiUrl;
    }
    
    /**
     * post方法
     * @param url
     * @param params
     * @return 
     * @see 
     */
    public static String post(String url, Map<String, String> params)
    {
    	RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(6000)
				.setConnectionRequestTimeout(2000).build();
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
        String body = null;
        HttpPost post = postForm(url, params);
        
        post.setHeader("User-Agent",
                "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2)");
        // 用逗号分隔显示可以同时接受多种编码
        post.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
        post.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
        CloseableHttpResponse response = null;
        try
        {
            response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK)
            {
                if (entity != null)
                {
                    body = EntityUtils.toString(entity, "UTF-8");
                }
            }
        }
        catch (ClientProtocolException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                // 关闭连接,释放资源  
                if (response != null)
                {
                    response.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return body;
    }
    
    /**
     * 组装发送参数
     * @param url
     * @param params
     * @return 
     * @see 
     */
    private static HttpPost postForm(String url, Map<String, String> params)
    {
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> pairList = new ArrayList<>(params.size());
        for (Map.Entry<String, String> entry : params.entrySet())
        {
            NameValuePair pair = new BasicNameValuePair(entry.getKey(),
                    entry.getValue().toString());
            pairList.add(pair);
        }
        httpPost.setEntity(new UrlEncodedFormEntity(pairList,
                Charset.forName("UTF-8")));
        return httpPost;
    }
    
    public static byte[] synPostByteStream(String url, byte[] data, RequestConfig requestConfig) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try {
                ByteArrayEntity entity = new ByteArrayEntity(data, ContentType.APPLICATION_OCTET_STREAM);
                RequestBuilder rb = RequestBuilder.post()
                        .setHeader("Content-Type", "application/octet-stream")
                        .setUri(new URI(url))
                        .setEntity(entity);
                if (requestConfig != null) {
                    rb.setConfig(requestConfig);
                }
                HttpUriRequest httpRequest = rb.build();
                try (CloseableHttpResponse response = httpclient.execute(httpRequest)) {
                    int statusCode = response.getStatusLine().getStatusCode();
                    if (statusCode == 200) {
                        HttpEntity resEntity = response.getEntity();
                        if (resEntity != null) {
                            byte[] bytes = EntityUtils.toByteArray(resEntity);
                            EntityUtils.consume(resEntity);
                            return bytes;
                        }
                    } else {
                    }
                }
            } catch (URISyntaxException ex) {
            }
        } catch (NoHttpResponseException ex) {
        } catch (IOException ex) {
        }
        return null;
    }
}
