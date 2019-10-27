package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author WeLong
 * @create 2019/10/25 20:16
 */
@Data
@TableName(value = "music")
public class Music extends ArtBase {

    private String url;
}
