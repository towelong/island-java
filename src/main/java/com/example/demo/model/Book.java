package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author WeLong
 * @create 2019/10/27 13:06
 */
@TableName(value = "book")
@Data
public class Book {
    private int id;
    @JsonProperty(value = "fav_nums")
    private int favNums=0;

}
