package com.example.demo.exception;

import lombok.Data;

/**
 * @Author WeLong
 * @create 2019/10/23 10:05
 */

@Data
public class NotFound extends HttpException {

    private String msg = "资源不存在";
    private int code = 999;

    public NotFound(String msg) {
        this.msg = msg;
    }
    public NotFound() {

    }
}
