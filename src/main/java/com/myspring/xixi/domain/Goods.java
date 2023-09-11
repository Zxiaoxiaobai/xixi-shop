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
 * @TableName t_goods
 */
@TableName(value ="t_goods")
@Data
public class Goods implements Serializable {
    /**
     * 商品id commodityId
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 分类
     */
    private Integer belongId;

    /**
     * 单价
     */
    private Integer price;

    /**
     * 联系方式
     */
    private Integer discount;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 创建时间
     */
    private LocalDateTime created;

    /**
     * 是否通过
     */
    private Integer pass;

    /**
     * 商品照片
     */
    private byte[] picGoods;

    /**
     * 所属人id
     */
    private Integer piece;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
