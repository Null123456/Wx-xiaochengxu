package com.ssk.Controller;

import com.alibaba.fastjson.JSONObject;
import com.ssk.service.WxService;
import com.ssk.utils.HttpUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Controller
public class WxController {


    //------------*****************微信公众号相关接口**************************************
    /**
     * 接入微信公众平台开发，开发者需要按照如下步骤完成：
     *
     * 1、填写服务器配置
     *
     * 2、get请求验证服务器地址的有效性
     * @param request
     * @return
     */
    @GetMapping(value = "wx")
    @ResponseBody
    public String wx(HttpServletRequest request){
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        //System.out.println("signature:"+signature+"timestamp:"+timestamp+"nonce:"+nonce+"echostr:"+echostr);
        if(WxService.check(timestamp,nonce,signature)){
            System.out.println("接入成功");
            return echostr;
        }else{
            System.out.println("接入失败");
        }
        return null;
    }

    /**
     * post接收消息和事件推送
     * @param request
     */
    @PostMapping(value = "wx")
    @ResponseBody
    public String wxx(HttpServletRequest request) throws IOException {
        /*try {
            ServletInputStream is = request.getInputStream();
            byte[] b = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while((len=is.read(b))!=-1){
                sb.append(new String(b,0,len));
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        Map<String,String> requestMap = WxService.parseRequest(request.getInputStream());
        System.out.println(requestMap);
        //准备回复的数据包
        String respXml = WxService.getResponse(requestMap);
        return respXml;


    }

    /**
     * 通过code换取网页授权access_token
     */
    @RequestMapping(value = "getuser")
    @ResponseBody
    public String GetUser(HttpServletRequest request){
        String code = (String)request.getParameter("code");
        //换取access_token
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        url = url.replace("APPID","wx55a6aee375e710f9").replace("SECRET","a05ff471099b4bee64d69ef637c84130").replace("CODE",code);
        String result = HttpUtils.get(url);
        System.out.println(result);
        String access_token = JSONObject.parseObject(result).getString("access_token");
        //拉取用户的基本信息
        url = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        url = url.replace("ACCESS_TOKEN",access_token).replace("OPENID","oETLow7FdlY-oDdRdXQjKJczznfk");
        String s = HttpUtils.get(url);
        return s;


    }

}
