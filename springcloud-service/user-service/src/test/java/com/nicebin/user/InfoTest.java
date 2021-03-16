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
        int[][] a = {
                {1,2,3},
                {4,5}
        };

        System.out.println(a[0].length+" "+a[1].length);
    }
}
