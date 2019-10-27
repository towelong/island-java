package com.example.demo.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author WeLong
 * @create 2019/10/25 20:17
 */

@Data
public class ArtBase {

    private String image;
    private String title;
    @JSONField(format = "yyyy-MM-dd")
    private Date pubdate;
    private String content;
    @JSONField(name = "fav_nums")
    @JsonProperty("fav_nums")
    private int favNums;
    private int type;
    @TableId(type = IdType.AUTO)
    private int id;

    @JsonIgnore
    private LocalDateTime createdAt;


    @JsonIgnore
    private LocalDateTime updatedAt;

    @JsonIgnore
    private LocalDateTime deletedAt;
}
