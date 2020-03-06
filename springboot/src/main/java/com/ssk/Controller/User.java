package com.ssk.Controller;

import com.ssk.Annotation.UserLoginToken;
import com.ssk.service.TokenService;
import com.ssk.service.UserService;
import com.ssk.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class User {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @ResponseBody
    @PostMapping("login")
    public ResultUtil hello(com.ssk.pojo.User user){
        ResultUtil resultUtil = new ResultUtil();
        com.ssk.pojo.User user1 = userService.queryBynamepwd(user);
        if(user1 !=null){
            String token = tokenService.getToken(user1);
            resultUtil.setCode("200");
            resultUtil.setData(user1);
            resultUtil.setToken(token);
        }else{
            resultUtil.setCode("500");
            resultUtil.setMsg("用户名或者密码错误");
        }
        return resultUtil;
    }
    @UserLoginToken
    @GetMapping("/getMessage")
    @ResponseBody
    public String getMessage(){
        return "hi,你好！！";
    }
}
