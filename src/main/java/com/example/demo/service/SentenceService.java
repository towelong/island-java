package com.example.demo.service;

import com.example.demo.model.Sentence;

/**
 * @Author WeLong
 * @create 2019/10/25 20:51
 */
public interface SentenceService {
    Object getData(int art_id, int type);
    void updataData(int art_id, int type);
    void deleteData(int art_id, int type);
}
