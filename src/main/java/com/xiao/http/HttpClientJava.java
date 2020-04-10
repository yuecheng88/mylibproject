package com.xiao.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * http请求，使用Java 自带的包
 * https://www.cnblogs.com/zhi-leaf/p/8508071.html(httpclient 与Java自带post请求）
 *
 * https://blog.csdn.net/u011568320/article/details/75097135(链接池的使用）
 */
public class HttpClientJava {


    public static String sendGet(String url)  {
        try {
            URL urlO = new URL(url) ;
            HttpURLConnection conn = (HttpURLConnection)urlO.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8")) ;
            String line = null;
            StringBuilder result = new StringBuilder() ;
            while((line = br.readLine()) != null) {
                result.append(line) ;
            }
            conn.disconnect();
            return result.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendPost(String url)  {
        try {
            URL urlO = new URL("") ;
            HttpURLConnection conn = (HttpURLConnection)urlO.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
            //conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            //写
            PrintWriter pw = new PrintWriter(new BufferedOutputStream(conn.getOutputStream()));
            pw.write("JSON String");
            pw.flush();
            pw.close();
            //读
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8")) ;
            String line = null;
            StringBuilder result = new StringBuilder() ;
            while((line = br.readLine()) != null) {
                result.append(line) ;
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
