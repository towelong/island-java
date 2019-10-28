package com.example.demo.exception;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.demo.utils.RequestHelper;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author WeLong
 * @create 2019/10/23 10:08
 */

@ControllerAdvice
@ResponseBody
public class GlobalException {
    /**
     * 捕获系统异常
     * @param e
     * @return map
     */
    @ExceptionHandler(value = Exception.class)
    public Map getSysError(Exception e) {
        Map<String,Object> map = new HashMap<>();
        map.put("code", -1);
        map.put("msg", e.getMessage());
        map.put("request",RequestHelper.getRequestUrl());
        return map;
    }

    /**
     * 捕获自定义异常
     * @param ex
     * @return map
     */
    @ExceptionHandler(value = HttpException.class)
    public Map errorHandler(HttpException ex) {
        Map<String,Object> map = new HashMap<>();
        map.put("code", ex.getCode());
        map.put("msg", ex.getMsg());
        map.put("request",ex.getRequest());
        return map;
    }

    /**
     * 捕获不合法参数异常
     * @param e
     * @return map
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Map getValidError(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        String msg = result.getFieldError().getDefaultMessage();
        Map<String,Object> map = new HashMap<>();
        map.put("code", -2);
        map.put("msg", msg);
        map.put("request",RequestHelper.getRequestUrl());
        return map;
    }

    /**
     * 请求体为空异常
     * @param e
     * @return map
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public Map getReadError(HttpMessageNotReadableException e) {
        String msg = "请求体不可为空";
        Map<String,Object> map = new HashMap<>();
        map.put("code", -3);
        map.put("msg", msg);
        map.put("request",RequestHelper.getRequestUrl());
        return map;
    }
        //MissingServletRequestParameterException
        @ExceptionHandler(value = MissingServletRequestParameterException.class)
        public Map getReadError(MissingServletRequestParameterException e) {
        String msg = "参数"+e.getParameterName()+"缺失";
        Map<String,Object> map = new HashMap<>();
        map.put("code", -4);
        map.put("msg", msg);
        map.put("request",RequestHelper.getRequestUrl());
        return map;
    }


    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public Map getReadError(MethodArgumentTypeMismatchException e) {
        String msg = e.getValue()+"类型错误";
        Map<String,Object> map = new HashMap<>();
        map.put("code", -5);
        map.put("msg", msg);
        map.put("request",RequestHelper.getRequestUrl());
        return map;
    }
    // ConstraintViolationException 获取Path参数异常
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Map getReadError(ConstraintViolationException e) {
        String msg = "参数异常";
        for(ConstraintViolation<?> s:e.getConstraintViolations()){
            msg = s.getMessage();
        }
        Map<String,Object> map = new HashMap<>();
        map.put("code", -6);
        map.put("msg", msg);
        map.put("request",RequestHelper.getRequestUrl());
        return map;
    }

    /**
     * token过期异常
     * @param e
     * @return map
     */
    @ExceptionHandler(value = TokenExpiredException.class)
    public Map getReadError(TokenExpiredException e) {
        String msg = "token已过期";
        Map<String,Object> map = new HashMap<>();
        map.put("code", -7);
        map.put("msg", msg);
        map.put("request",RequestHelper.getRequestUrl());
        return map;
}
}
