package proxy.jdk;

import lombok.Data;

/**
 * @Author DiaoJianBin
 * @Date 2021/7/22 12:55
 */
@Data
public class HelloImp implements Hello{

    String name;

    public String sayHello(String arg){
        return name+"说："+arg;
    }
}
