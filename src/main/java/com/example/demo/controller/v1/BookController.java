package com.example.demo.controller.v1;

import com.example.demo.annotation.AuthToken;
import com.example.demo.exception.Success;
import com.example.demo.model.Comment;
import com.example.demo.model.HotBook;
import com.example.demo.service.CommentService;
import com.example.demo.service.FavorService;
import com.example.demo.service.HotBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.*;

/**
 * @Author WeLong
 * @create 2019/10/27 12:47
 */

@RestController
@RequestMapping("/v1/book")
@Validated
public class BookController {
    @Autowired
    HotBookService hotBookService;
    @Autowired
    FavorService favorService;
    @Autowired
    CommentService commentService;

    @GetMapping("/hot_list")
    @AuthToken
    public List<HotBook> getAll(){
        return hotBookService.getAll();
    }

    @GetMapping("/{id}/detail")
    @AuthToken
    public Object getBooks(@PathVariable @Min(value = 1,message = "id必须为正整数") int id){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://t.yushu.im/v2/book/id/{s}";
        Object result = restTemplate.getForObject(url,Object.class,id);
        return result;
    }
    @GetMapping("/favor/count")
    @AuthToken
    public Map getMyFavorBookCount(){
        int count = favorService.selectMyFavorBook();
        Map map = new HashMap();
        map.put("count",count);
        return map;
    }

    @GetMapping("/search")
    @AuthToken
    public Object getBookDetail(@RequestParam(name = "q")
                                    @NotEmpty(message = "q参数不能为空")
                                            String q,
                                @RequestParam(name = "count",defaultValue = "20")
                                @Min(value = 0,message = "count参数不合法")
                                            int count,
                                @RequestParam(name = "start",defaultValue = "0")
                                @Min(value = 0,message = "start参数不合法")
                                            int start
                                ){
        String url = "http://t.yushu.im/v2/book/search?start={start}&count={count}&summary=1&q={q}";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url,Object.class,start,count,q);
    }

    @GetMapping("/{book_id}/favor")
    @AuthToken
    public Map getBookFavor(@PathVariable
                                @Min(value = 0,message = "book_id只能为正整数") int book_id){
        return favorService.findBookLike(book_id);
    }

    @GetMapping("/hot_keyword")
    @AuthToken
    public Map getKeyWord(){
        List list = Arrays.asList("Fluent Python",
                "Python",
                "小程序Java核心编程",
                "慕课网7七月",
                "微信小程序开发入门与实践",
                "C++");
        Map map = new HashMap();
        map.put("hot",list);
        return map;
    }
    @PostMapping("/add/short_comment")
    @AuthToken
    public void addComment(@RequestBody @Validated Comment comment){
        commentService.add(comment);
        throw new Success();
    }

    @GetMapping("/{book_id}/short_comment")
    @AuthToken
    public Map addComment(@PathVariable @Min(value = 1,message = "book_id必须为正整数") int book_id){
        return commentService.getComment(book_id);
    }
}
