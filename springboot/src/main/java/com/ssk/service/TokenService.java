package com.ssk.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ssk.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class TokenService {
    public String getToken(User user){
        String token = "";
        token = JWT.create().withAudience(user.getId()).sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
