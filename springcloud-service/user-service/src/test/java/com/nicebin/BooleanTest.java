package com.nicebin;

import org.junit.Test;

public class BooleanTest {

    @Test
    public void test(){
        A aa = new A();

        System.out.println("Boolean="+aa.getA());
        System.out.println("boolean="+aa.isB());

        aa.setA(true);
        aa.setB(false);

        System.out.println("Boolean="+aa.getA());
        System.out.println("boolean="+aa.isB());

    }
}
