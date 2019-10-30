/**
 * @作者 WeLong
 * @博客 $ https://towelong.cn
 * @开源项目 $ https://github.com/ToWeLong
 * @创建时间 2019/10/30 12:27
 */
package com.example.demo.controller.v1;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@ResponseBody
@RestController
public class TestController {

    @PostMapping("/test")
    public String test(@RequestBody Map<String, Object> map) {
        String code = map.get("code").toString();
        String type = map.get("type").toString();
        int t = Integer.parseInt(type);
        return "code:" + code + ",type:" + t;
    }
}
