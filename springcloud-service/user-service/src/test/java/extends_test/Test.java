package extends_test;

/**
 * @Author DiaoJianBin
 * @Date 2021/9/8 10:57
 */
public class Test {

    public static void main(String[] args) {
        C c = new C();
        System.out.println(c.nonStaticStr);  //A's nonstatic field
        System.out.println(c.staticStr);  //A's static field
        c.staticMethod(); //A's static method

        System.out.println("-------------------------------");

        A c1 = new C();
        System.out.println(c1.nonStaticStr);  //A's nonstatic field
        System.out.println(c1.staticStr);  //A's static field
        c1.staticMethod(); //A's static method

        // 以上这说明java中静态属性和静态方法可以被继承

        System.out.println("-------------------------------");
        B b = new B();
        System.out.println(b.nonStaticStr); // B's nonstatic field
        System.out.println(b.staticStr);   //B's static field
        b.staticMethod();  //B's static method

        System.out.println("-------------------------------");
        A b1 = new B();
        System.out.println(b1.nonStaticStr);  //A's nonstatic field
        System.out.println(b1.staticStr);  //A's static field
        b1.staticMethod(); //A's static method
        //b1.nonStaticStr  输出的是父类的非静态属性，说明非静态属性不可以被重写，不能实现多态
        //b1.staticStr     输出的是父类的静态属性，说明静态属性不可以被重写，不能实现多态
        //b1.staticMethod()输出的是父类A的静态方法，不是子类B改写后的，所以没有实现多态

        b1.nonStaticMethod();//普通方法是可以多态的
        //输出如下
        /*
        B's nonstatic method
		nonStaticStr = B's nonstatic field
		父类的nonStaticStr = A's nonstatic field
        */
    }
}
