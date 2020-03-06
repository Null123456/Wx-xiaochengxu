package com.ssk.utils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtils {
    public static String post(String url,String data) {

            try {
            URL weChatUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = weChatUrl.openConnection();
            // 设置通用的请求属性
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setDoOutput(true);
            //读取输出流
            OutputStream os = connection.getOutputStream();
            //写出数据
            os.write(data.getBytes());
            os.close();
            //获取输入流
            InputStream is = connection.getInputStream();
            byte[] b = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len=is.read(b))!=-1){
                sb.append(new String(b,0,len));
            }
            return sb.toString();
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
    }

    public static String get(String url){
        try {
            URL urlObj = new URL(url);
            URLConnection connection = urlObj.openConnection();
            InputStream is = connection.getInputStream();
            byte[] b = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len=is.read(b))!=-1){
                sb.append(new String(b,0,len));
            }
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
