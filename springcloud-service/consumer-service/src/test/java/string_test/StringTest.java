package string_test;

import org.junit.Test;

/**
 * @Author DiaoJianBin
 * @Date 2021/11/2 16:07
 */
public class StringTest {

    @Test
    public void test(){
        String a = "todo";
        String b = "to";
        String c = "do";
        String d = "to"+"do";
        System.out.println(a == (b+c));
        System.out.println(a == d);

        final String e = "to";
        final String f = "do";
        System.out.println(a == (e+f));
    }
}
