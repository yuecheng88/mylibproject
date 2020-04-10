package com.xiao.http;

import com.xiao.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import sun.net.www.http.HttpClient;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * *使用Java自带，未使用连接池
 * 500 单线程   调用prepay 96657 ms
 * 500 4线程   调用prepay 25237 ms
 * 500 5线程             19939 ms
 * 500 10线程            10595 ms
 * 500 20线程            5610 ms
 * 500 50线程            3246 ms
 * 1000 100线程          5800 ms
 *
 *
 * 使用httpClient 与连接池 默认20 route
 * 500 单线程   调用prepay 75376 ms
 * 500 5线程             15816 ms
 * 500 10线程            8219 ms
 * 500 20线程            4496 ms
 * 500 50线程            4813 ms
 *
 * 使用httpClient 与连接池 50 route
 * 500 50线程            2602 ms
 *
 * 使用httpClient 与连接池 100 route
 * 1000 100线程            2450 ms
 *
 * netstat -ano|findstar 进程ID
 */
public class TestWe {
    public static void main(String[] args) throws Exception {
//        String url = "http://localhost:8080/Test/test?sleep=50" ;
        Thread.sleep(12000);
        TestRun tr = new TestRun();
        List<Thread> list = new ArrayList<>( );
        for(int i=0;i<100;i++) {
            list.add(new Thread(tr)) ;
        }
        long min = System.currentTimeMillis();
        for(Thread t :list){
            t.start();
        }
        for(Thread t :list){
            t.join();
        }

        System.out.println("总用时=="+ (System.currentTimeMillis() - min));
        Thread.sleep(1200000);

        //HttpClientPool.getInstance().sendGet("http://localhost:8080/Test/test?sleep=200",null) ;
        //System.out.println(prePay(0)) ;

        //System.out.println(prePay(0));
    }
//    params.put("appid", "wx189d8e9d2b86f1bb");
//                params.put("mch_id", "1360696602");
//    wx_paykey = "yunjiQN68w6eHbYsGmaix4r8IivbqtsH";

     static String prePay(int i) throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        long min = System.currentTimeMillis();
        params.put("appid", "wx189d8e9d2b86f1bb");
        params.put("mch_id", "1360696602");
        String nonceStr = new Random().nextInt(30) + "";

        params.put("nonce_str", nonceStr);
        params.put("device_info", "WEB");
        params.put("body", "订单号-" +min);

        params.put( "out_trade_no", "YJ" + min + i);
        params.put("total_fee", "1");
        params.put("spbill_create_ip", "172.0.0.1");
        params.put("notify_url", "http://172.0.0.1:8080/Test/test");


        params.put("trade_type", "APP");
        String sign = MD5Util.md5Sign(params, "yunjiQN68w6eHbYsGmaix4r8IivbqtsH", "UTF8");
        params.put("sign", sign.toUpperCase());
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        params.forEach((k, v) -> {
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k)
                    || "sign".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        });
        sb.append("</xml>");
        Map<String, String> map = HttpClientJava2.wechatPostRequest(sb.toString(), "https://api.mch.weixin.qq.com/pay/unifiedorder");
         return map.get("prepay_id");
//        String res = HttpClientPool.getInstance().sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder",sb.toString()) ;
//        return res;
    }

}

class  TestRun implements  Runnable {
    private AtomicInteger ai = new AtomicInteger() ;
    @Override
    public void run() {
        //System.out.println("***************************************");
        long mia = System.currentTimeMillis();
        String result = null;
        for(int i = 0;i<10;i++) {
            try {
                result = TestWe.prePay(i) ;
            } catch (Exception e) {
                e.printStackTrace();
            }
            //result = HttpClientJava.sendGet("http://localhost:8080/Test/test?sleep=200") ;
            //result = HttpClientPool.getInstance().sendGet("http://localhost:8080/Test/test?sleep=200",null) ;
            if(result != null)
                ai.incrementAndGet() ;
        }
        //System.out.println(HttpClientJava2.a.get());
        System.out.println(Thread.currentThread().getName() +"-ai =" + ai.get() +"  --usetime=" +(System.currentTimeMillis() - mia));
    }
}