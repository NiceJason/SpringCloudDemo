package annotation;

import net.bytebuddy.description.annotation.AnnotationDescription;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @Author DiaoJianBin
 * @Date 2021/9/6 15:39
 */
@DIY("友情破颜拳")
public class ChangeParamTest {

    public static void main(String[] args) {
        // 获取到DIY注解
        DIY diy = ChangeParamTest.class.getAnnotation(DIY.class);
        System.out.println("注解原本的参数： "+diy.value());
        // 获取到代理处理器
        Object handler = Proxy.getInvocationHandler(diy);
        Field f;

        try {
            // 获取 memberValues
            f = handler.getClass().getDeclaredField("memberValues");
        } catch (NoSuchFieldException | SecurityException e) {
            throw new IllegalStateException(e);
        }
        f.setAccessible(true);
        Map<String, Object> memberValues;
        try {
            // 获取memberValues的map实例
            memberValues = (Map<String, Object>) f.get(handler);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
        // 修改需要的值
        memberValues.put("value","星爆气流斩");
        System.out.println("注解替换后的参数： "+diy.value());
    }
}
