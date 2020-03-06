package com.ssk.mapper;

import com.ssk.pojo.MemberList;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberListMapper {
    MemberList selectMemberByid(String memberId);
}
