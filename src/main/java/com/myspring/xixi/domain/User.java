package com.myspring.xixi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 
 * @TableName t_user
 */
@TableName(value ="t_user")
@Data
public class User implements Serializable {
    /**
     * 用户ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户账号
     */
    @NotBlank
    private String username;

    /**
     * 用户密码
     */
    @NotBlank
    private String password;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 用户电话
     */
    private String telephone;

    /**
     * 用户邮箱
     */
    @NotBlank()
    @Email(message = "邮箱格式错误")
    private String email;

    /**
     * 用户所在地
     */
    private String city;

    /**
     * 用户银行账户
     */
    private String bankaccount;

    /**
     * 用户创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime created;

    /**
     * 用户等级
     */
    private Integer level;

    /**
     * 是否通过注册
     */
    private Integer pass;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}