package com.xiao.http;

import com.xiao.design.Singleton;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import sun.misc.IOUtils;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *  https://blog.csdn.net/u011568320/article/details/75097135(链接池的使用）
 */
public class HttpClientPool {
    PoolingHttpClientConnectionManager cm = null;

    private HttpClientPool(){
        LayeredConnectionSocketFactory sslsf = null;
        try {
            sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("https", sslsf)
                .register("http", new PlainConnectionSocketFactory())
                .build();
        cm =new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        cm.setMaxTotal(200);   // 整个链接池的大小（不同域名请求使用同一cm才会有用）
        cm.setDefaultMaxPerRoute(100);  //每个host的连接最大连接数
    }
    private static class Singleton{
        private static final HttpClientPool singleton= new HttpClientPool();
    }
    public static HttpClientPool getInstance(){
        return  Singleton.singleton;
    }

    public CloseableHttpClient getHttpClient() {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();

        //CloseableHttpClient httpClient = HttpClients.createDefault();//如果不采用连接池就是这种方式获取连接*/
        return httpClient;
    }

    /**
     * httpclient 连接池发送get请求
     * @param reqUrl 请求url
     * @param data  请求参数(可以拼接在url上 或通过data）
     * @return
     */
    public String sendGet(String reqUrl, Map<String,String> data){
        CloseableHttpClient httpClient=this.getHttpClient();
        CloseableHttpResponse response=null;
        try {

            URIBuilder uriBuilder = new URIBuilder(reqUrl);
            //添加参数
            if(data != null && !data.isEmpty()) {
                List<NameValuePair> list = new ArrayList<>();
                data.forEach((k, v) -> {
                    BasicNameValuePair param = new BasicNameValuePair(k, v);
                    list.add(param);
                });
                uriBuilder.setParameters(list) ;
            }
            HttpGet httpget = new HttpGet(uriBuilder.build());
            //添加头
            // 浏览器表示
            httpget.addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)");
            // 传输的类型
            httpget.addHeader("Content-Type", "application/x-www-form-urlencoded");

            response = httpClient.execute(httpget);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            if(responseEntity != null) {
                //System.out.println(EntityUtils.toString(responseEntity,"UTF-8"));
                return EntityUtils.toString(responseEntity,"UTF-8") ;
            }
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (URISyntaxException e) {
            e.printStackTrace();
        }finally {
            if(response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  null;
    }

    /**
     * httpclient 链接池发送post请求
     * @param reqUrl  请求url
     * @param data   请求参数，可以为null
     * @return
     */
    public String sendPost(String reqUrl, Map<String,String> data){
        CloseableHttpClient httpClient=this.getHttpClient();
        HttpPost httpPost = new HttpPost(reqUrl);
        String json=null;
        CloseableHttpResponse response=null;
        try {
            //添加参数
            if(data != null && !data.isEmpty()) {
                List<NameValuePair> list = new ArrayList<>();
                data.forEach((k, v) -> {
                    BasicNameValuePair param = new BasicNameValuePair(k, v);
                    list.add(param);
                });
                // 使用URL实体转换工具
                UrlEncodedFormEntity entityParam = new UrlEncodedFormEntity(list, "UTF-8");
                httpPost.setEntity(entityParam);
            }

            //添加头
            // 浏览器表示
            httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)");
            // 传输的类型
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");

            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            if(responseEntity != null) {
                System.out.println(EntityUtils.toString(responseEntity,"UTF-8"));
            }

        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    /**
     * httpclient 链接池发送post请求
     * @param reqUrl  请求url
     * @param data   请求参数，可以为null
     * @return
     */
    public String sendPost(String reqUrl, String data){
        CloseableHttpClient httpClient=this.getHttpClient();
        HttpPost httpPost = new HttpPost(reqUrl);
        CloseableHttpResponse response=null;
        try {
            //添加参数
            if(data != null && !data.isEmpty()) {
                StringEntity entity = new StringEntity(data, "UTF-8");
                httpPost.setEntity(entity);
            }

            //添加头
            // 浏览器表示
            httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)");
            // 传输的类型
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");

            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            if(responseEntity != null) {
                //System.out.println(EntityUtils.toString(responseEntity,"UTF-8"));
                String tm = EntityUtils.toString(responseEntity,"UTF-8");
                if(tm.indexOf("CDATA[SUCCESS]") != -1 && tm.indexOf("CDATA[wx") != -1) {
                    return "SUCCESS" ;
                }
            }

        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
