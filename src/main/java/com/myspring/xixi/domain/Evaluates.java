package com.myspring.xixi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName t_evaluates
 */
@TableName(value ="t_evaluates")
@Data
public class Evaluates implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 商品id
     */
    private Long goodId;

    /**
     * 评价星级
     */
    private Integer starLevel;

    /**
     * 评价内容
     */
    private String evaluate;

    /**
     * 评价时间
     */
    private LocalDateTime created;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}