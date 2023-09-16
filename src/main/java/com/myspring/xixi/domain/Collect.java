package com.myspring.xixi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 *
 * @TableName t_bank
 */
@TableName(value ="t_collect")
@Data
public class Collect implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    private Integer userId;

    /**
     *
     */
    private Integer goodsId;

    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}

