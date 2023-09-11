package com.myspring.xixi.common.lang;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    private int code;       //200表示正常， 非200表示异常
    private String msg;
    private Object data;

    public static Result success(int code, String msg, Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result success(int code, String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    public static Result success(String msg, Object data) {
        return success(200, msg, data);
    }

    public static Result success(Object data){
        return success(200, "操作成功", data);
    }

    public static Result fail(int code, String msg, Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

//    public static Result fail(String msg, Object data){
//        return fail(400, msg, data);
//    }

    public static Result fail(String msg){
        return fail(400, msg, null);
    }

}
