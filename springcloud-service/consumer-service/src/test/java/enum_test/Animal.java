package enum_test;

/**
 * @Author DiaoJianBin
 * @Date 2021/9/10 10:25
 */
public enum Animal {
    Cat(1,"小猫"){
        /*
         * 这里不允许有静态方法
        public static void staticTest(){

        }
         */

        /*
         * 允许非静态方法覆盖
         */
        public void say(){
            System.out.println("喵喵喵");
        }
    },
    Dog(2,"小狗")
    ;

    private Animal(Integer code,String name){
    }

    public static void staticTest(){
        System.out.println("这是枚举类的静态方法");
    }

    public void say(){
        System.out.println("这是枚举类的普通方法");
    }
}
