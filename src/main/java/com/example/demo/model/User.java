package com.example.demo.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.amdelamar.jhash.Hash;
import com.amdelamar.jhash.algorithms.Type;
import com.amdelamar.jhash.exception.InvalidHashException;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author WeLong
 * @create 2019/10/21 18:38
 */
@Data
@TableName(value = "user")
public class User {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String nickname;
    private String email;
    private String password;
    private String openid;
    @TableField(exist = false)
    private int type;
    @TableField(exist = false)
    private String account;
    @TableField(exist = false)
    private String token;

    @JSONField(name = "created_at")
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JSONField(name = "updated_at")
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    @JSONField(name = "deleted_at")
    @JsonProperty("deleted_at")
    private LocalDateTime deletedAt;


    /**
     * 设置密文密码
     *
     * @param password 原始密码
     */
    public void setPasswordEncrypt(String password) {
        char[] chars = password.toCharArray();
        this.password = Hash.password(chars).algorithm(Type.PBKDF2_SHA256).create();
    }

    /**
     * 验证加密密码
     *
     * @param password 密文密码
     * @return valid
     */
    public boolean verify(String password) {
        char[] chars = password.toCharArray();
        try {
            return Hash.password(chars).verify(this.password);
        } catch (InvalidHashException e) {
            return false;
        }
    }

}
