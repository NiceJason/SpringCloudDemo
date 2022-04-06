import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @Author DiaoJianBin
 * @Date 2022/3/14 14:29
 */
public class JustTest {

    public static void main(String[] args){

        int count = 0;

        while(true){
            //用来测试jinfo修改jvm参数行不行
            System.out.println();
            System.out.println("第"+count+"此执行---------------------------");
            SoftReference<byte[]> softReference = new SoftReference<>(new byte[5 * 1024 * 1024]);

            System.gc();

            byte[] bytes = new byte[5 * 1024 * 1024];

            try{
                Thread.currentThread().sleep(TimeUnit.SECONDS.toMillis(10));
            }catch (Exception e){

            }
            ++count;
        }
    }
}
