package com.example.demo.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Dao.MovieMapper;
import com.example.demo.model.Movie;
import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author WeLong
 * @create 2019/10/25 20:55
 */
@Service
public class MovieServiceImpl implements MovieService{
    @Autowired
    MovieMapper movieMapper;

    @Override
    public Object getData(int art_id, int type){
        QueryWrapper<Movie> movieQueryWrapper = new QueryWrapper<>();
        movieQueryWrapper.eq("id",art_id).eq("type",type);
        Movie art = movieMapper.selectOne(movieQueryWrapper);
        return art;
    }

    @Override
    public void updataData(int art_id, int type){
        QueryWrapper<Movie> movieQueryWrapper = new QueryWrapper<>();
        movieQueryWrapper.eq("id",art_id).eq("type",type);
        Movie art = movieMapper.selectOne(movieQueryWrapper);
        Movie movie = new Movie();
        movie.setFavNums(art.getFavNums()+1);
        movie.setType(art.getType()); // 留个疑问，更新操作时，type会被赋值为0
        movieMapper.update(movie,movieQueryWrapper);
    }

    @Override
    public void deleteData(int art_id, int type){
        QueryWrapper<Movie> movieQueryWrapper = new QueryWrapper<>();
        movieQueryWrapper.eq("id",art_id).eq("type",type);
        Movie art = movieMapper.selectOne(movieQueryWrapper);
        Movie movie = new Movie();
        movie.setFavNums(art.getFavNums()-1);
        movie.setType(art.getType()); // 留个疑问，更新操作时，type会被赋值为0
        movieMapper.update(movie,movieQueryWrapper);
    }
}
