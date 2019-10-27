package com.example.demo.controller.v1;


import com.example.demo.annotation.AuthToken;
import com.example.demo.model.Favor;
import com.example.demo.service.FavorService;
import com.example.demo.service.FlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * @Author WeLong
 * @create 2019/10/25 19:17
 */

@RestController
@RequestMapping("/v1/classic")
public class ClassicController {

    @Autowired
    FlowService flowService;

    @Autowired
    FavorService favorService;

    @GetMapping("/latest")
    @AuthToken
    @ResponseBody
    public Object findLatest(){
        return flowService.findLastest();
    }

    @GetMapping("/{index}/next")
    @AuthToken
    @ResponseBody
    public Object findNext(@Validated @Min(value = 1,message = "index必须为整数") @PathVariable int index){
        return flowService.findNext(index);
    }

    @GetMapping("/{index}/previous")
    @AuthToken
    @ResponseBody
    public Object findLast(@Validated @Min(value = 1,message = "index必须为整数") @PathVariable int index){
        return flowService.findLast(index);
    }

    @GetMapping("/{type}/{id}")
    @AuthToken
    @ResponseBody
    public Object findDetail(@Validated
                                 @Min(value = 1,message = "index必须为正整数")
                                 @PathVariable int type,
                                 @Min(value = 1,message = "id必须为正整数")
                                 @PathVariable int id){
            return flowService.findDetail(type,id);
    }
    @GetMapping("/{type}/{id}/favor")
    @AuthToken
    @ResponseBody
    public Object findFavor(@Validated
                                @Min(value = 1,message = "index必须为正整数")
                                @PathVariable int type,
                                @Min(value = 1,message = "id必须为正整数")
                                @PathVariable int id){
        return favorService.getLikeInfo(id,type);
    }

    @GetMapping("/favor")
    @AuthToken
    @ResponseBody
    public List<Object> findMyFavor(){
        // art_id uid type
        return favorService.getfindMyFavor();
    }
}
