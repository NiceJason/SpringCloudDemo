package com.nicebin.common.utils;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Pattern;

/**
 * @author NiceBin
 * @description: 工具类
 * @date 2019/6/24 18:49
 */
public class Tool {

    /**
     * 获得一定范围的安全随机数，随后转为int
     *
     * @param min 最小值，能转为数值型就行
     * @param max 最大值，能转为数值型就行
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static int getSecureRandom(Object min, Object max) throws NoSuchAlgorithmException {
        int theMin = 0, theMax = 0;
        //检测大小值是否合法
        boolean legalMin = false, legalMax = false;

        if (min instanceof String) {
            theMin = Integer.parseInt((String) min);
            legalMin = true;
        }else if (min instanceof Integer) {
            theMin = (Integer) min;
            legalMin = true;
        }else if(min instanceof Long){
            theMin = ((Long) min).intValue();
            legalMin = true;
        }

        if (max instanceof String) {
            theMax = Integer.parseInt((String) max);
            legalMax = true;
        }else if (max instanceof Integer) {
            theMax = (Integer) max;
            legalMax = true;
        }else if(max instanceof Long){
            theMax = ((Long) max).intValue();
            legalMax = true;
        }

        if (!(legalMax && legalMin)) {
            throw new RuntimeException("随机数参数不正确");
        }

        //这种方法获取的随机数最安全
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        //nextInt值生成0到n之间的数，包含0不包含n，所以要+1来包含
        return secureRandom.nextInt(theMax - theMin + 1) + theMin;
    }

    /**
     * 判断是否为整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断字符串是否是数字或字母
     *
     * @param str
     * @return
     */
    public static boolean isLetterDigit(String str) {
        Pattern pattern = Pattern.compile("^[a-z0-9A-Z]+$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断字符串是否是邮箱
     *
     * @param str
     * @return
     */
    public static boolean isEmail(String str) {
        Pattern pattern = Pattern.compile("\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        if (str == null || "".equals(str)) {
            return true;
        } else return false;
    }

    /**
     * 判断字符串是否是Web地址
     *
     * @param str
     * @return
     */
    public static boolean isWebURL(String str) {
        Pattern pattern = Pattern.compile("(http|https):\\/\\/([\\w.]+\\/?)\\S*");
        return pattern.matcher(str).matches();
    }

    /**
     * String转对象
     * @param value
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T stringToBean(String value, Class<T> clazz) {
        if(value==null||value.length()<=0||clazz==null){
            return null;
        }

        if(clazz ==int.class ||clazz==Integer.class){
            return (T)Integer.valueOf(value);
        }
        else if(clazz==long.class||clazz==Long.class){
            return (T)Long.valueOf(value);
        }
        else if(clazz==String.class){
            return (T)value;
        }else {
            //这一步是转为对象的
            return JSON.toJavaObject(JSON.parseObject(value),clazz);
        }
    }

    /**
     * 对象转String
     * @param value
     * @param <T>
     * @return
     */
    public static <T> String beanToString(T value) {

        if(value==null){
            return null;
        }
        Class <?> clazz = value.getClass();
        if(clazz==int.class||clazz==Integer.class){
            return ""+value;
        }
        else if(clazz==long.class||clazz==Long.class){
            return ""+value;
        }
        else if(clazz==String.class){
            return (String)value;
        }else {
            return JSON.toJSONString(value);
        }
    }

    /**
     * 获得代理类方法中真实的方法
     * 小知识：
     * AopProxyUtils.ultimateTargetClass()
     * 获取一个代理对象的最终对象类型
     * @param joinPoint 切面的切点类
     * @return
     */
    public static Method getSpecificMethod(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method poxyMethod = methodSignature.getMethod();
        Class targetClass = AopProxyUtils.ultimateTargetClass(joinPoint.getTarget());
        return Tool.getSpecificMethod(poxyMethod,targetClass);
    }

    /**
     * 获得代理类方法中真实的方法
     * 小知识：
     * ClassUtils.getMostSpecificMethod(Method method, Class<?> targetClass)
     * 该方法是一个有趣的方法，他能从代理对象上的一个方法，找到真实对象上对应的方法。
     * 举个例子，MyComponent代理之后的对象上的someLogic方法，肯定是属于cglib代理之后的类上的method，
     * 使用这个method是没法去执行目标MyComponent的someLogic方法，
     * 这种情况下，就可以使用getMostSpecificMethod，
     * 找到真实对象上的someLogic方法，并执行真实方法
     *
     * BridgeMethodResolver.findBridgedMethod(Method bridgeMethod)
     * 如果当前方法是一个泛型方法，则会找Class文件中实际实现的方法
     * @param poxyMethod 代理的方法
     * @param targetclass 真实的目标类
     * @return
     */
    public static Method getSpecificMethod(Method poxyMethod,Class targetclass){
        Method specificMethod = ClassUtils.getMostSpecificMethod(poxyMethod,targetclass);
        specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);
        return specificMethod;
    }

    /**
     * String转义字符的转换，为了Json数据传输，“”转''
     * @param str
     * @return
     */
    public static String changeStrForJson(String str){
        str = str.replaceAll("\"","'");
        return str;
    }
}
