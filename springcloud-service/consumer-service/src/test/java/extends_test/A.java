package extends_test;

/**
 * @Author DiaoJianBin
 * @Date 2021/9/8 11:13
 */
public class A {

    public static String staticStr = "A's static field";
    public String nonStaticStr = "A's nonstatic field";

    public static void staticMethod(){
        System.out.println("A's static method");
    }
    public void nonStaticMethod(){
        System.out.println("A's nonstatic method");
        System.out.println("nonStaticStr = "+nonStaticStr);
    }
}
