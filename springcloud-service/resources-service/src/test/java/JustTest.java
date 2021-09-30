import org.junit.Test;

/**
 * @Author DiaoJianBin
 * @Date 2021/9/30 9:34
 */
public class JustTest {

    @Test
    public void test(){
        Integer a = 3;
        change(a);
        System.out.println(a);
    }

    public void change(Integer d){
        d = 4;
        System.out.println(d);
    }
}
