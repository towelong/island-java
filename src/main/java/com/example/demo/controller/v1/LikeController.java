package com.example.demo.controller.v1;

import com.example.demo.annotation.AuthToken;
import com.example.demo.exception.Success;
import com.example.demo.model.Favor;
import com.example.demo.service.FavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author WeLong
 * @create 2019/10/25 23:46
 */
@RestController
@RequestMapping("/v1/like")
public class LikeController {

    @Autowired
    FavorService favorService;

    @PostMapping("/add")
    @AuthToken
    public void like(@RequestBody @Validated Favor favor) {
        favorService.like(favor);
        throw new Success("点赞成功！");
    }

    @PostMapping("/cancel")
    @AuthToken
    public void dislike(@RequestBody @Validated Favor favor) {
        favorService.dislike(favor);
        throw new Success("取消点赞成功！");
    }
}
