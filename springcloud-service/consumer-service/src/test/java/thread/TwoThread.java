package thread;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两条线程交替打印
 *
 * @Author DiaoJianBin
 * @Date 2022/1/28 15:42
 */
public class TwoThread {
    @Test
    public void test() throws Exception{
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        FirstThread firstThread = new FirstThread(lock,condition);
        SecondThread secondThread = new SecondThread(lock,condition);
        firstThread.start();
        secondThread.start();
    }
}

class FirstThread extends Thread {
    Condition condition;
    ReentrantLock lock;

    public FirstThread(ReentrantLock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    public void run() {
        while (true){
            lock.lock();
            try {
                System.out.println("First打印");
                condition.signal();
            }finally {
                try{
                    condition.await();
                }catch (InterruptedException e){
                    System.out.println("First被打断");
                }

                lock.unlock();
            }
        }
    }
}

class SecondThread extends Thread {
    Condition condition;
    ReentrantLock lock;

    public SecondThread(ReentrantLock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    public void run() {
        while (true){
            lock.lock();
            try {
                System.out.println("Second打印");
                condition.signal();
            }finally {
                try{
                    condition.await();
                }catch (InterruptedException e){
                    System.out.println("Second被打断");
                }
                lock.unlock();
            }
        }
    }
}
