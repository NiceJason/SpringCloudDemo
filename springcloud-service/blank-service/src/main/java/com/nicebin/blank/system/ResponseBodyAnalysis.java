package com.nicebin.blank.system;

import com.nicebin.common.entity.ResultJson;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Author DiaoJianBin
 * @Description
 * @Date 2021/3/14 12:46
 */
@ControllerAdvice
public class ResponseBodyAnalysis implements ResponseBodyAdvice<ResultJson> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        System.out.println("进入了support");
        Class clazz = returnType.getParameterType();
        if(clazz == ResultJson.class){
            return true;
        }
        return false;
    }

    @Override
    public ResultJson beforeBodyWrite(ResultJson body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        System.out.println("返回值可以进行修改");
        return body;
    }
}
