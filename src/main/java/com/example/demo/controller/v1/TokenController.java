/**
 * @作者 WeLong
 * @博客 $ https://towelong.cn
 * @开源项目 $ https://github.com/ToWeLong
 * @创建时间 2019/10/28 22:32
 */
package com.example.demo.controller.v1;

import com.example.demo.exception.Forbidden;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.TokenUtils;
import com.example.demo.utils.WxManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class TokenController {

    @Autowired
    UserService userService;

    @PostMapping("/token")
    public Map getToken(@RequestBody User user){
        Map map = new HashMap();
        int type =user.getType();
        if(type != 200){
            throw new Forbidden("类型不存在");
        }
        String account = user.getAccount();
        String openId = WxManager.getOpenId(account);
        long uid = userService.createMiniUser(openId);
        TokenUtils tokenUtils = new TokenUtils();
        String token = tokenUtils.CreateToken(uid,16);
        map.put("token",token);
        return map;
    }

}
