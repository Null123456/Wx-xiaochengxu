package com.ssk.Controller;

import com.alibaba.fastjson.JSONObject;
import com.ssk.pojo.Global;
import com.ssk.utils.getOpenIdutil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GetOpenIdController {
    @RequestMapping(value = "GetId")
    @ResponseBody
    public String GetId(String appid,String code,String secret){
        getOpenIdutil getopenid=new getOpenIdutil();
        //调用访问微信服务器工具方法，传入三个参数获取带有openid、session_key的json字符串
        String jsonId=getopenid.getopenid(appid,code,secret);
        JSONObject jsonObject = JSONObject.parseObject(jsonId);
        //从json字符串获取openid和session_key
        String openid=jsonObject.getString("openid");
        String session_key=jsonObject.getString("session_key");
        //保存变量到全局
        Global.setOpenId(openid);
        Global.setSessionKey(session_key);

        System.out.println("openid:"+openid+"......session_key:"+session_key);
        String result = openid+"......"+session_key;
        return result;
    }
}
