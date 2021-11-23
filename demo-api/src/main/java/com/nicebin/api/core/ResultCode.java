package com.nicebin.api.core;

/**
 * @Author DiaoJianBin
 * @Description 错误码，参考阿里巴巴手册
 * @Date 2021/3/11 11:00
 */
public class ResultCode {
    //成功调用
    public static final String SUCCESS = "00000";

    //参数异常
    public static final String PARAMETER_ERROR = "A001";

    //系统执行时出错
    public static final String ERROR = "B0001";

    //系统服务降级
    public static final String DEGRADATION = "B0220";

    //系统Feign调用异常
    public static final String FEIGN_ERROR = "C0001";
}
