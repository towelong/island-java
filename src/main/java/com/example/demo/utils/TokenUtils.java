package com.example.demo.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.exception.Forbidden;
import com.example.demo.exception.NotFound;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * @Author WeLong
 * @create 2019/10/21 19:54
 */
public class TokenUtils {

    public String CreateToken(Long uid, int scope) {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        Date expireDate = this.getExpireDate(1000 * 60 * 60 * 24);
        String token = JWT.create()
                .withClaim("uid", uid)
                .withClaim("scope", scope)
                .withExpiresAt(expireDate)
                .sign(algorithm);
        return token;
    }

    public boolean VerifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
                .acceptExpiresAt(4)
                .build();
        DecodedJWT jwt = verifier.verify(token);

        Claim a = jwt.getClaim("scope");
        Date d = jwt.getExpiresAt();
        long tokenTime = d.getTime();
        long localTime = new Date().getTime();
        int scope = a.asInt();
        int admin = 8;
        if(tokenTime <= localTime){
            throw new Forbidden("token已过期");
        }
        if (scope > admin) {
            return true;
        } else {
            return false;
        }


    }

    /**
     * 获得过期时间
     *
     * @param expire 过期时间
     * @return Date
     */
    private Date getExpireDate(long expire) {
        long nowTime = new Date().getTime();
        long expireTime = nowTime + expire;
        Date expireDate = new Date(expireTime);
        return expireDate;
    }

    public int getUid() {
        String token;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String header = request.getHeader("Authorization");
        if(!(header != null && header.length()!=0)){
            throw new NotFound("token不合法");
        }
        else {
            // 去除请求头的Bearer 取出token
            token = header.substring(7);
            if (header.startsWith("Bearer")){
                boolean isVerify = this.VerifyToken(token);
                if(isVerify){
                    Algorithm algorithm = Algorithm.HMAC256("secret");
                    JWTVerifier verifier = JWT.require(algorithm)
                            .acceptExpiresAt(4)
                            .build();
                    DecodedJWT jwt = verifier.verify(token);
                    Claim uid = jwt.getClaim("uid");
                    return uid.asInt();
                }
                throw new NotFound("token不合法");
            }
            throw new NotFound("token不合法");
        }
    }
}
