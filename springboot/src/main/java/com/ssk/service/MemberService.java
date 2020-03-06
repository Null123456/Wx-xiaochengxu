package com.ssk.service;

import com.ssk.mapper.MemberMapper;
import com.ssk.pojo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MemberService {
    @Autowired
    MemberMapper memberMapper;
    public  List<Member> selectMemberAll(){
        List<Member> members = memberMapper.selectMemberAll();
        return members;
    }

}
