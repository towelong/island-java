package com.example.demo.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Dao.MusicMapper;
import com.example.demo.model.Music;
import com.example.demo.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author WeLong
 * @create 2019/10/25 20:56
 */
@Service
public class MusicServiceImpl implements MusicService {

    @Autowired
    MusicMapper musicMapper;

    @Override
    public Object getData(int art_id, int type){
        QueryWrapper<Music> musicQueryWrapper = new QueryWrapper<>();
        musicQueryWrapper.eq("id",art_id).eq("type",type);
        Music art = musicMapper.selectOne(musicQueryWrapper);
        return art;
    }

    @Override
    public void updataData(int art_id, int type){
        QueryWrapper<Music> musicQueryWrapper = new QueryWrapper<>();
        musicQueryWrapper.eq("id",art_id).eq("type",type);
        Music art = musicMapper.selectOne(musicQueryWrapper);
        Music music = new Music();
        music.setFavNums(art.getFavNums()+1);
        music.setType(art.getType()); // 留个疑问，更新操作时，type会被赋值为0
        musicMapper.update(music,musicQueryWrapper);
    }

    @Override
    public void deleteData(int art_id, int type){
        QueryWrapper<Music> musicQueryWrapper = new QueryWrapper<>();
        musicQueryWrapper.eq("id",art_id).eq("type",type);
        Music art = musicMapper.selectOne(musicQueryWrapper);
        Music music = new Music();
        music.setFavNums(art.getFavNums()-1);
        music.setType(art.getType()); // 留个疑问，更新操作时，type会被赋值为0
        musicMapper.update(music,musicQueryWrapper);
    }
}
