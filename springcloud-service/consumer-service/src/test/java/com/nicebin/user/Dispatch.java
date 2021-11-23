package com.nicebin.user;

/**
 * 方法静态分派演示
 * @author zzm
 */
public class Dispatch {
    public void sayHello(Man guy) {
        System.out.println("hello,gentleman!");
    }
    public void sayHello(Woman guy) {
        System.out.println("hello,lady!");
    }
    public void sayHello(Human guy) {
        System.out.println("hello,guy!");
    }
    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        Dispatch sr = new Dispatch();
        sr.sayHello(man);
        sr.sayHello(woman);
    }
}

 abstract class Human {
}
 class Man extends Human {
}
 class Woman extends Human {
}
