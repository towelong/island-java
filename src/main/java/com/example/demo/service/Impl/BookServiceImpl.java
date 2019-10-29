package com.example.demo.service.Impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Dao.BookMapper;
import com.example.demo.exception.NotFound;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author WeLong
 * @create 2019/10/27 14:37
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;

    public void updateData(int art_id){
        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
        bookQueryWrapper.eq("id",art_id);
        Book book = bookMapper.selectOne(bookQueryWrapper);
        if(book==null){
            Book b = new Book();
            b.setId(art_id);
            b.setFavNums(1);
            bookMapper.insert(b);
        }else{
            Book books = new Book();
            books.setFavNums(book.getFavNums()+1);
            bookMapper.update(books,bookQueryWrapper);
        }
    }

    public void deleteData(int art_id){
        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
        bookQueryWrapper.eq("id",art_id);
        Book book = bookMapper.selectOne(bookQueryWrapper);
        if(book!=null){
            Book books = new Book();
            books.setId(art_id);
            books.setFavNums(book.getFavNums()-1);
            bookMapper.update(books,bookQueryWrapper);
        }else {
            throw new NotFound();
        }
    }
}
