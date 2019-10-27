package com.example.demo.service;



/**
 * @Author WeLong
 * @create 2019/10/25 22:15
 */
public interface ArtService {

    Object getData(int art_id, int type);
    void updataData(int art_id, int type);
    void deleteData(int art_id, int type);
}
