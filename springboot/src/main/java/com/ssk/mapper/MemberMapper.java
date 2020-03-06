package com.ssk.mapper;

import com.ssk.pojo.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MemberMapper {
    List<Member> selectMemberAll();
}
