import org.junit.Test;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author DiaoJianBin
 * @Date 2022/1/19 10:09
 */
public class JustTest {

    @Test
    public void test() {
        RedissonClient client = null;
        RBloomFilter<Object> bloomFilter = client.getBloomFilter("");
        bloomFilter.tryInit(100, 0.03);
        bloomFilter.add("123");
        bloomFilter.contains("123");
    }
}
