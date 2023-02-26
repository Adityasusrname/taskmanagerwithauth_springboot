package com.adityasrivastava.taskmanagerwithauth.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {
    private static String JWT_KEY = "hfrhhuhfu9u88u78djbnncbhu8e";  //Move the key to seperate .properties file(not in Git)

    private Algorithm algorithm = Algorithm.HMAC256(JWT_KEY);

    public String createJWT(Long userId){
       return JWT.create().withSubject(userId.toString()).withIssuedAt(new Date()).sign(algorithm);
    }

    public Long retrieveUserId(String jwtString){
       var decodedJWT =  JWT.decode(jwtString);
       var userId = Long.valueOf(decodedJWT.getSubject());
       return userId;
    }

}
