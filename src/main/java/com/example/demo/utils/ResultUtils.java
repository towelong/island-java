package com.example.demo.utils;

/**
 * @Author WeLong
 * @create 2019/10/23 11:38
 */
public class ResultUtils {

    public static<T> Result<T> success(T msg) {
        Result result = new Result();
        result.setMsg(msg);
        result.setCode(0);
        return result;
    }
}
