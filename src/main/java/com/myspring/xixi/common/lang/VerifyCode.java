package com.myspring.xixi.common.lang;

import lombok.Data;

import java.io.Serializable;

@Data
public class VerifyCode implements Serializable {

    private String code;
    private byte[] imgBytes;

}
