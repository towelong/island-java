package com.example.demo.service.Impl;

import com.example.demo.exception.NotFound;
import com.example.demo.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author WeLong
 * @create 2019/10/25 20:11
 */
@Service
public class ArtServiceImpl implements ArtService {
    @Autowired
    MovieService movieService;
    @Autowired
    MusicService musicService;
    @Autowired
    SentenceService sentenceService;
    @Autowired
    BookService bookService;

    @Override
    public Object getData(int art_id, int type){
        switch (type){
            case 100:
                return movieService.getData(art_id,type);
            case 200:
                return musicService.getData(art_id,type);
            case 300:
                return sentenceService.getData(art_id,type);
            case 400:
                break;
             default:
                 throw new NotFound("资源未找到");
        }
        throw new NotFound("资源未找到");
    }

    @Override
    public void updataData(int art_id, int type){
        switch (type){
            case 100:
                movieService.updataData(art_id,type);
                break;
            case 200:
                musicService.updataData(art_id,type);
                break;
            case 300:
                sentenceService.updataData(art_id,type);
                break;
            case 400:
                bookService.updataData(art_id);
                break;
            default:
                throw new NotFound("点赞失败！");
        }
    }

    @Override
    public void deleteData(int art_id, int type){
        switch (type){
            case 100:
                movieService.deleteData(art_id,type);
                break;
            case 200:
                musicService.deleteData(art_id,type);
                break;
            case 300:
                sentenceService.deleteData(art_id,type);
                break;
            case 400:
                bookService.deleteData(art_id);
                break;
            default:
                throw new NotFound("取消点赞失败！");
        }
    }
}
