package com.example.demo.service;

import com.example.demo.model.Favor;

import java.util.List;
import java.util.Map;

/**
 * @Author WeLong
 * @create 2019/10/25 23:30
 */
public interface FavorService {

    void like(Favor favor);
    void dislike(Favor favor);
    boolean userLikeIt(int art_id,int type);
    Object getLikeInfo(int art_id,int type);
    List<Object> getfindMyFavor();
    int selectMyFavorBook();
    Map findBookLike(int book_id);

}
