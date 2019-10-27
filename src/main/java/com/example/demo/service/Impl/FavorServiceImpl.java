package com.example.demo.service.Impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Dao.BookMapper;
import com.example.demo.Dao.FavorMapper;
import com.example.demo.exception.Forbidden;
import com.example.demo.exception.NotFound;
import com.example.demo.model.Book;
import com.example.demo.model.Favor;
import com.example.demo.service.ArtService;
import com.example.demo.service.FavorService;
import com.example.demo.service.MovieService;
import com.example.demo.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @Author WeLong
 * @create 2019/10/25 23:33
 */
@Service
@Transactional
public class FavorServiceImpl implements FavorService {

    @Autowired
    FavorMapper favorMapper;
    @Autowired
    BookMapper bookMapper;
    @Autowired
    ArtService artService;

    @Override
    public void like(Favor favor){
        this.likes(favor.getArtId(),favor.getType());
    }

    @Override
    public void dislike(Favor favor){
        this.dislikes(favor.getArtId(),favor.getType());

    }

    public void likes(int art_id,int type){
        int uid = new TokenUtils().getUid();
        QueryWrapper<Favor> favorQueryWrapper = new QueryWrapper<>();
        favorQueryWrapper.eq("art_id",art_id).eq("type",type).eq("uid",uid);
        Favor exit = favorMapper.selectOne(favorQueryWrapper);
        if(exit != null){
            throw new Forbidden("你已经点赞过");
        }
        Favor favors = new Favor();
        favors.setArtId(art_id);
        favors.setType(type);
        favors.setUid(uid);
        favors.setCreatedAt(LocalDateTime.now());
        favors.setUpdatedAt(LocalDateTime.now());
        favorMapper.insert(favors);
        artService.updataData(art_id,type);
    }

    public void dislikes(int art_id,int type){
        int uid = new TokenUtils().getUid();
        QueryWrapper<Favor> favorQueryWrapper = new QueryWrapper<>();
        favorQueryWrapper.eq("art_id",art_id).eq("type",type).eq("uid",uid);
        Favor exit = favorMapper.selectOne(favorQueryWrapper);
        if(exit != null){
            favorMapper.delete(favorQueryWrapper);
            artService.deleteData(art_id,type);
        }else{
            throw new NotFound("未找到该记录");
        }
    }
    @Override
    public boolean userLikeIt(int art_id,int type){
        int uid = new TokenUtils().getUid();
        QueryWrapper<Favor> favorQueryWrapper = new QueryWrapper<>();
        favorQueryWrapper.eq("art_id",art_id).eq("type",type).eq("uid",uid);
        Favor exit = favorMapper.selectOne(favorQueryWrapper);
        return exit!=null ? true : false;
    }

    @Override
    public Object getLikeInfo(int art_id,int type){
        QueryWrapper<Favor> favorQueryWrapper = new QueryWrapper<>();
        favorQueryWrapper.eq("art_id",art_id).eq("type",type);
        Favor favor = favorMapper.selectOne(favorQueryWrapper);
        if(favor == null){
            throw new NotFound();
        }
        String s = JSON.toJSONString(artService.getData(favor.getArtId(),favor.getType()));
        Object object = JSON.parseObject(s);
        String fav_nums = JSON.toJSONString(((JSONObject) object).get("fav_nums"));
        String id = JSON.toJSONString(((JSONObject) object).get("id"));
        int like_status = this.userLikeIt(art_id,type)? 1 : 0;
        Map<String,Object> map = new HashMap<>();
        map.put("fav_nums",fav_nums);
        map.put("id",id);
        map.put("like_status",like_status);
        return map;
    }
    @Override
    public List<Object> getfindMyFavor(){
        int uid = new TokenUtils().getUid();
        Map<String,Object> map = new HashMap<>();
        map.put("uid",uid);
        favorMapper.selectByMap(map);
        List list = favorMapper.selectByMap(map);
        List data = new ArrayList();
        if(list.size()==0){
            throw new NotFound();
        }
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
            String str = JSON.toJSONString(list.get(i));
            Object object= JSON.parseObject(str);
            int artId = (int)((JSONObject) object).get("art_id");
            int type = (int)((JSONObject) object).get("type");
            if(type == 400){
                continue;
            }
            data.add(artService.getData(artId,type));
        }
        return data;
    }

    @Override
    public int selectMyFavorBook(){
        int uid = new TokenUtils().getUid();
        QueryWrapper<Favor> favorQueryWrapper= new QueryWrapper<>();
        favorQueryWrapper.eq("type",400).eq("uid",uid);
        return favorMapper.selectCount(favorQueryWrapper);
    }

    @Override
    public Map findBookLike(int book_id){
        QueryWrapper<Favor> favorQueryWrapper= new QueryWrapper<>();
        favorQueryWrapper.eq("art_id",book_id);
        Favor favor = favorMapper.selectOne(favorQueryWrapper);
        if(favor == null){
            throw new NotFound();
        }
        int bookId = favor.getArtId();
        QueryWrapper<Book> bookQueryWrapper= new QueryWrapper<>();
        bookQueryWrapper.eq("id",bookId);
        Book book = bookMapper.selectOne(bookQueryWrapper);
        if(book == null){
            throw new NotFound();
        }
        int favNums = book.getFavNums();
        Map map = new HashMap();
        map.put("like_status",this.userLikeIt(book_id,400)?1:0);
        map.put("id",bookId);
        map.put("fav_nums",favNums);
        return map;
    }
}
