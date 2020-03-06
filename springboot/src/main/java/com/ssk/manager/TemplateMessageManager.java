package com.ssk.manager;

import com.ssk.service.WxService;
import com.ssk.utils.HttpUtils;


public class TemplateMessageManager {

    public static void main(String[] args) {
        //sendTemplateMessage();
//        String re = WxService.upload("C:\\Users\\Administrator\\Desktop\\萌宠平台\\resources\\koon.jpg", "image");
//        System.out.println(re);

//        System.out.println(WxService.getAccessToken()+"********");
//        System.out.println(WxService.getUserInfo("oETLow7FdlY-oDdRdXQjKJczznfk"));
        String image = WxService.uploadlong("C:\\Users\\Administrator\\Desktop\\萌宠平台\\resources\\微信图片_20200216230918.jpg", "image");
        System.out.println(image);
    }
    /**
     * 设置行业
     */
    public static void set(){
        String url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token="+ WxService.getAccessToken();
        String data = "{\n" +
                "    \"industry_id1\":\"1\",\n" +
                "    \"industry_id2\":\"4\"\n" +
                "}";
        String post = HttpUtils.post(url, data);
        System.out.println(post);
    }

    /**
     * 获取行业
     */
    public static void get(){
        String url ="https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token="+WxService.getAccessToken();
        String s = HttpUtils.get(url);
        System.out.println(s);
    }

    /**
     * 发送模板消息
     */
    public static void sendTemplateMessage(){
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+WxService.getAccessToken();
        String data = "{\n" +
                "        \"touser\":\"oETLowx5kkz9YCUT7UQJKHt5kIOM\",\n" +
                "        \"template_id\":\"bbew9DGFPEJ4FQ1nx1M3kbpBwghsESkkJIyxOxS-nUk\",\n" +
                "        \"url\":\"http://weixin.qq.com\",\n" +
                "        \"data\":{\n" +
                "        \"first\": {\n" +
                "        \"value\":\"您好!您投递的简历有新的反馈！\",\n" +
                "        \"color\":\"#173177\"\n" +
                "        },\n" +
                "        \"time\":{\n" +
                "        \"value\":\"2019/2/17 15:09:25\",\n" +
                "        \"color\":\"#173177\"\n" +
                "        },\n" +
                "        \"company\": {\n" +
                "        \"value\":\"阿里巴巴\",\n" +
                "        \"color\":\"#173177\"\n" +
                "        },\n" +
                "        \"result\": {\n" +
                "        \"value\":\"面试通过\",\n" +
                "        \"color\":\"#173177\"\n" +
                "        },\n" +
                "        \"remark\":{\n" +
                "        \"value\":\"请联系本公司HR！\",\n" +
                "        \"color\":\"#173177\"\n" +
                "        }\n" +
                "        }\n" +
                "        }";
        String result = HttpUtils.post(url, data);
        System.out.println(result);

    }
}
