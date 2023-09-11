package com.myspring.xixi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName t_order
 */
@TableName(value ="t_order")
@Data
public class Order implements Serializable {
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
     * 商品个数
     */
    private Integer num;

    /**
     * 总价
     */
    private BigDecimal price;

    /**
     * 实付
     */
    private BigDecimal actualPrice;

    /**
     * 收货地址
     */
    private Long addressId;

    /**
     * 创建时间
     */
    private LocalDateTime created;

    /**
     * 发货
     */
    private Integer delived;

    /**
     * 收货
     */
    private Integer getGood;

    /**
     * 退货
     */
    private Integer reGood;

    /**
     * 评价
     */
    private Integer evaluate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}