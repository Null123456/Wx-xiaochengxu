package com.ssk.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.ocr.AipOcr;
import com.ssk.pojo.*;
import com.ssk.utils.HttpUtil;
import com.ssk.utils.HttpUtils;
import com.thoughtworks.xstream.XStream;
import jdk.internal.util.xml.impl.Input;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class WxService {
    private static final String TOKEN ="admin";
    //存储access_token
    private static AccessToken at;
    //百度云文字识别设置APPID/AK/SK
        public static final String APP_ID = "18498888";
        public static final String API_KEY = "nz8iIOdnso9CFhP06WmZhLuc";
        public static final String SECRET_KEY = "VvDiPzrYe8Zulbzllbrmy4yh26iVfXwa";


    /**
     * 验证签名
     * @param timestamp
     * @param nonce
     * @param signature
     * @return
     */
    public static boolean check(String timestamp, String nonce, String signature){
      //  1）将token、timestamp、nonce三个参数进行字典序排序
        String[] strs = new String[]{TOKEN,timestamp,nonce};
        Arrays.sort(strs);
        String str = strs[0]+strs[1]+strs[2];
        String mysig = sha1(str);
       // 2）将三个参数字符串拼接成一个字符串进行sha1加密
        //3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        System.out.println(mysig);
        System.out.println(signature);
        return mysig.equalsIgnoreCase(signature);
    }

    /**
     * 进行sha1加密
     * @param str
     * @return
     */
    private static String sha1(String str) {
        //获取一个加密对象
        try {
            MessageDigest md = MessageDigest.getInstance("sha1");
            //加密
            byte[] digest = md.digest(str.getBytes());
            char[] chars = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
            StringBuilder sb = new StringBuilder();
            //处理加密结果
            for(byte b:digest){
                sb.append(chars[(b>>4)&15]);
                sb.append(chars[b&15]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析xml数据包
     * @param is
     * @return
     */
    public static Map<String,String> parseRequest(InputStream is){
        Map<String,String> map = new HashMap<String,String>();
        //1.创建Reader对象
        SAXReader reader = new SAXReader();
        //2.加载xml
        try {
            Document document = reader.read(is);
            //3.获取根节点
            Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.elements();
            for(Element e: elements){
                map.put(e.getName(),e.getStringValue());
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 用户处理所有的事件和消息的回复
     * @param requestMap
     * @return
     */
    public static String getResponse(Map<String, String> requestMap) {
        BaseMessage msg = null;
        String msgType = requestMap.get("MsgType");
        switch (msgType){
            case "text":
                msg = dealTextMessage(requestMap);
                break;
            case "image":
                msg = dealImage(requestMap);
                break;
            case "voice":
                break;
            case "video":
                break;
            case "shortvideo":
                break;
            case "location":
                break;
            case "link":
                break;
            case "event":
                msg = dealEvent(requestMap);
                break;
            default:
                break;
        }
        //把消息对象处理为xml数据包
        if(msg!=null){
            return beanToXml(msg);
        }
        return  null;

    }

    /**
     * 进行图片识别
     * @param requestMap
     * @return
     */
    private static BaseMessage dealImage(Map<String, String> requestMap) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 调用接口
        String path = requestMap.get("PicUrl");
        org.json.JSONObject res = client.basicGeneralUrl(path, options);
        System.out.println(res.toString(2));
        //转为jsonObject
        JSONObject jsonObject = JSONObject.parseObject(res.toString());
        JSONArray words_result = jsonObject.getJSONArray("words_result");
        Iterator<Object> iterator = words_result.iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()){
            JSONObject next = (JSONObject)iterator.next();
            sb.append(next.get("words"));
        }
        return new TextMessage(requestMap,sb.toString());
    }

    private static BaseMessage dealEvent(Map<String, String> requestMap) {
        String event = requestMap.get("Event");
        switch (event){
            case "CLICK":
                return dealClick(requestMap);
            case "VIEW":
                return dealView(requestMap);
            default:
                break;
        }
        return null;
    }

    /**
     * 处理View类型的按钮的菜单
     * @param requestMap
     * @return
     */
    private static BaseMessage dealView(Map<String, String> requestMap) {

        return null;
    }

    /**
     * 处理click菜单
     * @param requestMap
     * @return
     */
    private static BaseMessage dealClick(Map<String, String> requestMap) {
        String key = requestMap.get("EventKey");
        switch (key){
            //点击一级菜单
            case "1":
                //处理点击了第一个一级菜单
                return new TextMessage(requestMap,"支持功能：天气、翻译、藏头诗、笑话、歌词、计算、域名信息/备案/收录查询、IP查询、手机号码归属、人工智能聊天");
            //点击一级菜单
            case "32":
                //处理点击了第三个一级菜单的第二个子菜单
                Image image = new Image();
                image.setMediaId("9IOS0a6JCMxb2XiV-7wP2DuxcYU9YAaPzjARuIEASmg");
                return new ImageMessage(requestMap,image);
            default:
                break;
        }
        return null;
    }

    /**
     * 对象转xml  Xstream库
     * @param msg
     * @return
     */
    private static String beanToXml(BaseMessage msg) {
        XStream stream = new XStream();
        //设置需要处理XStreamAlias("xml")注释的类
        stream.processAnnotations(TextMessage.class);
        stream.processAnnotations(ImageMessage.class);
        stream.processAnnotations(MusicMessage.class);
        stream.processAnnotations(NewsMessage.class);
        stream.processAnnotations(VideoMessage.class);
        stream.processAnnotations(VoiceMessage.class);

        String xml = stream.toXML(msg);
        System.out.println(xml);
        return xml;
    }

    /**
     * 处理文本消息
     * @param requestMap
     * @return
     */
    private static BaseMessage dealTextMessage(Map<String, String> requestMap) {
        //用户发来的内容
        String content = requestMap.get("Content");
        if("图文".equals(content)){
            List<Articles> articles = new ArrayList<Articles>();
            articles.add(new Articles("小米10 Pro 快速体验：米粉期待已久的「诚意之作」",
                    "小米10 Pro 的正面采用了一块 6.67 英寸的三星 AMOLED 双曲面屏幕，两侧的曲率并不大，从而有效地避免了大曲率所带来的绿边现象。",
                    "http://mmbiz.qpic.cn/mmbiz_jpg/5fI73IeXpjxRh5zy3QKEVH0fUbDuO0PEATQYvfYSnXD0GclhR43YQ6fp36pBg9jkAHMSxvm3g82Xe0u9qiaJTibQ/0",
                    "https://mp.weixin.qq.com/s/wuQocggyN-gKCEeOzEm3iw"));
            NewsMessage nm = new NewsMessage(requestMap,articles);
            return nm;
        }
        if("登录".equals(content)){
            String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx55a6aee375e710f9&redirect_uri=https://e.mousenat.cn/ssk/getuser&response_type=code&scope=snsapi_userinfo#wechat_redirect";
            TextMessage tm = new TextMessage(requestMap,"<a href=\""+url+"\">点击这里登录</a>");
            return tm;
        }

        //调用机器人api方法返回回复聊天的内容
        String resp = chat(content);
        TextMessage tm = new TextMessage(requestMap,resp);
        return tm;
    }

    /***
     * 智能机器人API接口
     * @param content
     * @return
     */
    private static String chat(String content) {
        /*智能机器人API接口说明
        支持功能：天气、翻译、藏头诗、笑话、歌词、计算、域名信息/备案/收录查询、IP查询、手机号码归属、人工智能聊天
        接口地址：http://api.qingyunke.com/api.php?key=free&appid=0&msg=关键词
　　　　　key　固定参数free
　　　　　appid 设置为0，表示智能识别，可忽略此参数
　　　　　msg　关键词，请参考下方参数示例，该参数可智能识别，该值请经过 urlencode 处理后再提交
        返回结果：{"result":0,"content":"内容"}
　　　　　result　状态，0表示正常，其它数字表示错误
　　　　　content　信息内容*/
        Map<String,String> param = new HashMap<String,String>();
        param.put("key","free");
        param.put("appid","0");
        param.put("msg",content);
        String result = HttpUtil.get("http://api.qingyunke.com/api.php", param);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String nr=jsonObject.getString("content");
        return nr;
    }
    /***
     * 获取token
     */
    private static void getToken(){
        Map<String,String> param = new HashMap<>();
        param.put("grant_type","client_credential");
        param.put("appid", "wx55a6aee375e710f9");
        param.put("secret","a05ff471099b4bee64d69ef637c84130");
        String s = HttpUtil.get("https://api.weixin.qq.com/cgi-bin/token", param);
        JSONObject jsonObject = JSONObject.parseObject(s);
        String access_token = jsonObject.getString("access_token");
        String expires_in = jsonObject.getString("expires_in");
        at = new AccessToken(access_token,expires_in);
    }

    /**
     * 向外暴露获取taccess_token
     * @return
     */
    public static String getAccessToken(){
        if(at ==null || at.isExpired()){
            getToken();
        }
        return at.getAccess_token();
    }

    /**
     * 上传临时素材
     * @param path
     * @param type
     * @return
     */
    public static String upload(String path,String type){
        File file = new File(path);
        //地址
        String url = "https://api.weixin.qq.com/cgi-bin/media/upload?type="+type+"&access_token="+WxService.getAccessToken();
        try {
            URL urlObj = new URL(url);
            //强转为安全连接
            HttpsURLConnection connection = (HttpsURLConnection)urlObj.openConnection();
            //设置连接信息
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            //设置请求头信息
            connection.setRequestProperty("Connection","Keep-Alive");
            connection.setRequestProperty("Charset","uft-8");
            //数据的边界
            String boundary="-------"+System.currentTimeMillis();
            connection.setRequestProperty("Content-Type","multipart/form-data;boundary="+boundary);
            //准备数据
            //第一部分：头部信息
            StringBuilder sb = new StringBuilder();
            sb.append("--");
            sb.append(boundary);
            sb.append("\r\n");
            sb.append("Content-Disposition: from-data;name=\"media\";filename=\""+file.getName()+"\"\r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
            //获取输出流
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(sb.toString().getBytes());
            //第二部分：文件内容
            //获取输入流
            InputStream is = new FileInputStream(file);
            byte[] b = new byte[1024];
            int len;
            while ((len=is.read(b))!=-1){
                outputStream.write(b,0,len);
            }
            //第三部分：尾部信息
            String foot = "\r\n--"+boundary+"--\r\n";
            outputStream.write(foot.getBytes());
            outputStream.flush();
            outputStream.close();
            //读取数据
            InputStream inputStream = connection.getInputStream();
            StringBuilder res = new StringBuilder();
            while((len=inputStream.read(b))!=-1){
                res.append(new String(b,0,len));
            }
            return res.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 上传永久素材
     * @param path
     * @param type
     * @return
     */
    public static String uploadlong(String path,String type){
        File file = new File(path);
        //地址https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE
        String url = "https://api.weixin.qq.com/cgi-bin/material/add_material?type="+type+"&access_token="+WxService.getAccessToken();
        try {
            URL urlObj = new URL(url);
            //强转为安全连接
            HttpsURLConnection connection = (HttpsURLConnection)urlObj.openConnection();
            //设置连接信息
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            //设置请求头信息
            connection.setRequestProperty("Connection","Keep-Alive");
            connection.setRequestProperty("Charset","uft-8");
            //数据的边界
            String boundary="-------"+System.currentTimeMillis();
            connection.setRequestProperty("Content-Type","multipart/form-data;boundary="+boundary);
            //准备数据
            //第一部分：头部信息
            StringBuilder sb = new StringBuilder();
            sb.append("--");
            sb.append(boundary);
            sb.append("\r\n");
            sb.append("Content-Disposition: from-data;name=\"media\";filename=\""+file.getName()+"\"\r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
            //获取输出流
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(sb.toString().getBytes());
            //第二部分：文件内容
            //获取输入流
            InputStream is = new FileInputStream(file);
            byte[] b = new byte[1024];
            int len;
            while ((len=is.read(b))!=-1){
                outputStream.write(b,0,len);
            }
            //第三部分：尾部信息
            String foot = "\r\n--"+boundary+"--\r\n";
            outputStream.write(foot.getBytes());
            outputStream.flush();
            outputStream.close();
            //读取数据
            InputStream inputStream = connection.getInputStream();
            StringBuilder res = new StringBuilder();
            while((len=inputStream.read(b))!=-1){
                res.append(new String(b,0,len));
            }
            return res.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 获取带参数二维码的ticket
     * @return
     */
    public static String getQrcodeTicket(){
       String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+WxService.getAccessToken();
       //生成临时字符二维码
       String data ="{\"expire_seconds\": 2592000, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"123\"}}}";
        String result = HttpUtils.post(url, data);
        System.out.println(result);
        JSONObject jsonObject = JSONObject.parseObject(result.toString());
        String ticket = jsonObject.getString("ticket");
        return ticket;
    }

    /**
     * 获取用户基本信息
     * @param openid
     * @return
     */
    public static String getUserInfo(String openid){
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        url = url.replace("ACCESS_TOKEN",getAccessToken()).replace("OPENID",openid);
        String s = HttpUtils.get(url);
        return s;
    }

}
