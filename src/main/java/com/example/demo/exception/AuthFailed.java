package com.example.demo.exception;

import lombok.Data;

/**
 * @Author WeLong
 * @create 2019/10/23 13:04
 */
@Data
public class AuthFailed extends HttpException{
    private String msg = "无权限访问";
    private int code = -2;

    public AuthFailed(String msg) {
        this.msg = msg;
    }
    public AuthFailed() {

    }
}
