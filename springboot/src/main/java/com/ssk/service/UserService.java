package com.ssk.service;


import com.ssk.mapper.UserMapper;
import com.ssk.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public User queryBynamepwd(User user){
        return userMapper.Sel(user);
    }

    public User findUserById(String userid){
        return userMapper.selectByid(userid);
    }
    /*@Transactional
    public void insertUser(User user){
        userMapper.insert(user);
    }*/
}
