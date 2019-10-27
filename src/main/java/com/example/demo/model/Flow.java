package com.example.demo.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author WeLong
 * @create 2019/10/25 19:01
 */

@Data
@TableName(value = "flow")
public class Flow {
    private int indexs;
    @JSONField(name = "ard_id")
    @JsonProperty("art_id")
    private int artId;
    private int type;
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    @JSONField(deserialize = false)
    private int status;
    @JSONField(deserialize = false,name = "created_at")
    private LocalDateTime createdAt;
    @JSONField(deserialize = false,name = "updated_at")
    private LocalDateTime updatedAt;
    @JSONField(deserialize = false,name = "deleted_at")
    private LocalDateTime deletedAt;
}
