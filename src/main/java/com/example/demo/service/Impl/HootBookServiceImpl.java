package com.example.demo.service.Impl;

import com.example.demo.Dao.HotBookMapper;
import com.example.demo.model.HotBook;
import com.example.demo.service.HotBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author WeLong
 * @create 2019/10/27 12:16
 */
@Service
public class HootBookServiceImpl implements HotBookService {

    @Autowired
    HotBookMapper hotBookMapper;
    @Override
    public List<HotBook> getAll(){
        List<HotBook> hotBooks = hotBookMapper.selectList(null);
        return hotBooks;
    }
}
