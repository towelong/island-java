package com.example.demo.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Dao.FlowMapper;
import com.example.demo.exception.NotFound;
import com.example.demo.model.Flow;
import com.example.demo.service.ArtService;
import com.example.demo.service.FavorService;
import com.example.demo.service.FlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author WeLong
 * @create 2019/10/25 19:09
 */
@Service
public class FlowServiceImpl implements FlowService {
    @Autowired
    FlowMapper flowMapper;

    @Autowired
    FavorService favorService;

    @Autowired
    ArtService artService;

    @Override
    public Object findLastest(){
        QueryWrapper<Flow> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("indexs");
        List<Flow> list = flowMapper.selectList(wrapper);
        int index = list.get(0).getIndexs();
        int art_id = list.get(0).getArtId();
        int type = list.get(0).getType();
        String str = JSON.toJSONString(artService.getData(art_id,type));
        Object object = JSON.parseObject(str);
        boolean likeIt = favorService.userLikeIt(art_id,type);
        if(likeIt){
            ((JSONObject) object).put("like_status",1);
        }else {
            ((JSONObject) object).put("like_status",0);
        }
        ((JSONObject) object).put("index",index);
        ((JSONObject) object).put("image","http://127.0.0.1:5000/"+((JSONObject) object).get("image"));
        return object;
    }

    @Override
    public Object findNext(int index){
        return this.find(index+1);
    }

    @Override
    public Object findLast(int index){
        return this.find(index-1);
    }

    /**
     *
     * @param type flow中的type号
     * @param id flow中的id号
     * @return Object
     */
    @Override
    public Object findDetail(int type,int id){
        QueryWrapper<Flow> wrapper = new QueryWrapper<>();
        wrapper.eq("type",type).eq("id",id);
        Flow flow = flowMapper.selectOne(wrapper);
        if(flow == null){
            throw new NotFound();
        }
        String str = JSON.toJSONString(artService.getData(flow.getArtId(),type));
        Object object = JSON.parseObject(str);
        boolean likeIt = favorService.userLikeIt(flow.getArtId(),type);
        if(likeIt){
            ((JSONObject) object).put("like_status",1);
        }else {
            ((JSONObject) object).put("like_status",0);
        }
        ((JSONObject) object).put("index",flow.getIndexs());
        ((JSONObject) object).put("image","http://127.0.0.1:5000/"+((JSONObject) object).get("image"));
        return object;
    }


    public Object find(int index){
        QueryWrapper<Flow> wrapper = new QueryWrapper<>();
        wrapper.eq("indexs",index);
        Flow exit = flowMapper.selectOne(wrapper);
        if(exit == null){
            throw new NotFound();
        }
        String str = JSON.toJSONString(artService.getData(exit.getArtId(),exit.getType()));
        Object object = JSON.parseObject(str);
        boolean likeIt = favorService.userLikeIt(exit.getArtId(),exit.getType());
        if(likeIt){
            ((JSONObject) object).put("like_status",1);
        }else {
            ((JSONObject) object).put("like_status",0);
        }
        ((JSONObject) object).put("index",index);
        ((JSONObject) object).put("image","http://127.0.0.1:5000/"+((JSONObject) object).get("image"));
        return object;
    }
}
