package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author WeLong
 * @create 2019/10/27 12:12
 */
@Data
public class HotBook {
    private int id;
    @JsonProperty("index")
    private int indexs;
    private String image;
    private String author;
    private String title;
}
