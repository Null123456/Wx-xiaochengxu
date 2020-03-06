package com.ssk.mapper;


import com.ssk.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper  {
    User Sel(@Param(value="user")User user);
    User selectByid(String userid);
}
