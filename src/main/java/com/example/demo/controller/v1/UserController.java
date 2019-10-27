package com.example.demo.controller.v1;


import com.amdelamar.jhash.exception.InvalidHashException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.Result;
import com.example.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * @Author WeLong
 * @create 2019/10/24 20:10
 */

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public Result create(@RequestBody @Validated User user) {
        userService.createUser(user);
        return ResultUtils.success("注册成功！");
    }

    @PostMapping("/login")
    public Map login(@RequestBody @Validated User user) throws InvalidHashException {
        String token = userService.verifyUser(user);
        Map map = new HashMap();
        map.put("token",token);
        return map;
    }
}
