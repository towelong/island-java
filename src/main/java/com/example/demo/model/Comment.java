package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @Author WeLong
 * @create 2019/10/27 15:58
 */
@Data
@TableName(value = "comment")
public class Comment {
    @TableId(type = IdType.AUTO)
    @JsonIgnore
    private int id;
    private String content;
    private int nums=0;

    @JsonProperty("book_id")
    @Min(value = 1,message = "book_id必须为正整数")
    @JsonIgnore
    private int bookId;
}
