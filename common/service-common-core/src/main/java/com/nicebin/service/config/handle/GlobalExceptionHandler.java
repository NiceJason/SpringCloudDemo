package com.nicebin.service.config.handle;

import com.nicebin.api.core.Result;
import com.nicebin.api.core.ResultCode;
import com.nicebin.common.exception.SystemException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.validation.ValidationException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 用于捕获和处理Controller抛出的异常
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {



    /**
     * 请求方式异常
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public Result handleException(HttpRequestMethodNotSupportedException e) {
        return new Result(ResultCode.PARAMETER_ERROR, "不支持该请求方式："+e.getMethod());
    }

    /**
     * 参数格式异常
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseBody
    public Result handleException(HttpMessageConversionException e) {
        log.error(e.getMessage());
        return new Result(ResultCode.PARAMETER_ERROR, "参数格式异常");
    }

    /**
     * 参数缺失异常
     */
    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseBody
    public Result handleException(ServletRequestBindingException e) {
        return new Result(ResultCode.PARAMETER_ERROR, e.getMessage());
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public Result handleException(ValidationException e) {
        return new Result(ResultCode.PARAMETER_ERROR, e.getMessage());
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    @ResponseBody
    public Result handleException(InvalidDataAccessApiUsageException e) {
        return new Result(ResultCode.PARAMETER_ERROR, e.getMessage());
    }

    /**
     * 数据库违反唯一索引异常
     */
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    @ResponseBody
    public Result handleException(DataIntegrityViolationException e){
        SQLIntegrityConstraintViolationException exception = null;
        Throwable cause1 = e.getCause();
        if(cause1 instanceof SQLIntegrityConstraintViolationException){
            exception = (SQLIntegrityConstraintViolationException)cause1;
        }else{
            Throwable cause2 = cause1.getCause();
            if(cause2 instanceof SQLIntegrityConstraintViolationException){
                exception = (SQLIntegrityConstraintViolationException)cause2;
            }
        }

        if(exception!=null){
            String message = exception.getMessage();
            if(message!=null && message.contains("Duplicate entry")){
                int start = message.indexOf("'");
                int end = message.indexOf("'",start+1);
                return new Result(ResultCode.PARAMETER_ERROR,message.substring(start,end)+"数据已存在");
            }
        }

        return Result.error("系统异常");
    }
    
    /**
     * 自定义系统异常
     */
    @ExceptionHandler(SystemException.class)
    @ResponseBody
    public Result handleException(SystemException e) {
        log.error("自定义系统异常:" + e.getMsg());
        if (e.getCause() != null) {
            log.error(e.getMsg(),e.getCause());
        }

        String errorCode = ResultCode.ERROR;
        if (e.getCode() != null) {
            errorCode = e.getCode();
        }

        String errorMeg = "系统错误";
        if (e.getMsg() != null) {
            errorMeg = e.getMsg();
        }

        return new Result(errorCode, errorMeg);
    }

    /**
     * 自定义业务异常
     */
    @ExceptionHandler(FeignException.class)
    @ResponseBody
    public Result handleException(FeignException e) {
        return new Result(ResultCode.FEIGN_ERROR, e.getMessage());
    }

    /**
     * 自定义业务异常
     */
    @ExceptionHandler(MissingServletRequestPartException.class)
    @ResponseBody
    public Result handleException(MissingServletRequestPartException e) {
        return new Result(ResultCode.PARAMETER_ERROR, "缺少必要参数,参数名称为:" + e.getRequestPartName());
    }

    /**
     * 全局异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(Exception e) {
        log.error("全局捕抓系统异常:" + e.getMessage(), e);
        return Result.error("系统异常");
    }
}
