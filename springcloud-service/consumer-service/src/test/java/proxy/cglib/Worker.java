package proxy.cglib;

import lombok.Data;

/**
 * @Author DiaoJianBin
 * @Date 2021/8/25 9:05
 */
@Data
public class Worker {
    String name;

    public void doWork(String something) {
        System.out.println(name + "正在" + something);
    }

    String test() {
        return name + "的test被调用了";
    }
}
