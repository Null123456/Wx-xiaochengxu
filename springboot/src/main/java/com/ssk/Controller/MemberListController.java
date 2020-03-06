package com.ssk.Controller;

import com.ssk.Annotation.UserLoginToken;
import com.ssk.pojo.MemberList;
import com.ssk.service.MemberListService;
import com.ssk.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberListController {
    @Autowired
    MemberListService memberListService;


    @UserLoginToken
    @RequestMapping(value = "selectMemberByid")
    @ResponseBody
    public ResultUtil selectMemberByid(String memberId){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode("200");
        MemberList memberList = memberListService.selectMemberByid(memberId);
        System.out.println(memberList);
        resultUtil.setData(memberList);
        return resultUtil;

    }
}
