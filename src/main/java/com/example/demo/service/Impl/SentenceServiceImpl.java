package com.example.demo.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Dao.SentenceMapper;
import com.example.demo.model.Sentence;
import com.example.demo.service.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author WeLong
 * @create 2019/10/25 20:56
 */
@Service
public class SentenceServiceImpl implements SentenceService {
    @Autowired
    SentenceMapper sentenceMapper;

    @Override
    public Object getData(int art_id,int type){
        QueryWrapper<Sentence> sentenceQueryWrapper = new QueryWrapper<>();
        sentenceQueryWrapper.eq("id",art_id).eq("type",type);
        Sentence art = sentenceMapper.selectOne(sentenceQueryWrapper);
        return art;
    }

    @Override
    public void updataData(int art_id, int type){
        QueryWrapper<Sentence> sentenceQueryWrapper = new QueryWrapper<>();
        sentenceQueryWrapper.eq("id",art_id).eq("type",type);
        Sentence art = sentenceMapper.selectOne(sentenceQueryWrapper);
        Sentence sentence = new Sentence();
        sentence.setFavNums(art.getFavNums()+1);
        sentence.setType(art.getType()); // 留个疑问，更新操作时，type会被赋值为0
        sentenceMapper.update(sentence,sentenceQueryWrapper);
    }

    @Override
    public void deleteData(int art_id, int type){
        QueryWrapper<Sentence> sentenceQueryWrapper = new QueryWrapper<>();
        sentenceQueryWrapper.eq("id",art_id).eq("type",type);
        Sentence art = sentenceMapper.selectOne(sentenceQueryWrapper);
        Sentence sentence = new Sentence();
        sentence.setFavNums(art.getFavNums()-1);
        sentence.setType(art.getType()); // 留个疑问，更新操作时，type会被赋值为0
        sentenceMapper.update(sentence,sentenceQueryWrapper);
    }
}
