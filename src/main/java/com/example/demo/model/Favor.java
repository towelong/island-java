package com.example.demo.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;

/**
 * @Author WeLong
 * @create 2019/10/25 23:13
 */
@Data
public class Favor {
    @TableId(type = IdType.AUTO)
    private int id;

    private int uid;

    @Min(value = 0,message = "art_id只能为整数")
    @JSONField(name = "art_id")
    @JsonProperty("art_id")
    private int artId;
    @Min(value = 0,message = "type只能为整数")
    private int type;

    @JSONField(name = "created_at",serialize = false)
    @JsonIgnore
    private LocalDateTime createdAt;

    @JSONField(name = "updated_at",serialize = false)
    @JsonIgnore
    private LocalDateTime updatedAt;

    @JSONField(name = "deleted_at",serialize = false)
    @JsonIgnore
    private LocalDateTime deletedAt;
}
