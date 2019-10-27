package com.example.demo.utils;

import lombok.Data;

/**
 * @Author WeLong
 * @create 2019/10/23 9:48
 */
@Data
public class Result<T> {
    private int code = 0;
    private String url = RequestHelper.getRequestUrl();
    private T msg;

    public Result() {

    }
    public Result( T msg) {
        this.msg = msg;
    }

}
