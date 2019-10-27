package com.example.demo.AOP;

import com.example.demo.annotation.AuthToken;
import com.example.demo.exception.AuthFailed;
import com.example.demo.exception.NotFound;
import com.example.demo.utils.ResultUtils;
import com.example.demo.utils.TokenUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author WeLong
 * @create 2019/10/21 16:44
 */
@Aspect
@Component
public class aop {

    @Pointcut("@annotation(authToken)")
    public void doToken(AuthToken authToken) {

    }

    @Around("doToken(authToken)")
    public Object deBefore(ProceedingJoinPoint pjp, AuthToken authToken) throws Throwable {
        // 获取访问该方法所需的role_name信息
        String[] role_name = authToken.role_name();
        for(int i=0;i<role_name.length;i++){
            switch (role_name[i]){
                case "user":
                    if(this.PassAuth()){
                        return pjp.proceed();
                    }
                    break;
                case "admin":
                    if(this.PassAuth()){
                        return pjp.proceed();
                    }
                    break;
            }
        }
        throw new AuthFailed();
    }
    public boolean PassAuth() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        TokenUtils tokenUtils = new TokenUtils();
            String header = request.getHeader("Authorization");
        if(!(header != null && header.length()!=0)){
            return false;
        }
        else {
            // 去除请求头的Bearer 取出token
            String token = header.substring(7);
            if (header.startsWith("Bearer")){
                boolean isVerify = tokenUtils.VerifyToken(token);
                if(isVerify){
                    return true;
                }
                return false;
            }
            return false;
        }

    }

}
