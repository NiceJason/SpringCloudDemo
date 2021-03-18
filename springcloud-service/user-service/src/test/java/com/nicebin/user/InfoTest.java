package com.nicebin.user;

import org.junit.Test;

import java.util.Date;

/**
 * @Author DiaoJianBin
 * @Description
 * @Date 2021/3/1 17:23
 */
public class InfoTest {
    String a = "123";
    Date date = new Date();

    public String toString() {
        return a + " " + date.toString();
    }

    @Test
    public void test(){
        try{
            throw new RuntimeException("异常了");
        }catch (RuntimeException e){
            System.out.println("RuntimeException被捕捉 "+e.getMessage());
        }catch (Throwable throwable){
            System.out.println("Throwable被捕捉 "+throwable.getMessage());
        }
    }
}
