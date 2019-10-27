package com.example.demo.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author WeLong
 * @create 2019/10/25 11:30
 */

@Data
@NoArgsConstructor
public class Success extends HttpException {
    private int code = 0;
    private String msg = "请求成功";
    public Success(String msg){
        this.msg = msg;
    }
}
