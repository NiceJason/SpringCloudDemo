package extends_test;

/**
 * @Author DiaoJianBin
 * @Date 2021/9/8 11:13
 */
public class B extends A{
    public static String staticStr = "B's static field";
    public String nonStaticStr = "B's nonstatic field";
    public static void staticMethod(){
        System.out.println("B's static method");
    }
    public void nonStaticMethod(){
        System.out.println("B's nonstatic method");
        System.out.println("nonStaticStr = "+nonStaticStr);
        System.out.println("父类的nonStaticStr = "+super.nonStaticStr);
    }
}
