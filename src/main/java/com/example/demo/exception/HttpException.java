package com.example.demo.exception;

import com.example.demo.utils.RequestHelper;
import lombok.Data;

/**
 * @Author WeLong
 * @create 2019/10/23 10:01
 */
@Data
public class HttpException extends RuntimeException {


    private String msg;
    private int code;
    private String request = RequestHelper.getRequestUrl();

}
