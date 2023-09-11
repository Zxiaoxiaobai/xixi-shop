package com.myspring.xixi.common.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginDTO implements Serializable {
    private Long id;
    private String username;
    private String password;
    private Integer level;
    private Integer pass;
}
