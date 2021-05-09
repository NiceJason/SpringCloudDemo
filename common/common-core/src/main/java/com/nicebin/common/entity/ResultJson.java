package com.nicebin.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nicebin.common.constant.ErrorCodeConsts;
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
public class ResultJson implements Serializable {
    @JsonIgnore
    private final long serialVersionUID = 1L;
    //消息状态码
    private String code;
    //消息简介
    private String msg;
    //消息内容
    private Object data;

    public ResultJson(Object data){
        this.code = ErrorCodeConsts.SUCCESS;
        this.data = data;
    }

    public ResultJson(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public ResultJson(String code,String msg,Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
