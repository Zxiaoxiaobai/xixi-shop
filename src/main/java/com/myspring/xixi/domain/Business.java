package com.myspring.xixi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * @TableName t_business
 */
@TableName(value ="t_business")
@Data
public class Business implements Serializable {
    /**
     * 商户id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 商店名称
     */
    private String shopName;

    /**
     * 商店老板
     */
    private Long userId;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 银行账户
     */
    private String bankAccount;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime created;

    /**
     * 是否通过
     */
    private Integer pass;

    /**
     * 营业执照
     */
    private byte[] picShop;

    /**
     * 身份证
     */
    private byte[] picId;

        /**
     * 商店等级
     */
    private Integer level;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}