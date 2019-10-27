package com.example.demo.service;


import com.example.demo.model.Flow;


/**
 * @Author WeLong
 * @create 2019/10/25 19:07
 */
public interface FlowService {
    Object findLastest();
    Object findNext(int indexs);
    Object findLast(int index);
    Object findDetail(int type,int id);
}
