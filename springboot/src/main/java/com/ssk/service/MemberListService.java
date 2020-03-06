package com.ssk.service;

import com.ssk.mapper.MemberListMapper;
import com.ssk.pojo.MemberList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberListService {
    @Autowired
    MemberListMapper memberListMapper;
    public MemberList selectMemberByid(String memberId){
        MemberList memberList = memberListMapper.selectMemberByid(memberId);
        return memberList;
    }

}
