package com.myspring.xixi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName t_integrals
 */
@TableName(value ="t_integrals")
@Data
public class Integrals implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long pass;

    /**
     * username
     */
    private String username;

    /**
     * information
     */
    private String information;
    /**
     * id
     * */
    private Integer Id;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
