import lombok.Data;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author DiaoJianBin
 * @Date 2021/7/21 11:50
 */
@Data
public class JsonTest {
    @Test
    public void test() throws ParseException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = simpleDateFormat.format(date);
        System.out.println(result);

        String date2 = "2121-11-11 23:13:13";
        Date result2 = simpleDateFormat.parse(date2);
        System.out.println(result2);
    }

    public void getData(Integer a, String... b) {
        System.out.println("b :" + b);
    }

}

class Box<T> {
    private T data;

    public Box() {

    }

    public Box(T data) {
        setData(data);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
