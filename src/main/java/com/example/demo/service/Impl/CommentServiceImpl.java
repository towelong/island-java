package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Dao.CommentMapper;
import com.example.demo.exception.NotFound;
import com.example.demo.model.Comment;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author WeLong
 * @create 2019/10/27 16:03
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public void add(Comment comment){
        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        String content = comment.getContent();
        commentQueryWrapper.eq("content",content).eq("book_id",comment.getBookId());
        Comment exit = commentMapper.selectOne(commentQueryWrapper);
        if(exit != null){
            exit.setNums(exit.getNums()+1);
            commentMapper.update(exit,commentQueryWrapper);
        }
        else {
            comment.setBookId(comment.getBookId());
            comment.setContent(comment.getContent());
            comment.setNums(1);
            commentMapper.insert(comment);
        }
    }

    @Override
    public Map getComment(int book_id){
        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("book_id",book_id);
        Comment exit = commentMapper.selectOne(commentQueryWrapper);
        if(exit == null){
            throw new NotFound();
        }
        Map map = new HashMap();
        List list = new ArrayList();
        list.add(exit);
        map.put("book_id",book_id);
        map.put("comment",list);
        return map;
    }
}
