package Interger_test;

import org.junit.Test;

/**
 * @Author DiaoJianBin
 * @Date 2021/11/19 15:27
 */
public class TestMain {

    public static void main(String[] args){
        Integer b = new Integer(1000);
        System.out.println(TestEnum.SUCCESS.getCode() == b);
    }
}
