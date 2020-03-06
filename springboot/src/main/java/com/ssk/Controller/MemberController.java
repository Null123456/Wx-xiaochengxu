package com.ssk.Controller;


import com.ssk.Annotation.UserLoginToken;
import com.ssk.pojo.Member;
import com.ssk.service.MemberService;
import com.ssk.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MemberController {
    @Autowired
    MemberService memberService;


    @UserLoginToken
    @RequestMapping(value = "selectMemberAll")
    @ResponseBody
    public ResultUtil selectMemberAll(){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode("200");
        List<Member> members = memberService.selectMemberAll();
        resultUtil.setData(members);
        return resultUtil;
    }
}
