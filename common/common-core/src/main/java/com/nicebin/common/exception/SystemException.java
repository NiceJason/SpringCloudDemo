package com.nicebin.common.exception;

import com.nicebin.common.constant.ErrorCodeConsts;
import lombok.Data;

/**
 * @Author DiaoJianBin
 * @Description 系统自定义异常
 * @Date 2021/3/14 11:51
 */
@Data
public class SystemException extends RuntimeException {
    //异常代码
    private String code;
    private String msg;

    public SystemException(String msg){
        super(msg);
        this.code = ErrorCodeConsts.ERROR;
        this.msg = msg;
    }

    public SystemException(String code,String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
