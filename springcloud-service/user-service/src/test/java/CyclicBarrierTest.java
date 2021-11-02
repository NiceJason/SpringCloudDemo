import org.junit.Test;

import java.util.concurrent.CyclicBarrier;

/**
 * @Author DiaoJianBin
 * @Date 2021/9/30 11:49
 */
public class CyclicBarrierTest {
    /**
     * CyclicBarrier的使用
     * 场景如下：有2个老师批改试卷，都改完后需汇总一次。一共重复3次
     */
    @Test
    public  void test() {
        //cyclicBarrier需要等待的数量，即cyclicBarrier.await()被调用两次后全部唤醒
        int count = 2;
        //第二个参数为等待结束后调用的线程
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count, new Runnable() {
            int now = 1;
            @Override
            public void run() {
                System.out.println("第"+now+"次汇总成功");
                now++;
            }
        });

        for(int i =1;i<=3;i++){
            Thread teacherA = new Thread(new Teacher(cyclicBarrier),"teacherA"+i);
            Thread teacherB = new Thread(new Teacher(cyclicBarrier),"teacherB"+i);

            teacherA.start();
            teacherB.start();
        }
        //保持主线程不结束，不然没东西输出
        while (true){

        }
    }

    class Teacher implements Runnable{

        CyclicBarrier cyclicBarrier;

        public Teacher(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"批卷完成");
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
