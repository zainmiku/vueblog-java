package com.fanhoufang.common.lang;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public static Result success(Integer code, String msg, Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
    public static Result success(Object data){
        Result result = new Result();
        return success(200,"操作成功",data);
    }

    public static Result fail(Integer code, String msg, Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
    public static Result fail(String msg, Object data){

        return fail(400,msg,data);
    }
    public static Result fail(String msg){

        return fail(400,msg,null);
    }

}
