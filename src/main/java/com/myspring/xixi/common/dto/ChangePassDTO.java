package com.myspring.xixi.common.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChangePassDTO implements Serializable {
    private Long id;
    private Integer pass;
}
