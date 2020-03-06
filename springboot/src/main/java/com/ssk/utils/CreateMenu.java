package com.ssk.utils;

import com.alibaba.fastjson.JSONObject;
import com.ssk.pojo.*;
import com.ssk.service.WxService;

public class CreateMenu {
    public static void main(String[] args) {
        //菜单对象
        Button btn = new Button();
        //第一个一级菜单
        btn.getButton().add(new ClickButton("智能机器人","1"));
        //第二个一级菜单
        btn.getButton().add(new ViewButton("健康打卡","http://106.12.195.29/yqsb/p/index.html#/stu/login"));
        //第三个一级菜单
        SubButton sb = new SubButton("其他");
        //第三个一级菜单增加子菜单
        sb.getSub_button().add(new PhotoOrAlbumButton("图文识别","31"));
        sb.getSub_button().add(new ViewButton("网易新闻","http://news.163.com"));
        sb.getSub_button().add(new ClickButton("联系我","32"));
        //加入第三个一级菜单
        btn.getButton().add(sb);
        //转为json
        Object o = JSONObject.toJSON(btn);
        System.out.println(o);
        //发送请求
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+WxService.getAccessToken();
        System.out.println(WxService.getAccessToken()+"**************");
        String result = HttpUtils.post(url, o.toString());
        System.out.println(url);
        System.out.println(result);
    }
}
