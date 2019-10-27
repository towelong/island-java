package com.example.demo.service;

import com.example.demo.model.Comment;

import java.util.Map;

/**
 * @Author WeLong
 * @create 2019/10/27 16:02
 */
public interface CommentService  {
    void add(Comment comment);
    Map getComment(int book_id);
}
