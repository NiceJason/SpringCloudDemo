package proxy.jdk;

import org.junit.Test;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.PriorityQueue;

public class TestClass {
    @Test
    public void jdkProxyTest() {
        PriorityQueue queue = new PriorityQueue();
        queue.addAll(Arrays.asList(3,5,10,7,4,15,11,13,20,12,9));
        queue.remove(12);
    }
}
