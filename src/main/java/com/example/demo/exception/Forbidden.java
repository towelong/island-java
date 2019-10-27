package com.example.demo.exception;

import lombok.Data;

/**
 * @Author WeLong
 * @create 2019/10/25 14:22
 */
@Data
public class Forbidden extends HttpException {

    private String msg = "资源已存在";
    private int code = 10001;

    public Forbidden(String msg) {
        this.msg = msg;
    }
    public Forbidden() {

    }
}
