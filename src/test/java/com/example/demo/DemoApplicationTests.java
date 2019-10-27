package com.example.demo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Dao.UserMapper;
import com.example.demo.model.User;
import com.example.demo.utils.TokenUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.amdelamar.jhash.Hash;
import com.amdelamar.jhash.algorithms.Type;
import com.amdelamar.jhash.exception.InvalidHashException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;


import java.util.Date;


@SpringBootTest
class DemoApplicationTests {


    @Autowired
    UserMapper userMapper;


    @Test
    void contextLoads() {
        RestTemplate restTemplate = new RestTemplate();
//        http://t.yushu.im/v3/book/search?q={s}&count={s}&start={s}&summary={s}
        String url = "http://t.yushu.im/v2/book/id/{s}";
        Object s = restTemplate.getForObject(url,Object.class,1120);
        System.out.println(s);
    }


    @Test
    public void jwt() {
        TokenUtils tokenUtils = new TokenUtils();
        int scope = 16;
        String token = tokenUtils.CreateToken(1L,scope);
        System.out.println(token);

    }
    @Test
    public void jwtVerify() {
        TokenUtils tokenUtils = new TokenUtils();
//        scope为16
        String token1 = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjEsInNjb3BlIjoxNiwiZXhwIjoxNTcxODE3MTU5fQ.qtG1Cz3Kpl7ZbDECJXWPQ0hFd7q3gCBOeGKCVU-S8J4";
        boolean b = tokenUtils.VerifyToken(token1);
        System.out.println(b);

    }
    @Test
    public void j() {
        com.auth0.jwt.algorithms.Algorithm algorithm = com.auth0.jwt.algorithms.Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
                .acceptExpiresAt(4)
                .build(); //Reusable verifier instance
        DecodedJWT jwt = verifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjEsInNjb3BlIjoxNiwiZXhwIjoxNTcxNzI1NTM2fQ.MybYIKlw4_a-OTmklOkpQGI7NuPSf7rGcglMm1PosoY");
        Date expire = jwt.getExpiresAt();
        expire.getTime();
        Claim a = jwt.getClaim("scope");
        a.asInt();
        a.asDate();
    }

    /**
     * 设置密文密码
     *
     * @param password 原始密码
     */
    public void setPasswordEncrypt(String password) {
        char[] chars = password.toCharArray();
        String s= Hash.password(chars).algorithm(Type.BCRYPT).create();
        System.out.println(s);
    }

    /**
     * 验证加密密码
     *
     * @param password 密文密码
     * @return valid
     */
    public boolean verify(String password) {
        char[] chars = password.toCharArray();
        try {
            return Hash.password(chars).verify(password);
        } catch (InvalidHashException e) {
            return false;
        }
    }
}
