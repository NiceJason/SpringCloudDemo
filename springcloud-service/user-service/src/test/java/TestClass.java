import org.springframework.core.annotation.Order;
import org.springframework.web.client.RestTemplate;

/**
 * @Author DiaoJianBin
 * @Description
 * @Date 2021/7/9 14:59
 */
@Order
public class TestClass {

    private static class Foo {
        private int x;
        private static int counter;

        public Foo() {
            x = (++counter);
        }
    }


    public static void main(String[] args) {
        RestTemplate restTemplate = null;
    }

}
