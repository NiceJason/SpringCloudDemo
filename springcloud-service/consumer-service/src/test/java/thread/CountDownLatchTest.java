package thread;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @Author DiaoJianBin
 * @Date 2021/9/30 16:17
 */
public class CountDownLatchTest {
    /**
     * CountDownLatch的使用
     * 场景如下：有2个老师批改试卷，都改完后需汇总一次。
     */
    @Test
    public void test() {
        int count = 2;

        CountDownLatch countDownLatcht = new CountDownLatch(count);

        Thread teacherA = new Thread(new Teacher(countDownLatcht), "teacherA");
        Thread teacherB = new Thread(new Teacher(countDownLatcht), "teacherB");

        teacherA.start();
        teacherB.start();

        try {
            countDownLatcht.await();
            System.out.println("老师们改完试卷了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class Teacher implements Runnable {

        CountDownLatch countDownLatcht;

        public Teacher(CountDownLatch countDownLatcht) {
            this.countDownLatcht = countDownLatcht;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "批卷完成");
            try {
                countDownLatcht.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
