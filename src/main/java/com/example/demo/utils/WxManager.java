/**
 * @作者 WeLong
 * @博客 $ https://towelong.cn
 * @开源项目 $ https://github.com/ToWeLong
 * @创建时间 2019/10/28 19:51
 */
package com.example.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.wx;
import org.springframework.web.client.RestTemplate;

public class WxManager {
    public static String getOpenId(String code) {
        RestTemplate restTemplate = new RestTemplate();
        wx wxs = new wx();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid={appId}&secret={appSecret}&js_code={code}&grant_type=authorization_code";
        String appId = wxs.getAppId();
        String appSecret = wxs.getAppSecret();
        String result = restTemplate.getForObject(url,String.class,appId,appSecret,code);
        JSONObject jsonObject = JSON.parseObject(result);
        String openId = jsonObject.get("openid").toString();
        return openId;
    }
}
