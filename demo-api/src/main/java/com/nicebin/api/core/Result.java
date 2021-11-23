package com.nicebin.api.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author DiaoJianBin
 * @Description 信息返回类
 * @Date 2021/3/11 10:48
 */
@Data
@NoArgsConstructor
public class Result implements Serializable {
    @JsonIgnore
    private final long serialVersionUID = 1L;
    //消息状态码
    private String code;
    //消息内容
    private Object data;

    public Result(String code, Object data){
        this.code = code;
        this.data = data;
    }

    public static Result success(Object data){
        Result result = new Result(ResultCode.SUCCESS,data);
        return result;
    }

    public static Result error(Object data){
        Result result = new Result(ResultCode.ERROR,data);
        return result;
    }
}
